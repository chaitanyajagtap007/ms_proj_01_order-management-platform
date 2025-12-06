package com.mh.crj.service;

import com.mh.crj.entity.Order;
import com.mh.crj.model.OrderRequestDto;

public interface OrderService {
	
	public Order createOrder(OrderRequestDto orderRequestDto);

}
