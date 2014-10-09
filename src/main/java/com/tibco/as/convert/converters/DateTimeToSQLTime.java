package com.tibco.as.convert.converters;

import java.sql.Time;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToSQLTime implements IConverter {

	@Override
	public Time convert(Object value) {
		return new Time(((DateTime) value).getTimeInMillis());
	}
}
