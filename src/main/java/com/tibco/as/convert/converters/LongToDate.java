package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;

public class LongToDate implements IConverter<Long, Date> {

	@Override
	public Date convert(Long value) {
		return new Date(value);
	}
}
