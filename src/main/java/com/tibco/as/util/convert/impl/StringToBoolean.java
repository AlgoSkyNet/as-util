package com.tibco.as.util.convert.impl;

public class StringToBoolean extends AbstractConverter<String, Boolean> {

	private String truePattern;

	public StringToBoolean(String truePattern) {
		this.truePattern = truePattern;
	}

	protected Boolean doConvert(String source) {
		return truePattern.equalsIgnoreCase(source);
	}

}
