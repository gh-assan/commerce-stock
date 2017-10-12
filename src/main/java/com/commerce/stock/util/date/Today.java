package com.commerce.stock.util.date;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class Today implements TimeSpan {

	@Override
	public boolean supports(String timespan) {
		return timespan.equals("today");
	}

	@Override
	public DateRange get() {
		
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		Date timestamp =  Date.from(utc.toInstant());
		
		ZonedDateTime mid = utc.truncatedTo(ChronoUnit.DAYS);
		Date midTimestamp =  Date.from(mid.toInstant());
		
		return new DateRange(midTimestamp , timestamp);
	}

}
