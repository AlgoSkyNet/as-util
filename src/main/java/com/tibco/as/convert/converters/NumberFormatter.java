package com.tibco.as.convert.converters;

import java.text.DecimalFormat;

import com.tibco.as.convert.IConverter;

public class NumberFormatter implements IConverter {

	private DecimalFormat format;

	public NumberFormatter(String pattern) {
		this.format = new DecimalFormat(pattern);
	}

	@Override
	public String convert(Object source) {
		return format.format(source);
	}

}
