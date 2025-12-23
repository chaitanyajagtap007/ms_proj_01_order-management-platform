package com.mh.crj.service;

import java.util.List;

import com.mh.crj.entity.Product;
import com.mh.crj.model.ProductRequestDto;

public interface ProductService {
	
	public Product createProduct(ProductRequestDto productRequestDto);

	public List<Product> getAllProduct();

	public Product getProduct(Integer id);

	public Product updateStock(Integer id, Integer stock);

	public Product updatePrice(Integer id, Double price);

}