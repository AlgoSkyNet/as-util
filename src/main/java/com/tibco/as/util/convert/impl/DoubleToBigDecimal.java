package com.tibco.as.util.convert.impl;

import java.math.BigDecimal;

public class DoubleToBigDecimal extends AbstractConverter<Double, BigDecimal> {

	@Override
	protected BigDecimal doConvert(Double source) {
		return BigDecimal.valueOf(source);
	}

}