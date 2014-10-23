package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class StringToShort implements IConverter {

	@Override
	public Short convert(Object source) {
		return Short.valueOf((String) source);
	}

}
