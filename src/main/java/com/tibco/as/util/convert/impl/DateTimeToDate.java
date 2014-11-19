package com.tibco.as.util.convert.impl;

import java.util.Date;

import com.tibco.as.space.DateTime;
import com.tibco.as.util.convert.IConverter;

public class DateTimeToDate implements IConverter {

	@Override
	public Date convert(Object value) {
		return ((DateTime) value).getTime().getTime();
	}
}
