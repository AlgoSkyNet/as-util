package com.tibco.as.util.convert.impl;

public class NumberToFloat extends AbstractConverter<Number, Float> {

	@Override
	protected Float doConvert(Number source) {
		return source.floatValue();
	}

}
