package com.mh.crj.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mh.crj.entity.OrderStatus;
import com.mh.crj.entity.Orders;

public interface OrderRepo extends JpaRepository<Orders, Integer> {
	Optional<Orders> findByUserIdAndProductIdAndStatus(
	        Integer userId,
	        Integer productId,
	        OrderStatus status
	);

}
