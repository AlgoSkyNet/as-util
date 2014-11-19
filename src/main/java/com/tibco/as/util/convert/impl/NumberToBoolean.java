package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class NumberToBoolean implements IConverter {

	@Override
	public Boolean convert(Object value) {
		return ((Number) value).doubleValue() != 0;
	}

}
