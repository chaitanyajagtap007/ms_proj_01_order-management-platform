package com.mh.crj.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mh.crj.entity.Product;
import com.mh.crj.entity.ProductStatus;
import com.mh.crj.exception.ProductNotFoundException;
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
	
	@Override
	public Product getProduct(Integer id) {
		Product product = productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product is not available with id "+id));
		return product;
	}

	@Override
	public List<Product> getAllProduct() {
		List<Product> allProduct = productRepo.findAll();
		return allProduct;
	}
	
	@Override
	public Product updateStock(Integer id, Integer newStock) {
		
		Product product = productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product is not available with id "+id));
		product.setStatus(newStock > 0 ? ProductStatus.ACTIVE : ProductStatus.OUT_OF_STOCK);
		
		product.setStock(newStock);		
		
		Product updateStock = productRepo.save(product);
		return updateStock;
		
	}
	 
	
	@Override
	public Product updatePrice(Integer id, Double price) {

		Product product = productRepo.findById(id).orElseThrow(()-> new ProductNotFoundException("Product is not available with id "+id));
		product.setPrice(price);
		
		Product updatePrice = productRepo.save(product);
		
		return updatePrice;
	}
}
