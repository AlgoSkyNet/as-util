package com.tibco.as.util.convert.impl;

public class StringToInteger extends AbstractConverter<String, Integer> {

	@Override
	protected Integer doConvert(String source) {
		return Integer.valueOf(source);
	}

}
