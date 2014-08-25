package com.tibco.as.convert.format;


public class FloatFormat extends SimpleFormat {

	private static final long serialVersionUID = 4972386479521332572L;

	@Override
	public Object parse(String source) {
		return Float.valueOf(source);
	}

}
