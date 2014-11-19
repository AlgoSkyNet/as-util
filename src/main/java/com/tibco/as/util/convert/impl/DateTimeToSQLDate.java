package com.tibco.as.util.convert.impl;

import java.sql.Date;

import com.tibco.as.space.DateTime;
import com.tibco.as.util.convert.IConverter;

public class DateTimeToSQLDate implements IConverter {

	@Override
	public Date convert(Object value) {
		return new Date(((DateTime) value).getTimeInMillis());
	}
}
