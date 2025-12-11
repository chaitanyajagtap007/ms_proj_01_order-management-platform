package com.mh.crj.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mh.crj.model.ResponseMessage;

@FeignClient(name ="user-service", url = "http://localhost:9090/user")
public interface UserServiceClient {
	@GetMapping("/getById/{id}")
	public ResponseMessage getUser(@PathVariable Integer id);
}