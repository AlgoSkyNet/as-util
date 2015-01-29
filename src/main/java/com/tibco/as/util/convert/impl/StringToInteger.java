package com.tibco.as.util.convert.impl;

public class StringToInteger extends AbstractStringParser<Integer> {

	@Override
	protected Integer parse(String string) {
		return Integer.valueOf(string);
	}

}
