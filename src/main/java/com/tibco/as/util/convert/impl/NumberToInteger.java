package com.tibco.as.util.convert.impl;

public class NumberToInteger extends AbstractConverter<Number, Integer> {

	@Override
	protected Integer doConvert(Number source) {
		return source.intValue();
	}

}
