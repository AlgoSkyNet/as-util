package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToShort implements IConverter {

	@Override
	public Short convert(Object value) {
		return ((Number) value).shortValue();
	}

}
