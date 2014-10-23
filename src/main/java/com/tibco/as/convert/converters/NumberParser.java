package com.tibco.as.convert.converters;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import com.tibco.as.convert.IConverter;

public class NumberParser implements IConverter {

	private NumberFormat format;

	public NumberParser(String pattern) {
		this.format = new DecimalFormat(pattern);
	}

	@Override
	public Number convert(Object source) throws ParseException {
		return format.parse((String) source);
	}

}
