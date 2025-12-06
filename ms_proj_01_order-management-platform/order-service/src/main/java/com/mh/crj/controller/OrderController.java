package com.mh.crj.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:5174")
public class OrderController {

	@GetMapping("/msg")
	public String getMethodName() {
		return "this is order service";
	}
	
}
