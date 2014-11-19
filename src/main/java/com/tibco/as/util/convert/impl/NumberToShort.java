package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToShort implements IConverter {

	@Override
	public Short convert(Object value) {
		return ((Number) value).shortValue();
	}

}
