package com.commerce.stock.util.date;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class LastMonth implements TimeSpan {

	@Override
	public boolean supports(String timespan) {
		return timespan.equals("lastMonth");
	}

	@Override
	public DateRange get() {
		
		ZonedDateTime utc = ZonedDateTime.now(ZoneOffset.UTC);
		Date timestamp =  Date.from(utc.toInstant());
		
		LocalDate initial = LocalDate.now();
		LocalDate month = initial.withDayOfMonth(1);
		
		Date monthTimestamp = Date.from(month.atStartOfDay(ZoneOffset.UTC).toInstant());

		
		return new DateRange(monthTimestamp , timestamp);
	}

}
