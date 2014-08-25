package com.tibco.as.convert.converters;

import java.util.Calendar;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class CalendarToDateTime implements IConverter<Calendar, DateTime> {

	@Override
	public DateTime convert(Calendar value) {
		return DateTime.create(value);
	}

}
