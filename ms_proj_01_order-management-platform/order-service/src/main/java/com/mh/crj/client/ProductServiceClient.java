package com.mh.crj.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mh.crj.model.ProductDto;

@FeignClient(name ="product-service", url = "http://localhost:9091/product")
public interface ProductServiceClient {
	
	@GetMapping("/getProduct/{id}")
	public ProductDto  getProduct(@PathVariable Integer id);
	

    @PutMapping("/updateStock/{id}")
    public ProductDto updateStock(@PathVariable Integer id,@RequestParam Integer stock);
	
}
