package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToBoolean implements IConverter<Number, Boolean> {

	@Override
	public Boolean convert(Number value) {
		return value.doubleValue() != 0;
	}

}
