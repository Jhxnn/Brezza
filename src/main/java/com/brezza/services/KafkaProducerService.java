package com.brezza.services;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmailEvent(String email, String nome) {
        String message = "{\"email\": \"" + email + "\", \"nome\": \"" + nome + "\"}";
        kafkaTemplate.send("usuario.criado", message);
    }
    public void sendGenerateQRCodeMessage(String qrCode, double value, String description) {
        String message = String.format("{\"qrCode\": \"%s\", \"value\": %f, \"description\": \"%s\"}", qrCode, value, description);

        sendMessage("pagamento.gerar", message);
    }

    public void sendPaymentApprovedMessage(String qrCode, double value, String description) {
        String message = String.format("{\"qrCode\": \"%s\", \"value\": %f, \"description\": \"%s\"}", qrCode, value, description);

        sendMessage("pagamento.aprovado", message);
    }
    private void sendMessage(String topic, String message) {
        try {
            kafkaTemplate.send(topic, message);
            System.out.println("Mensagem enviada para o tópico " + topic + ": " + message);
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem para o Kafka: " + e.getMessage());
        }
    }

}

