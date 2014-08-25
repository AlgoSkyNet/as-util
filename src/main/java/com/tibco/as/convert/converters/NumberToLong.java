package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToLong implements IConverter<Number, Long> {

	@Override
	public Long convert(Number value) {
		return value.longValue();
	}

}
