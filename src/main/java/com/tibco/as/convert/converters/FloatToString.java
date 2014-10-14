package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.FloatFormat;

public class FloatToString extends AbstractNumberFormatter {

	public FloatToString(Field field) {
		super(field.getDecimalFormat(), new FloatFormat());
	}

}
