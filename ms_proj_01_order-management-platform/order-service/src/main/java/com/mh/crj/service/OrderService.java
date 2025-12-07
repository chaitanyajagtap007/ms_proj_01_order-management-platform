package com.mh.crj.service;

import java.util.List;

import com.mh.crj.entity.Orders;
import com.mh.crj.model.OrderRequestDto;

public interface OrderService {

	public Orders createOrder(OrderRequestDto orderRequestDto);
	public Orders getOrder(Integer id);
//	public List<Orders> getAllOrders();
//	public void cancleOrder(Integer id);
}
 