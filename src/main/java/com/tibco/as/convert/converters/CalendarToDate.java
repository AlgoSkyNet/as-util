package com.tibco.as.convert.converters;

import java.util.Calendar;
import java.util.Date;

import com.tibco.as.convert.IConverter;

public class CalendarToDate implements IConverter {

	@Override
	public Date convert(Object source) {
		return ((Calendar) source).getTime();
	}

}
