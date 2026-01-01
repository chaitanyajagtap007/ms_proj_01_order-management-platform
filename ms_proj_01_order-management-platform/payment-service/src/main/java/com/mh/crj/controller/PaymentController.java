package com.mh.crj.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {


	@GetMapping("/msg")
	public String getMethodName() {
		return "this is payment service";
	}
	
}
