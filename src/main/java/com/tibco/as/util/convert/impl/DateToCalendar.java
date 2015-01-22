package com.tibco.as.util.convert.impl;

import java.util.Calendar;
import java.util.Date;

public class DateToCalendar extends AbstractConverter<Date, Calendar> {

	@Override
	protected Calendar doConvert(Date source) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(source);
		return calendar;
	}

}
