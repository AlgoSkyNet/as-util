package com.tibco.as.convert.converters;

import java.text.DateFormat;
import java.util.Date;

import com.tibco.as.convert.IConverter;

public class DateToString implements IConverter {

	private DateFormat format;

	public DateToString(DateFormat format) {
		this.format = format;
	}

	@Override
	public String convert(Object source) {
		return format.format((Date) source);
	}

}