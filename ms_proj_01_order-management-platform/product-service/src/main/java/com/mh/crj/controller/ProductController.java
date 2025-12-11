package com.mh.crj.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.entity.Product;
import com.mh.crj.model.ProductRequestDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.service.ProductService;
import com.mh.crj.utility.Constants;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
    private ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody ProductRequestDto productRequestDto) {
    	Product product = productService.createProduct(productRequestDto);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Order created successfully",product));
	  }
	
}
