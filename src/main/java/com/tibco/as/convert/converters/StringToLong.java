package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.LongFormat;

public class StringToLong extends AbstractNumberParser {

	public StringToLong(Field field) {
		super(field.getIntegerFormat(), new LongFormat());
	}

	@Override
	protected Long convert(Number number) {
		return number.longValue();
	}
}
