package com.tibco.as.convert.converters;

import com.tibco.as.convert.ConverterFactory;
import com.tibco.as.convert.Field;

public class BooleanToString extends AbstractFormatter {

	public BooleanToString(Field field) {
		super(ConverterFactory.getBooleanFormat(field));
	}

}
