package com.tibco.as.util.convert.impl;

import java.util.Date;

import com.tibco.as.space.DateTime;
import com.tibco.as.util.convert.IConverter;

public class DateToDateTime implements IConverter {

	@Override
	public DateTime convert(Object value) {
		return DateTime.create(((Date) value).getTime());
	}

}
