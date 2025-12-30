package com.mh.crj.exception;

public class InsufficientStockException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InsufficientStockException(String msg){
		super(msg);
	}
}