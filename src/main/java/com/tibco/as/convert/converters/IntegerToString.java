package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.IntegerFormat;

public class IntegerToString extends AbstractNumberFormatter {

	public IntegerToString(Field field) {
		super(field.getIntegerFormat(), new IntegerFormat());
	}

}
