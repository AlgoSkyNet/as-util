package com.tibco.as.util.convert.impl;

import java.sql.Time;

import com.tibco.as.space.DateTime;

public class DateTimeToSQLTime extends AbstractConverter<DateTime, Time> {

	@Override
	protected Time doConvert(DateTime source) {
		return new Time(source.getTimeInMillis());
	}
}
