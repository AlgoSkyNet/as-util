package com.tibco.as.convert.format;


public class DoubleFormat extends SimpleFormat {

	private static final long serialVersionUID = -6966466924550469451L;

	@Override
	public Object parse(String source) {
		return Double.valueOf(source);
	}

}
