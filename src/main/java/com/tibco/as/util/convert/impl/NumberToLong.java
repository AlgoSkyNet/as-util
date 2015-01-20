package com.tibco.as.util.convert.impl;

public class NumberToLong extends AbstractConverter<Number, Long> {

	@Override
	protected Long doConvert(Number source) {
		return source.longValue();
	}

}
