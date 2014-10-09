package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateToDateTime implements IConverter {

	@Override
	public DateTime convert(Object value) {
		return DateTime.create(((Date) value).getTime());
	}

}
