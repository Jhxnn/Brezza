package com.brezza.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brezza.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, UUID>{

}
