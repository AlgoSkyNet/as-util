package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import com.tibco.as.space.DateTime;

public class CalendarToDateTime extends AbstractConverter<Calendar, DateTime> {

	@Override
	protected DateTime doConvert(Calendar source) {
		return DateTime.create(source);
	}

}
