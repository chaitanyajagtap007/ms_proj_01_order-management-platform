package com.mh.crj.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.mh.crj.model.OrderDto;
import com.mh.crj.model.ResponseMessage;


@FeignClient(name ="order-service")
public interface OrderServiceClient {


	@GetMapping("/order/getOrder/{id}")
	public Optional<OrderDto>  getOrderForFeignClient(@PathVariable Integer id);
	
	@PutMapping("/order/{orderId}/confirm")
	public  ResponseEntity<ResponseMessage>  confirmOrder(@PathVariable Integer orderId);
	
	@DeleteMapping("/order/{id}/cancle")
	public  ResponseEntity<ResponseMessage> cancleOrder(@PathVariable Integer id);

}