package com.brezza.services;

import java.io.IOException;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.brezza.dtos.PaymentDto;
import com.brezza.models.Payment;
import com.brezza.models.enums.PaymentStatus;
import com.brezza.repositories.PaymentRepository;
import com.brezza.repositories.UserRepository;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class PixService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	KafkaProducerService kafkaProducerService;
	
	@Autowired
	PaymentRepository paymentRepository;
	
	@Value("${asaas.api.token}")
    private String asaasToken;
	
	@Value("${address.key}")
	private String addressKey;
	
    private static final String API_URL = "https://api-sandbox.asaas.com/v3/pix/qrCodes/static";
    private  final String ACCESS_TOKEN = asaasToken;
    private final OkHttpClient client = new OkHttpClient();

    public Payment gerarQRCode(PaymentDto paymentDto) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject json = new JSONObject();
        json.put("addressKey", addressKey);
        json.put("format", "PAYLOAD");
        json.put("value", paymentDto.value());
        json.put("description", paymentDto.description());

        String jsonBody = json.toString();

        RequestBody body = RequestBody.create(jsonBody, mediaType);
        Request request = new Request.Builder()
                .url(API_URL)
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("access_token", ACCESS_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response.code() + " - " + response.message());
            }
            
            String responseBody = response.body() != null ? response.body().string() : "";

            JSONObject jsonResponse = new JSONObject(responseBody);
            String qrCodeId = jsonResponse.getString("id"); 
            String payload = jsonResponse.getString("payload"); 

            Payment payment = new Payment();
            var payer = userRepository.findById(paymentDto.payer()).orElseThrow(()-> new RuntimeException("cannot be found"));
            payment.setPayer(payer);
            payment.setIdQr(qrCodeId);
            payment.setDescription(paymentDto.description());
            payment.setValue(paymentDto.value());
            payment.setPayload(payload);
            payment.setStatus(PaymentStatus.PENDING);
            paymentRepository.save(payment);
            kafkaProducerService.sendGenerateQRCodeMessage(qrCodeId, paymentDto.value(), paymentDto.description());
            return payment;
        }
    }
    
    public String pagarPix(UUID paymentId) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");

        var payment = paymentRepository.findById(paymentId).orElseThrow(()-> new RuntimeException("cannot be found"));
        
        JSONObject json = new JSONObject();
        json.put("qrCode", payment.getIdQr());
        json.put("value", payment.getValue());
        json.put("description", payment.getDescription());

        String jsonBody = json.toString();
        RequestBody body = RequestBody.create(jsonBody, mediaType);

        Request request = new Request.Builder()
                .url("https://api-sandbox.asaas.com/v3/pix/qrCodes/pay")
                .post(body)
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
                .addHeader("access_token", ACCESS_TOKEN)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Erro na requisição: " + response.code() + " - " + response.message());
            }
            payment.setStatus(PaymentStatus.PAYED);
            paymentRepository.save(payment);
            kafkaProducerService.sendPaymentApprovedMessage(payment.getIdQr(), payment.getValue(), payment.getDescription());
            return response.body() != null ? response.body().string() : "Pagamento efetuado";
        }
    }
  
    
}
