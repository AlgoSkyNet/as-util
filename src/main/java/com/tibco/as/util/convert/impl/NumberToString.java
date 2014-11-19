package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToString implements IConverter {

	@Override
	public Object convert(Object source) {
		return ((Number) source).toString();
	}

}