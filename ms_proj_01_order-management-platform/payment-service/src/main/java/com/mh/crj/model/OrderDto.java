package com.mh.crj.model;

import lombok.Data;

@Data
public class OrderDto {

	private Integer userId;
	private Integer productId;
    private Integer quantity;
}
