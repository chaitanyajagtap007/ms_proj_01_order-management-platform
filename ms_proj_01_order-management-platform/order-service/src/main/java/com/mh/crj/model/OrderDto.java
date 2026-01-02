package com.mh.crj.model;

import com.mh.crj.entity.OrderStatus;

import lombok.Data;

@Data
public class OrderDto {

	private Integer orderId;
	private Integer userId;
	private Integer productId;
    private Integer quantity;
    private Double totalAmount;
    private OrderStatus status;
}
