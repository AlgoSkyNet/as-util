package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.ConverterFactory;

public class BytesToString extends AbstractFormatter {

	public BytesToString(Attributes attributes) {
		super(ConverterFactory.getBlobFormat(attributes));
	}

}
