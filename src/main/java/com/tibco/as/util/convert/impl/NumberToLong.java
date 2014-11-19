package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToLong implements IConverter {

	@Override
	public Long convert(Object value) {
		return ((Number) value).longValue();
	}

}
