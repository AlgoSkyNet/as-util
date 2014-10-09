package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ConverterFactory;

public class StringToBoolean extends AbstractParser {

	public StringToBoolean(Attributes attributes) {
		super(ConverterFactory.getBooleanFormat(attributes));
	}

	@Override
	protected Boolean parse(String string) throws ConvertException {
		return (Boolean) parseObject(string);
	}

}
