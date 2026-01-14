package com.mh.crj.service;


import com.mh.crj.entity.Payment;
import com.mh.crj.entity.PaymentMethod;

public interface PaymentService {

	public Payment initiatePayment(Integer orderId ,Integer productId,PaymentMethod paymentMethod);
	

	public Payment markPaymentSuccess(Integer paymentId);

	public Payment markPaymentFailed(Integer paymentId);
	
}
