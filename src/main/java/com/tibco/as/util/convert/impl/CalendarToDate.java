package com.tibco.as.util.convert.impl;

import java.util.Calendar;
import java.util.Date;

import com.tibco.as.util.convert.IConverter;

public class CalendarToDate implements IConverter {

	@Override
	public Date convert(Object source) {
		return ((Calendar) source).getTime();
	}

}
