package com.tibco.as.util.convert.impl;

public class StringToDouble extends AbstractConverter<String, Double> {

	@Override
	protected Double doConvert(String source) {
		return Double.valueOf(source);
	}

}
