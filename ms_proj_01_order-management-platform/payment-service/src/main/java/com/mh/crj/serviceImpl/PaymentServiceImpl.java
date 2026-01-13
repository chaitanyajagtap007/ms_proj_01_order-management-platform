package com.mh.crj.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.mh.crj.client.OrderServiceClient;
import com.mh.crj.entity.Payment;
import com.mh.crj.entity.PaymentMethod;
import com.mh.crj.entity.PaymentStatus;
import com.mh.crj.exception.OrderNotFoundException;
import com.mh.crj.model.OrderDto;
import com.mh.crj.model.PaymentRequestDto;
import com.mh.crj.repository.PaymentRepo;
import com.mh.crj.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private OrderServiceClient orderServiceClient;
	
	@Override
	public Payment initiatePayment(Integer orderId ,Integer productId,PaymentMethod paymentMethod) {
		
		OrderDto order = orderServiceClient.getOrderForFeignClient(orderId).orElseThrow(()-> new OrderNotFoundException("Order is not available with this id.."));
		
		
			
		Payment payment= Payment.builder()
		         .orderId(order.getOrderId())
		         .userId(order.getUserId())
		         .amount(order.getTotalAmount())
		         .paymentMethod(paymentMethod)
		         .status(PaymentStatus.PENDING)
		         .build();

		Payment save = paymentRepo.save(payment);
	
		return save;
		
	}
	
	// add new methods
	// if payment is done then change the payment status
}
