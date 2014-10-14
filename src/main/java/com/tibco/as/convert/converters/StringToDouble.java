package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.DoubleFormat;

public class StringToDouble extends AbstractNumberParser {

	public StringToDouble(Field field) {
		super(field.getDecimalFormat(), new DoubleFormat());
	}

	@Override
	protected Double convert(Number number) {
		return number.doubleValue();
	}

}
