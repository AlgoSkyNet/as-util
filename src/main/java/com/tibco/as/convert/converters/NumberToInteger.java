package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToInteger implements IConverter<Number, Integer> {

	@Override
	public Integer convert(Number value) {
		return value.intValue();
	}
	
}
