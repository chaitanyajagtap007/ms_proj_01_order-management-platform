package com.mh.crj.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

	private Integer id;
	
	private String name;
	
    private Double price;

    private Integer stock;

}
