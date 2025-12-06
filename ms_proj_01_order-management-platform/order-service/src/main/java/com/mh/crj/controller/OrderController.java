package com.mh.crj.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.model.ResponseMessage;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5174")
public class OrderController {

	@GetMapping("/msg")
	public String getMethodName() {
		return "this is order service";
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseMessage> createOrder(@RequestBody String entity) {
		//TODO: process POST request
		
		return null;
	}
	
	
}
