package com.tibco.as.util.convert.impl;

import java.util.Calendar;
import java.util.Date;

import com.tibco.as.util.convert.IConverter;

public class DateToCalendar implements IConverter {

	@Override
	public Calendar convert(Object date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) date);
		return calendar;
	}

}
