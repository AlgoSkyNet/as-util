package com.tibco.as.util.convert.impl;

public class NumberToString extends AbstractConverter<Number, String> {

	@Override
	protected String doConvert(Number source) {
		return source.toString();
	}

}