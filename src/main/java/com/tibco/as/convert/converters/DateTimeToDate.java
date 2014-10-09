package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateTimeToDate implements IConverter {

	@Override
	public Date convert(Object value) {
		return ((DateTime) value).getTime().getTime();
	}
}
