package com.cg.onlineshopping.exception;

public class CustomerAlreadyExistsException extends RuntimeException {

	public CustomerAlreadyExistsException() {

	}

	public CustomerAlreadyExistsException(String msg) {
		super(msg);
	}
}
