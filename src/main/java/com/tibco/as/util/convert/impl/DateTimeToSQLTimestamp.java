package com.tibco.as.util.convert.impl;

import java.sql.Timestamp;

import com.tibco.as.space.DateTime;
import com.tibco.as.util.convert.IConverter;

public class DateTimeToSQLTimestamp implements IConverter {

	@Override
	public Timestamp convert(Object value) {
		return new Timestamp(((DateTime) value).getTimeInMillis());
	}
}
