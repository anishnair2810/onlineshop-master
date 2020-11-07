package com.cg.onlineshopping.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class CentralizedExceptionHandler {
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(AddressNotFoundException.class)
	public String handleAddressNotFound(AddressNotFoundException e)
	{
		return e.getMessage();
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(AddressAlreadyExistsException.class)
	public String handleCustomerAlreadyExists(AddressAlreadyExistsException e)
	{
		return e.getMessage();
	}
}
