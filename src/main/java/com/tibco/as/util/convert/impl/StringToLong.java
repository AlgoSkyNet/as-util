package com.tibco.as.util.convert.impl;

public class StringToLong extends AbstractConverter<String, Long> {

	@Override
	protected Long doConvert(String source) {
		return Long.valueOf(source);
	}

}
