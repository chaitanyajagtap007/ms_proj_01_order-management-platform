package com.mh.crj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.crj.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{
	
}
