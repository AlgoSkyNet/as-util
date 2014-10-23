package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class StringToLong implements IConverter {

	@Override
	public Long convert(Object source) {
		return Long.valueOf((String) source);
	}

}
