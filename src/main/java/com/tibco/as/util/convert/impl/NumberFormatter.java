package com.tibco.as.util.convert.impl;

import java.text.NumberFormat;

public class NumberFormatter extends AbstractConverter<Number, String> {

	private NumberFormat format;

	public NumberFormatter(NumberFormat format) {
		this.format = format;
	}

	@Override
	protected String doConvert(Number source) {
		return format.format(source);
	}

}
