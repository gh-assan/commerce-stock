package com.commerce.stock.util.date;

public interface TimeSpan {
	
	public boolean supports(String timespan);
	public DateRange get();

}
