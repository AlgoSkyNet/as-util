package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.IntegerFormat;

public class StringToInteger extends AbstractNumberParser {

	public StringToInteger(Field field) {
		super(field.getIntegerFormat(), new IntegerFormat());
	}

	@Override
	protected Integer convert(Number number) {
		return number.intValue();
	}

}
