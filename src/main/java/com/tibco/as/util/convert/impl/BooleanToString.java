package com.tibco.as.util.convert.impl;

public class BooleanToString extends AbstractConverter<Boolean, String> {

	private String truePattern;
	private String falsePattern;

	public BooleanToString(String truePattern, String falsePattern) {
		this.truePattern = truePattern;
		this.falsePattern = falsePattern;
	}

	@Override
	protected String doConvert(Boolean source) {
		return source ? truePattern : falsePattern;
	}

}
