package com.cwp.ps.app.service;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cwp.ps.app.entity.Payment;
import com.cwp.ps.app.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired(required = true)
	private PaymentRepository paymentRepository;	
	
	public Payment doPay(Payment payment) {
		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		return paymentRepository.save(payment);
	}
	
	public String paymentProcessing() {
		// this should by 3rd party api call,ex/ paypal, paytm etc
		return new Random().nextBoolean() ? "success" : "failed";
	}
}
