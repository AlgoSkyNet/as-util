package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.LongFormat;

public class LongToString extends AbstractNumberFormatter {

	public LongToString(Field field) {
		super(field.getIntegerFormat(), new LongFormat());
	}

}
