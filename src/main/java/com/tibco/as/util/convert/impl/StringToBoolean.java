package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class StringToBoolean implements IConverter {

	private String truePattern;

	public StringToBoolean(String truePattern) {
		this.truePattern = truePattern;
	}

	@Override
	public Boolean convert(Object source) {
		return truePattern.equalsIgnoreCase((String) source);
	}

}
