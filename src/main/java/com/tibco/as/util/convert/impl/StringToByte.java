package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class StringToByte implements IConverter {

	@Override
	public Byte convert(Object source) {
		return Byte.valueOf((String) source);
	}

}
