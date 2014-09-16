package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToShort implements IConverter<Number, Short> {

	@Override
	public Short convert(Number value) {
		return value.shortValue();
	}

}
