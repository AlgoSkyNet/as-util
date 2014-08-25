package com.tibco.as.convert.format;


public class LongFormat extends SimpleFormat {

	private static final long serialVersionUID = -5114969923012552772L;

	@Override
	public Object parse(String source) {
		return Long.valueOf(source);
	}

}
