package com.mh.crj.serviceImpl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.crj.client.OrderServiceClient;
import com.mh.crj.entity.Payment;
import com.mh.crj.entity.PaymentMethod;
import com.mh.crj.entity.PaymentStatus;
import com.mh.crj.exception.OrderNotFoundException;
import com.mh.crj.exception.PaymentNotFoundException;
import com.mh.crj.model.OrderDto;
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
		
		
		/**
	     * STEP 1: Initiate payment (PENDING)
	     */
			
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

	/**
     * STEP 2: Payment SUCCESS
     * - Update payment status
     * - Notify Order Service
     */
	
	@Override
    public Payment markPaymentSuccess(Integer paymentId) {

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() ->
                        new PaymentNotFoundException("Payment not found with id: " + paymentId));

        payment.setStatus(PaymentStatus.SUCCESS);
        payment.setUpdatedAt(LocalDateTime.now());

        Payment savedPayment = paymentRepo.save(payment);

        // 🔥 VERY IMPORTANT
        // Notify order-service AFTER payment success
        orderServiceClient.confirmOrder(payment.getOrderId());

        return savedPayment;
    }
	
	/**
     * STEP 3: Payment FAILED
     */
    @Override
    public Payment markPaymentFailed(Integer paymentId) {

        Payment payment = paymentRepo.findById(paymentId)
                .orElseThrow(() ->
                        new PaymentNotFoundException("Payment not found with id: " + paymentId));

        payment.setStatus(PaymentStatus.FAILED);
        payment.setUpdatedAt(LocalDateTime.now());

        Payment canPayment = paymentRepo.save(payment);
        
        // 🔥 VERY IMPORTANT
        // Notify order-service AFTER payment faild
        orderServiceClient.cancleOrder(payment.getOrderId());
        
        
        return canPayment;
    }
		
}
