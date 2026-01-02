package com.mh.crj.client;

import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mh.crj.model.OrderDto;


@FeignClient(name ="order-service", url = "http://localhost:9092/order")
public interface OrderServiceClient {


	@GetMapping("/getOrder/{id}")
	public Optional<OrderDto>  getOrderForFeignClient(@PathVariable Integer id);

}