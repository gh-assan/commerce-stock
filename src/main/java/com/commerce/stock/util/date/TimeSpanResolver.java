package com.commerce.stock.util.date;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeSpanResolver {
	
	List<TimeSpan> timespans;

	@Autowired
	public TimeSpanResolver(List<TimeSpan> timespans) {
		this.timespans = timespans;
	}

	public DateRange resolve(String timespan) {
		
		DateRange result = null;
		
		for (TimeSpan s : this.timespans )
			if (s.supports(timespan))
				return s.get();
		
		return result;
	}
}
