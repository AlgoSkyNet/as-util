package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

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
