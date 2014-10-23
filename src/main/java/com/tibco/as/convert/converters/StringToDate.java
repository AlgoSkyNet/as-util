package com.tibco.as.convert.converters;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

public class StringToDate extends AbstractStringParser {

	private DateFormat format;

	public StringToDate(DateFormat format) {
		this.format = format;
	}

	@Override
	protected Date parse(String string) throws ParseException {
		return format.parse(string);
	}

}
