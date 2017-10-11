package com.commerce.stock.exception;

public class OutdatedStockException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	public static int CODE = 204;
	
	@Override public String getMessage() {
		return "outdated stock, because a newer stock was processed first";
	}
	
}
