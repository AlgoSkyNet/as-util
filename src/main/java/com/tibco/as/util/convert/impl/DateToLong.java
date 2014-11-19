package com.tibco.as.util.convert.impl;

import java.util.Date;

import com.tibco.as.util.convert.IConverter;

public class DateToLong implements IConverter {

	@Override
	public Long convert(Object source) {
		return ((Date) source).getTime();
	}

}
