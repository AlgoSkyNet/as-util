package com.tibco.as.util.convert.impl;

import java.util.Calendar;
import java.util.Date;

public class CalendarToDate extends AbstractConverter<Calendar, Date> {

	@Override
	protected Date doConvert(Calendar source) {
		return source.getTime();
	}

}
