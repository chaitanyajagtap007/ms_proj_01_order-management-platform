package com.mh.crj.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.entity.Order;
import com.mh.crj.model.OrderRequestDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.service.OrderService;
import com.mh.crj.utility.Constants;

import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5174")
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/msg")
	public String getMethodName() {
		return "this is order service";
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseMessage> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		Order order = orderService.createOrder(orderRequestDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"User save successfully",order));
		
	}
	
	
}
