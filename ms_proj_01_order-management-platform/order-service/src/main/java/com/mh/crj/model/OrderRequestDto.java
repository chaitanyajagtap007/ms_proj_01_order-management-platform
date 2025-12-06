package com.mh.crj.model;

import lombok.Data;

@Data
public class OrderRequestDto {

	private Long userId;
    private Long productId;
    private Integer quantity;
    private Double totalAmount;
	
}
