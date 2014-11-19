package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToInteger implements IConverter {

	@Override
	public Integer convert(Object value) {
		return ((Number) value).intValue();
	}

}
