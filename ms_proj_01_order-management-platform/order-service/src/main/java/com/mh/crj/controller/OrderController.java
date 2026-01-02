package com.mh.crj.controller;

import java.net.HttpURLConnection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mh.crj.entity.OrderStatus;
import com.mh.crj.entity.Orders;
import com.mh.crj.model.OrderDto;
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
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Order created successfully",order));
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<ResponseMessage>  getOrder(@PathVariable Integer id) {
		Orders order = orderService.getOrder(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"Order get successfully",order));
	}
	
	@GetMapping("/getOrder/{id}")
	public OrderDto getOrderForFeignClient(@PathVariable Integer id) {
		Orders order = orderService.getOrder(id);
		
		OrderDto dto = new OrderDto();
		
		dto.setOrderId(order.getId());
		dto.setUserId(order.getUserId());
		dto.setProductId(order.getProductId());
		dto.setTotalAmount(order.getTotalAmount());
		dto.setStatus(order.getStatus());
		
		return dto;
	}
	
	@GetMapping("/get-all")
	public ResponseEntity<ResponseMessage> getAllOrders() {
		List<Orders> allOrders = orderService.getAllOrders();
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,"All Order get successfully",allOrders));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseMessage> updateOrder(@PathVariable Integer id, @RequestParam OrderStatus status) {
		Orders updateOrder = orderService.updateStatus(id, status);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage(HttpURLConnection.HTTP_CREATED,Constants.SUCCESS,"Order created successfully",updateOrder));
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseMessage>  cancleOrder(@PathVariable Integer id) {
		String msg = orderService.cancleOrder(id);
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(HttpURLConnection.HTTP_OK,Constants.SUCCESS,msg));
	}
	
}
