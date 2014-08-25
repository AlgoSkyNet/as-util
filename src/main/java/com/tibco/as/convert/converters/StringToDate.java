package com.tibco.as.convert.converters;

import java.text.DateFormat;
import java.util.Date;

import com.tibco.as.convert.ConvertException;

public class StringToDate extends AbstractParser<Date> {

	public StringToDate(DateFormat format) {
		super(format);
	}

	@Override
	protected Date parse(String string) throws ConvertException {
		return (Date) parseObject(string);
	}

}
