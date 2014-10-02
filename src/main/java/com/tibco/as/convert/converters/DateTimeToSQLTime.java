package com.tibco.as.convert.converters;

import java.sql.Time;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToSQLTime implements IConverter<DateTime, Time> {

	@Override
	public Time convert(DateTime value) {
		return new Time(value.getTimeInMillis());
	}
}
