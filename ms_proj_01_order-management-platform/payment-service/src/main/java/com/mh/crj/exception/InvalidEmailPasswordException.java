package com.mh.crj.exception;

public class InvalidEmailPasswordException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public InvalidEmailPasswordException(String msg) {
		super(msg);
	}

}
