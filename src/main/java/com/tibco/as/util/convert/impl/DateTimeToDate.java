package com.tibco.as.util.convert.impl;

import java.util.Date;

import com.tibco.as.space.DateTime;

public class DateTimeToDate extends AbstractConverter<DateTime, Date> {

	@Override
	protected Date doConvert(DateTime source) {
		return source.getTime().getTime();
	}
}
