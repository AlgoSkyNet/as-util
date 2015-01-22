package com.tibco.as.util.convert.impl;

public class NumberToBoolean extends AbstractConverter<Number, Boolean> {

	@Override
	protected Boolean doConvert(Number source) {
		return source.doubleValue() != 0;
	}

}
