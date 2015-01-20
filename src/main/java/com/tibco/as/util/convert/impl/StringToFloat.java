package com.tibco.as.util.convert.impl;

public class StringToFloat extends AbstractConverter<String, Float> {

	@Override
	protected Float doConvert(String source) {
		return Float.valueOf(source);
	}

}
