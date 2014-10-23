package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToByte implements IConverter {

	@Override
	public Byte convert(Object value) {
		return ((Number) value).byteValue();
	}

}
