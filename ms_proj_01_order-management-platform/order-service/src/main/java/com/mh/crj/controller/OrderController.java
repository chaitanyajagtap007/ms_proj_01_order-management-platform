package com.mh.crj.controller;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.entity.Orders;
import com.mh.crj.model.OrderRequestDto;
import com.mh.crj.model.ResponseMessage;
import com.mh.crj.service.OrderService;
import com.mh.crj.utility.Constants;


@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@GetMapping("/msg")
	public String getMethodName() {
		return "this is order service";
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResponseMessage> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
		Orders order = orderService.createOrder(orderRequestDto);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"Order created successfully",order));
	}
	
	@PostMapping("/get/{id}")
	public ResponseEntity<ResponseMessage>  getOrder(@PathVariable Integer id) {
		Orders order = orderService.getOrder(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"Order created successfully",order));
	}
	
}
