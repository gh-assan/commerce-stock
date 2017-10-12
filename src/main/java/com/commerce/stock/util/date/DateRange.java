package com.commerce.stock.util.date;

import java.util.Date;

public class DateRange {
	
	private Date from;
	private Date until;
	
	public DateRange(Date from, Date until) {
		super();
		this.from = from;
		this.until = until;
	}

	public Date getFrom() {
		return from;
	}

	@Override
	public String toString() {
		return "DateRange [from=" + from + ", until=" + until + "]";
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getUntil() {
		return until;
	}

	public void setUntil(Date until) {
		this.until = until;
	}
	
	
	
}
