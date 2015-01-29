package com.tibco.as.util.convert.impl;

public class StringToLong extends AbstractStringParser<Long> {

	@Override
	protected Long parse(String string) {
		return Long.valueOf(string);
	}

}
