package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToFloat implements IConverter {

	@Override
	public Float convert(Object value) {
		return ((Number) value).floatValue();
	}

}
