package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToByte implements IConverter {

	@Override
	public Byte convert(Object value) {
		return ((Number) value).byteValue();
	}

}
