package com.tibco.as.util.convert.impl;

public class NumberToShort extends AbstractConverter<Number, Short> {

	@Override
	protected Short doConvert(Number source) {
		return source.shortValue();
	}

}
