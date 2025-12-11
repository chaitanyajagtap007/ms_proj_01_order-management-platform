package com.mh.crj.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.crj.entity.Product;
import com.mh.crj.entity.ProductStatus;
import com.mh.crj.model.ProductRequestDto;
import com.mh.crj.repository.ProductRepo;
import com.mh.crj.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public Product createProduct(ProductRequestDto productRequestDto) {
		System.out.println(productRequestDto);
		Product product = Product.builder()
				.name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .stock(productRequestDto.getStock())
                .status(productRequestDto.getStock() > 0 ? ProductStatus.ACTIVE : ProductStatus.OUT_OF_STOCK)
                .build();
		
		Product savedProduct = productRepo.save(product);
		System.out.println(product);
		return savedProduct;
	}
	 
}
