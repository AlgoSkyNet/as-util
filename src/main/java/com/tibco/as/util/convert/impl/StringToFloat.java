package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class StringToFloat implements IConverter {

	@Override
	public Float convert(Object source) {
		return Float.valueOf((String) source);
	}

}
