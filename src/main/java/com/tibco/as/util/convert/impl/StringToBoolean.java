package com.tibco.as.util.convert.impl;

public class StringToBoolean extends AbstractStringParser<Boolean> {

	private String truePattern;

	public StringToBoolean(String truePattern) {
		this.truePattern = truePattern;
	}

	@Override
	protected Boolean parse(String string) {
		return truePattern.equalsIgnoreCase(string);
	}

}
