package com.mh.crj.controller;


import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.entity.Payment;
import com.mh.crj.entity.PaymentMethod;
import com.mh.crj.entity.PaymentStatus;
import com.mh.crj.model.PaymentRequestDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.service.PaymentService;
import com.mh.crj.utility.Constants;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@GetMapping("/msg")
	public String getMethodName() {
		return "this is payment service";
	} 
	
	
	@PostMapping("/initiate")
    public ResponseEntity<ResponseMessage> initiatePayment(
    		@RequestParam Integer orderId,    		
    		@RequestParam Integer productId,
            @RequestParam PaymentMethod paymentMethod) {

        Payment payment = paymentService.initiatePayment(orderId,productId,paymentMethod);

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Payment initiated successfully ",payment));
	}

	/**
     * STEP 2: Payment SUCCESS
     * Called by PAYMENT GATEWAY (simulated) / frontend callback
     * This will internally call Order-Service
     */
    @PutMapping("/{paymentId}/success")
    public ResponseEntity<ResponseMessage> paymentSuccess(
            @PathVariable Integer paymentId) {

        Payment payment = paymentService.markPaymentSuccess(paymentId);

        return ResponseEntity.ok(
                new ResponseMessage(
                        HttpStatus.OK.value(),
                        Constants.SUCCESS,
                        "Payment successful",
                        payment
                ));
    }

	
    /**
     * STEP 3: Payment FAILED
     * Called when payment fails
     */
    @PutMapping("/{paymentId}/failed")
    public ResponseEntity<ResponseMessage> paymentFailed(
            @PathVariable Integer paymentId) {

        Payment payment = paymentService.markPaymentFailed(paymentId);

        return ResponseEntity.ok(
                new ResponseMessage(
                        HttpStatus.OK.value(),
                        Constants.SUCCESS,
                        "Payment failed",
                        payment
                ));
    }
    
}
