package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToDate implements IConverter<DateTime, Date> {

	@Override
	public Date convert(DateTime value) {
		return value.getTime().getTime();
	}
}
