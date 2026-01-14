package com.mh.crj.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.crj.entity.Payment;
import com.mh.crj.entity.PaymentMethod;
import com.mh.crj.entity.PaymentStatus;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

	public Optional<Payment> findBfindByOrderIdAndPaymentMethodAndAmountAndStatus(Integer orderId,PaymentMethod paymentMethod,Double amount,PaymentStatus status);
}
