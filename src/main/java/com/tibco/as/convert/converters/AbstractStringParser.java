package com.tibco.as.convert.converters;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public abstract class AbstractStringParser<T> implements IConverter<String, T> {

	@Override
	public T convert(String string) throws ConvertException {
		if (string.isEmpty()) {
			return null;
		}
		return parse(string);
	}

	protected abstract T parse(String string) throws ConvertException;

}
