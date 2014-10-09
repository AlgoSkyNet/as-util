package com.tibco.as.convert.converters;

import java.text.Format;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public abstract class AbstractFormatter implements IConverter {

	private Format format;

	public AbstractFormatter(Format format) {
		this.format = format;
	}

	@Override
	public String convert(Object source) throws ConvertException {
		try {
			return format.format(source);
		} catch (IllegalArgumentException e) {
			throw new ConvertException(e, source);
		}
	}

}
