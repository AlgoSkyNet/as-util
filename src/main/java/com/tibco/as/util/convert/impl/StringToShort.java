package com.tibco.as.util.convert.impl;

public class StringToShort extends AbstractStringParser<Short> {

	@Override
	protected Short parse(String string) {
		return Short.valueOf(string);
	}

}
