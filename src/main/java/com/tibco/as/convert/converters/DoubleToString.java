package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.DoubleFormat;

public class DoubleToString extends AbstractNumberFormatter {

	public DoubleToString(Field field) {
		super(field.getDecimalFormat(), new DoubleFormat());
	}

}
