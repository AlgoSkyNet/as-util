package com.tibco.as.util.convert.impl;

public class StringToShort extends AbstractConverter<String, Short> {

	@Override
	protected Short doConvert(String source) {
		return Short.valueOf(source);
	}

}
