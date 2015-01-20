package com.tibco.as.util.convert.impl;

import java.sql.Timestamp;

import com.tibco.as.space.DateTime;

public class DateTimeToSQLTimestamp extends
		AbstractConverter<DateTime, Timestamp> {

	@Override
	protected Timestamp doConvert(DateTime source) {
		return new Timestamp(source.getTimeInMillis());
	}
}
