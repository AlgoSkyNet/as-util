package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;

public class DateToLong implements IConverter<Date, Long> {

	@Override
	public Long convert(Date value) {
		return value.getTime();
	}

}
