package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.FloatFormat;

public class StringToFloat extends AbstractNumberParser {

	public StringToFloat(Field field) {
		super(field.getDecimalFormat(), new FloatFormat());
	}

	@Override
	protected Float convert(Number number) {
		return number.floatValue();
	}

}
