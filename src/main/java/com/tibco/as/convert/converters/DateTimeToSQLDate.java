package com.tibco.as.convert.converters;

import java.sql.Date;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToSQLDate implements IConverter {

	@Override
	public Date convert(Object value) {
		return new Date(((DateTime) value).getTimeInMillis());
	}
}
