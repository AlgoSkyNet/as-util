package com.tibco.as.convert.converters;

import java.util.Calendar;
import java.util.Date;

import com.tibco.as.convert.IConverter;

public class DateToCalendar implements IConverter {

	@Override
	public Calendar convert(Object date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) date);
		return calendar;
	}

}
