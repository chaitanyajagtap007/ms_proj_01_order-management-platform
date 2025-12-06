package com.mh.crj.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.crj.entity.OrderStatus;
import com.mh.crj.entity.Orders;
import com.mh.crj.model.OrderRequestDto;
import com.mh.crj.repository.OrderRepo;
import com.mh.crj.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepo orderRepo;
	
	@Override
	public Orders createOrder(OrderRequestDto orderRequestDto) {

		Orders order = Orders.builder()
				.userId(orderRequestDto.getUserId())
                .productId(orderRequestDto.getProductId())
                .quantity(orderRequestDto.getQuantity())
                .totalAmount(orderRequestDto.getTotalAmount())
                .status(OrderStatus.CREATED)
                .build();
		
		Orders createdOrder = orderRepo.save(order);
		return createdOrder;
	}
	
}
