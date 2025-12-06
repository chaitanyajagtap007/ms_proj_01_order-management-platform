package com.mh.crj.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.crj.entity.Order;
import com.mh.crj.entity.OrderStatus;
import com.mh.crj.model.OrderRequestDto;
import com.mh.crj.repository.OrderRepository;
import com.mh.crj.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order createOrder(OrderRequestDto orderRequestDto) {
		 Order order = Order.builder()
	                .userId(orderRequestDto.getUserId())
	                .productId(orderRequestDto.getProductId())
	                .quantity(orderRequestDto.getQuantity())
	                .totalAmount(orderRequestDto.getTotalAmount())
	                .status(OrderStatus.CREATED)
	                .build();
		 
		Order save = orderRepository.save(order);
		return save;
	}

}
