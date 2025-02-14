package com.brezza.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brezza.dtos.PaymentDto;
import com.brezza.services.PixService;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	
	@Autowired
	PixService pixService;
	
	
	@PostMapping("/qrCode")
	public ResponseEntity<String> gerarQrCode(@RequestBody PaymentDto paymentDto) throws IOException {
	    String response = pixService.gerarQRCode(paymentDto);
	    return ResponseEntity.ok(response);
	}

}
