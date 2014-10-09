package com.tibco.as.convert.converters;

import java.util.Date;

import com.tibco.as.convert.IConverter;

public class DateToLong implements IConverter {

	@Override
	public Long convert(Object source) {
		return ((Date) source).getTime();
	}

}
