package com.tibco.as.convert.converters;

import com.tibco.as.convert.Attributes;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ConverterFactory;

public class StringToBytes extends AbstractParser {

	public StringToBytes(Attributes attributes) {
		super(ConverterFactory.getBlobFormat(attributes));
	}

	@Override
	protected byte[] parse(String string) throws ConvertException {
		return (byte[]) parseObject(string);
	}

}
