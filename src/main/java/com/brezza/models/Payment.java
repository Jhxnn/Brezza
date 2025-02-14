package com.brezza.models;

import java.util.UUID;

import com.brezza.models.enums.PaymentMethod;

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
	
	private double value;
	
	private PaymentMethod method;

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

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public PaymentMethod getMethod() {
		return method;
	}

	public void setMethod(PaymentMethod method) {
		this.method = method;
	}
	
	
	
	
}
