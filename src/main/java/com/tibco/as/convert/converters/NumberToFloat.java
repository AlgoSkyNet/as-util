package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToFloat implements IConverter {

	@Override
	public Float convert(Object value) {
		return ((Number) value).floatValue();
	}

}
