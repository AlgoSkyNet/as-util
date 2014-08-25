package com.tibco.as.convert.converters;

import java.text.DateFormat;
import java.util.Date;

public class DateToString extends AbstractFormatter<Date> {

	public DateToString(DateFormat format) {
		super(format);
	}

}