package com.tibco.as.util.convert.impl;

public class StringToDouble extends AbstractStringParser<Double> {

	@Override
	protected Double parse(String string) {
		return Double.valueOf(string);
	}

}
