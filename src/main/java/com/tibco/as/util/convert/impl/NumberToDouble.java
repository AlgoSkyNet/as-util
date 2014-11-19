package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToDouble implements IConverter {

	@Override
	public Double convert(Object value) {
		return ((Number) value).doubleValue();
	}

}
