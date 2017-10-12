package com.commerce.stock.util.date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class DateFormatter {
		
		SimpleDateFormat dateFormat ;
	
		public DateFormatter() {
			
			dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	    	dateFormat.setTimeZone(TimeZone.getTimeZone("UCT")); 

		}
		
		
		public DateFormatter(String format) {			
			dateFormat = new SimpleDateFormat(format);
	    	dateFormat.setTimeZone(TimeZone.getTimeZone("UCT")); 
		}
		
		public String format(Date date) {
			return dateFormat.format(date);
		}
}
