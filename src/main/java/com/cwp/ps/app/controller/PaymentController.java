package com.cwp.ps.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwp.ps.app.entity.Payment;
import com.cwp.ps.app.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;
	
	@PostMapping("/pay")
	public ResponseEntity<Payment> submitPayment(@RequestBody Payment payment){
		if(!ObjectUtils.isEmpty(payment)) {
			Payment pay = paymentService.doPay(payment);
			return new ResponseEntity<>(pay, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/pay/history/{orderId}")
	public ResponseEntity<Payment> findPaymentHistoryByOrderId(@PathVariable int orderId){
		
		Payment payment = paymentService.findPayHistoryByOrderId(orderId);
		if(!ObjectUtils.isEmpty(payment)) {
			return new ResponseEntity<>(payment, HttpStatus.FOUND);
		}
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
}
