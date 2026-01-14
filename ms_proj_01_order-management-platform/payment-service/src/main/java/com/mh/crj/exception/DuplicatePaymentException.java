package com.mh.crj.exception;


public class DuplicatePaymentException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DuplicatePaymentException(String message) {
        super(message);
    }
}
