package com.tibco.as.convert.converters;

import java.util.Calendar;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToCalendar implements IConverter<DateTime, Calendar> {

	@Override
	public Calendar convert(DateTime value) {
		return value.getTime();
	}

}
