package com.tibco.as.convert.format;


public class ShortFormat extends SimpleFormat {

	private static final long serialVersionUID = 7739767893757637688L;

	@Override
	public Object parse(String source) {
		return Short.valueOf(source);
	}

}
