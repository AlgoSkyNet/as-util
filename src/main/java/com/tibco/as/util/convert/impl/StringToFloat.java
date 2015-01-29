package com.tibco.as.util.convert.impl;

public class StringToFloat extends AbstractStringParser<Float> {

	@Override
	protected Float parse(String string) {
		return Float.valueOf(string);
	}

}
