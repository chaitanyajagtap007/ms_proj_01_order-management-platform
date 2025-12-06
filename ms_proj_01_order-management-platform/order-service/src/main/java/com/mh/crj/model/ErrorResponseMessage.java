package com.mh.crj.model;

import java.util.HashMap;
import java.util.List;

import lombok.Data;

@Data
public class ErrorResponseMessage {

	private Integer statusCode;
	private String status;
	private String message;
	private List<?> list;
	private HashMap<?,?> map;
	
	
	public ErrorResponseMessage(Integer statusCode, String status, String message, List<?> list) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.list = list;
	}


	public ErrorResponseMessage(Integer statusCode, String status, String message, HashMap<?, ?> map) {
		super();
		this.statusCode = statusCode;
		this.status = status;
		this.message = message;
		this.map = map;
	}
	
}
