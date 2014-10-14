package com.tibco.as.convert.converters;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ConverterFactory;
import com.tibco.as.convert.Field;

public class StringToBoolean extends AbstractParser {

	public StringToBoolean(Field field) {
		super(ConverterFactory.getBooleanFormat(field));
	}

	@Override
	protected Boolean parse(String string) throws ConvertException {
		return (Boolean) parseObject(string);
	}

}
