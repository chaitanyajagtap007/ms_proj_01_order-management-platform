package com.mh.crj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.crj.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
