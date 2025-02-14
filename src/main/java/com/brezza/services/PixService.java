package com.brezza.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.brezza.dtos.PaymentDto;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class PixService {
	
	@Value("${asaas.api.token}")
    private static String asaasToken;
	
	@Value("${address.key}")
	private static String addressKey;
	
    private static final String API_URL = "https://api-sandbox.asaas.com/v3/pix/qrCodes/static";
    private static final String ACCESS_TOKEN = asaasToken;
    private final OkHttpClient client = new OkHttpClient();

    public String gerarQRCode(PaymentDto paymentDto) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"addressKey\":\""+ addressKey + "\",\"format\":\"PAYLOAD\",\"value\":" +paymentDto.value() + "\"description\":\""+ paymentDto.description() +"\"}");
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
            return response.body() != null ? response.body().string() : "Resposta vazia";
        }
    }
}
