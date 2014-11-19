package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import com.tibco.as.space.DateTime;
import com.tibco.as.util.convert.IConverter;

public class DateTimeToCalendar implements IConverter {

	@Override
	public Calendar convert(Object value) {
		return ((DateTime) value).getTime();
	}

}
