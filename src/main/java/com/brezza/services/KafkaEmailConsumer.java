package com.brezza.services;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.json.JSONObject;

@Service
public class KafkaEmailConsumer {

    private final EmailService emailService;

    public KafkaEmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

	    @KafkaListener(topics = "usuario.criado", groupId = "email-group")
    public void processEmail(String message) {
        JSONObject json = new JSONObject(message);
        String email = json.getString("email");
        String nome = json.getString("nome");

        emailService.enviarEmailTexto(email, 
            "Conta Criada - BREZZA", 
            "Sua conta foi criada com sucesso. \nBem vindo ao BREZZA, " + nome + 
            ". \nChave de verificação: 443. \nQualquer dúvida, contate nosso suporte!"
        );
    }
}
