package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class BooleanToString implements IConverter {

	private String truePattern;
	private String falsePattern;

	public BooleanToString(String truePattern, String falsePattern) {
		this.truePattern = truePattern;
		this.falsePattern = falsePattern;
	}

	@Override
	public String convert(Object source) {
		if ((Boolean) source) {
			return truePattern;
		}
		return falsePattern;
	}

}
