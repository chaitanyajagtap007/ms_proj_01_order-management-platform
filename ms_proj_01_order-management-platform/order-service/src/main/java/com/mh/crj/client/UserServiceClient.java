package com.mh.crj.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mh.crj.model.UserDto;


@FeignClient(name ="user-service", url = "http://localhost:9090/user")
public interface UserServiceClient {
	
	@GetMapping("/getUser/{id}")
	public UserDto getUser(@PathVariable Integer id);

}