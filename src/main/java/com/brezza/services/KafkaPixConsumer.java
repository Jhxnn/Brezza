package com.brezza.services;

import org.json.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaPixConsumer {

	@KafkaListener(topics = "pagamento.gerar", groupId = "payment-group")
    public void consumeGenerateQRCodeMessage(String message) {
        System.out.println("Mensagem para gerar QRCode recebida: " + message);
        JSONObject jsonMessage = new JSONObject(message);
        
        String qrCode = jsonMessage.getString("qrCode");
        double value = jsonMessage.getDouble("value");
        String description = jsonMessage.getString("description");

        
        generateQRCode(qrCode, value, description);
    }
	@KafkaListener(topics = "pagamento.aprovado", groupId = "payment-group")
    public void consumePaymentApprovedMessage(String message) {
        System.out.println("Mensagem de pagamento aprovado recebida: " + message);
        JSONObject jsonMessage = new JSONObject(message);
        
        String qrCode = jsonMessage.getString("qrCode");
        double value = jsonMessage.getDouble("value");
        String description = jsonMessage.getString("description");

        processPayment(qrCode, value, description);
    }

    private void generateQRCode(String qrCode, double value, String description) {
        System.out.println("Gerando QRCode com as seguintes informações:");
        System.out.println("QRCode: " + qrCode);
        System.out.println("Valor: " + value);
        System.out.println("Descrição: " + description);

    }

    private void processPayment(String qrCode, double value, String description) {
        System.out.println("Processando pagamento com QRCode: " + qrCode);
        System.out.println("Valor: " + value);
        System.out.println("Descrição: " + description);

    }
}
