package com.tibco.as.convert.converters;

import java.sql.Date;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToSQLDate implements IConverter<DateTime, Date> {

	@Override
	public Date convert(DateTime value) {
		return new Date(value.getTimeInMillis());
	}
}
