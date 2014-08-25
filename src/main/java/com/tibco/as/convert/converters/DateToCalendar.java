package com.tibco.as.convert.converters;

import java.util.Calendar;
import java.util.Date;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class DateToCalendar implements IConverter<Date, Calendar> {

	@Override
	public Calendar convert(Date date) throws ConvertException {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

}
