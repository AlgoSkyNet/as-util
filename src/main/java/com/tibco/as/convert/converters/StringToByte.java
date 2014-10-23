package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class StringToByte implements IConverter {

	@Override
	public Byte convert(Object source) {
		return Byte.valueOf((String) source);
	}

}
