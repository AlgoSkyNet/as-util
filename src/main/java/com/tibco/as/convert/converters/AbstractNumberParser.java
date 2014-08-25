package com.tibco.as.convert.converters;

import java.text.Format;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ConverterFactory;

public abstract class AbstractNumberParser<T extends Number> extends
		AbstractParser<T> {

	public AbstractNumberParser(String pattern, Format defaultFormat) {
		super(ConverterFactory.getNumberFormat(pattern, defaultFormat));
	}

	@Override
	protected T parse(String string) throws ConvertException {
		return convert((Number) parseObject(string));
	}

	protected abstract T convert(Number number);

}
