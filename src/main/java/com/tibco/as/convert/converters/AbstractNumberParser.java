package com.tibco.as.convert.converters;

import java.text.Format;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ConverterFactory;

public abstract class AbstractNumberParser extends AbstractParser {

	public AbstractNumberParser(String pattern, Format defaultFormat) {
		super(ConverterFactory.getNumberFormat(pattern, defaultFormat));
	}

	@Override
	protected Object parse(String string) throws ConvertException {
		return convert((Number) parseObject(string));
	}

	protected abstract Object convert(Number number);

}
