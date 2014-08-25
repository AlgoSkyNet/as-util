package com.tibco.as.convert.format;


public class IntegerFormat extends SimpleFormat {

	private static final long serialVersionUID = 4952088134032086809L;

	@Override
	public Object parse(String source) {
		return Integer.valueOf(source);
	}

}
