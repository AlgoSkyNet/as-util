package com.tibco.as.util.convert.impl;

import java.sql.Time;

import com.tibco.as.space.DateTime;
import com.tibco.as.util.convert.IConverter;

public class DateTimeToSQLTime implements IConverter {

	@Override
	public Time convert(Object value) {
		return new Time(((DateTime) value).getTimeInMillis());
	}
}
