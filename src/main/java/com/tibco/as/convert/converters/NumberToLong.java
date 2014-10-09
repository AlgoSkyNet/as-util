package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToLong implements IConverter {

	@Override
	public Long convert(Object value) {
		return ((Number) value).longValue();
	}

}
