package com.tibco.as.util.convert.impl;

import java.util.Date;

import com.tibco.as.util.convert.IConverter;

public class LongToDate implements IConverter {

	@Override
	public Date convert(Object value) {
		return new Date((Long) value);
	}
}
