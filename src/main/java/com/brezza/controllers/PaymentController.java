package com.brezza.controllers;

import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brezza.dtos.PaymentDto;
import com.brezza.models.Payment;
import com.brezza.services.PixService;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	
	@Autowired
	PixService pixService;
	
	
	@PostMapping("/qrCode")
	public ResponseEntity<Payment> gerarQrCode(@RequestBody PaymentDto paymentDto) throws IOException {
	    return ResponseEntity.status(HttpStatus.CREATED).body(pixService.gerarQRCode(paymentDto));
	}
	
	@PostMapping("/pagar/{paymentId}")
	public ResponseEntity<String> pagarQrCode(@PathVariable(name = "paymentId")UUID id) throws IOException{
		return ResponseEntity.status(HttpStatus.OK).body(pixService.pagarPix(id));
	}

}
