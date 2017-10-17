package com.commerce.stock.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends BaseException implements HasCode{
	
	private static final long serialVersionUID = 1L;
	
	
	@Override 
	public String getMessage() {
		return "product Not found";
	}

	@Override
	public HttpStatus getCode() {
		// TODO Auto-generated method stub
		return HttpStatus.NOT_FOUND;
	}
	
}
