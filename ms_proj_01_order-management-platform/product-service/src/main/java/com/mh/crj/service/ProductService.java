package com.mh.crj.service;

import com.mh.crj.entity.Product;
import com.mh.crj.model.ProductRequestDto;

public interface ProductService {
	
	public Product createProduct(ProductRequestDto productRequestDto);

}