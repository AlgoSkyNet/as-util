package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.ConverterFactory;

public class BooleanToString extends AbstractFormatter {

	public BooleanToString(Attributes attributes) {
		super(ConverterFactory.getBooleanFormat(attributes));
	}

}
