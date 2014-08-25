package com.tibco.as.convert.converters;

import java.text.Format;

import com.tibco.as.convert.ConverterFactory;

public abstract class AbstractNumberFormatter<S extends Number> extends
		AbstractFormatter<S> {

	public AbstractNumberFormatter(String pattern, Format defaultFormat) {
		super(ConverterFactory.getNumberFormat(pattern, defaultFormat));
	}

}
