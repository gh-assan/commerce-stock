package com.commerce.stock.exception;

import org.springframework.http.HttpStatus;

public class OutdatedStockException extends BaseException implements HasCode {
	
	private static final long serialVersionUID = 1L;
	
	
	
	@Override 
	public String getMessage() {
		return "outdated stock, because a newer stock was processed first";
	}
	
	@Override
	public HttpStatus getCode() {
		// TODO Auto-generated method stub
		 return HttpStatus.NO_CONTENT;
	}
}
