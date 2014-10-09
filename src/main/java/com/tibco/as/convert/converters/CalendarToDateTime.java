package com.tibco.as.convert.converters;

import java.util.Calendar;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class CalendarToDateTime implements IConverter {

	@Override
	public DateTime convert(Object value) {
		return DateTime.create((Calendar) value);
	}

}
