package com.tibco.as.convert.converters;

import java.text.Format;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public abstract class AbstractFormatter<T> implements IConverter<T, String> {

	private Format format;

	public AbstractFormatter(Format format) {
		this.format = format;
	}

	@Override
	public String convert(T source) throws ConvertException {
		try {
			return format.format(source);
		} catch (IllegalArgumentException e) {
			throw new ConvertException(e, source);
		}
	}

}
