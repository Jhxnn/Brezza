package com.brezza.models;

import java.util.UUID;

import com.brezza.models.enums.PaymentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "payments")
public class Payment {

	
	private UUID paymentId;
	
	
	@ManyToOne
	@JoinColumn(name = "payer", referencedColumnName = "id")
	private Users payer;
	
	private String idQr;
	
	private double value;
	
	private String payload;

	private String description;
	
	private PaymentStatus status;
	
	
	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public UUID getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(UUID paymentId) {
		this.paymentId = paymentId;
	}

	public Users getPayer() {
		return payer;
	}

	public void setPayer(Users payer) {
		this.payer = payer;
	}

	public String getIdQr() {
		return idQr;
	}

	public void setIdQr(String idQr) {
		this.idQr = idQr;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getPayload() {
		return payload;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	
	
	
	
	
}
