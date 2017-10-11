package com.commerce.stock.exception;

import org.springframework.http.HttpStatus;

public interface HasCode {
	
	public HttpStatus getCode();

}
