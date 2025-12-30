package com.mh.crj.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mh.crj.model.ProductDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.model.UserDto;

@FeignClient(name ="product-service", url = "http://localhost:9091/product")
public interface ProductServiceClient {
	
	@GetMapping("/getProduct/{id}")
	public ProductDto  getProduct(@PathVariable Integer id);
	
}
