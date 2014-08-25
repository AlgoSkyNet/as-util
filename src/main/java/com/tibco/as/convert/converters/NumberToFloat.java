package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToFloat implements IConverter<Number, Float> {

	@Override
	public Float convert(Number value) {
		return value.floatValue();
	}

}
