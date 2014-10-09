package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToBoolean implements IConverter {

	@Override
	public Boolean convert(Object value) {
		return ((Number) value).doubleValue() != 0;
	}

}
