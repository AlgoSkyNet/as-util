package com.tibco.as.convert.converters;

import java.math.BigDecimal;

import com.tibco.as.convert.IConverter;

public class DoubleToBigDecimal implements
		IConverter<Double, BigDecimal> {

	@Override
	public BigDecimal convert(Double value) {
		return BigDecimal.valueOf(value);
	}
	
}
