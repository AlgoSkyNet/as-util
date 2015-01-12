package com.tibco.as.util.convert.impl;

import java.text.NumberFormat;

import com.tibco.as.util.convert.IConverter;

public class NumberFormatter implements IConverter {

	private NumberFormat format;

	public NumberFormatter(NumberFormat format) {
		this.format = format;
	}

	@Override
	public String convert(Object source) {
		return format.format(source);
	}

}
