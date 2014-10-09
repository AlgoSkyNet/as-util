package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToInteger implements IConverter {

	@Override
	public Integer convert(Object value) {
		return ((Number) value).intValue();
	}

}
