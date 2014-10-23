package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class StringToFloat implements IConverter {

	@Override
	public Float convert(Object source) {
		return Float.valueOf((String) source);
	}

}
