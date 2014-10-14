package com.tibco.as.convert.converters;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ConverterFactory;
import com.tibco.as.convert.Field;

public class StringToBytes extends AbstractParser {

	public StringToBytes(Field field) {
		super(ConverterFactory.getBlobFormat(field));
	}

	@Override
	protected byte[] parse(String string) throws ConvertException {
		return (byte[]) parseObject(string);
	}

}
