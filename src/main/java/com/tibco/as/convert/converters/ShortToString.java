package com.tibco.as.convert.converters;

import com.tibco.as.convert.Field;
import com.tibco.as.convert.format.ShortFormat;

public class ShortToString extends AbstractNumberFormatter {

	public ShortToString(Field field) {
		super(field.getIntegerFormat(), new ShortFormat());
	}

}
