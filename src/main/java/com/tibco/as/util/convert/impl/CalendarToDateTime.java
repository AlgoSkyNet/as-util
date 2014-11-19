package com.tibco.as.util.convert.impl;

import java.util.Calendar;

import com.tibco.as.space.DateTime;
import com.tibco.as.util.convert.IConverter;

public class CalendarToDateTime implements IConverter {

	@Override
	public DateTime convert(Object value) {
		return DateTime.create((Calendar) value);
	}

}
