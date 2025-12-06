package com.mh.crj.model;

import lombok.Data;

@Data
public class OrderRequestDto {

	private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Double totalAmount;
	
}
