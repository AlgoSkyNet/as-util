package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;
import com.tibco.as.space.DateTime;

public class DateToDateTime implements IConverter<Date, DateTime> {

	@Override
	public DateTime convert(Date value) {
		return DateTime.create(value.getTime());
	}

}
