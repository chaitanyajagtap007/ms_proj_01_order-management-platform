package com.mh.crj.service;

import com.mh.crj.entity.Orders;
import com.mh.crj.model.OrderRequestDto;

public interface OrderService {

	public Orders createOrder(OrderRequestDto orderRequestDto);

}
 