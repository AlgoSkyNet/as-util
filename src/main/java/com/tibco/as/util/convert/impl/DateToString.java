package com.tibco.as.util.convert.impl;

import java.text.DateFormat;
import java.util.Date;

public class DateToString extends AbstractConverter<Date, String> {

	private DateFormat format;

	public DateToString(DateFormat format) {
		this.format = format;
	}

	@Override
	protected String doConvert(Date source) {
		return format.format(source);
	}

}