package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;

public class LongToDate implements IConverter {

	@Override
	public Date convert(Object value) {
		return new Date((Long) value);
	}
}
