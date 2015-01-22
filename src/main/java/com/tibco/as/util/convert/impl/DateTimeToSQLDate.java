package com.tibco.as.util.convert.impl;

import java.sql.Date;

import com.tibco.as.space.DateTime;

public class DateTimeToSQLDate extends AbstractConverter<DateTime, Date> {

	@Override
	protected Date doConvert(DateTime source) {
		return new Date(source.getTimeInMillis());
	}
}
