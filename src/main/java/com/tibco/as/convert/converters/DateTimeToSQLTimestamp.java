package com.tibco.as.convert.converters;

import java.sql.Timestamp;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToSQLTimestamp implements IConverter {

	@Override
	public Timestamp convert(Object value) {
		return new Timestamp(((DateTime) value).getTimeInMillis());
	}
}
