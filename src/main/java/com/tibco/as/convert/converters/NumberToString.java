package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToString implements IConverter {

	@Override
	public Object convert(Object source) {
		return ((Number) source).toString();
	}

}