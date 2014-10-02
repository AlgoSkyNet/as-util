package com.tibco.as.convert.converters;

import java.sql.Timestamp;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToSQLTimestamp implements IConverter<DateTime, Timestamp> {

	@Override
	public Timestamp convert(DateTime value) {
		return new Timestamp(value.getTimeInMillis());
	}
}
