package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToDouble implements IConverter {

	@Override
	public Double convert(Object value) {
		return ((Number) value).doubleValue();
	}

}
