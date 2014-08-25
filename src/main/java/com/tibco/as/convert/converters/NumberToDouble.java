package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class NumberToDouble implements IConverter<Number, Double> {

	@Override
	public Double convert(Number value) {
		return value.doubleValue();
	}

}
