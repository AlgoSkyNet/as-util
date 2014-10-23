package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

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
