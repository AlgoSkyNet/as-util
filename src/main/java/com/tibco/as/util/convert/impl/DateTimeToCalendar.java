package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import com.tibco.as.space.DateTime;

public class DateTimeToCalendar extends AbstractConverter<DateTime, Calendar> {

	@Override
	protected Calendar doConvert(DateTime source) {
		return source.getTime();
	}

}
