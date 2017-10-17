package com.commerce.stock.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends Exception implements HasCode {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public abstract HttpStatus getCode();

}
