package com.tibco.as.util.convert.impl;

public class NumberToDouble extends AbstractConverter<Number, Double> {

	@Override
	protected Double doConvert(Number source) {
		return source.doubleValue();
	}

}
