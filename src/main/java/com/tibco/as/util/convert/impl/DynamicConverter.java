package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.ConverterFactory;
import com.tibco.as.util.convert.IConverter;

public class DynamicConverter implements IConverter {

	private ConverterFactory factory;
	private Class<?> to;

	public DynamicConverter(ConverterFactory factory, Class<?> to) {
		this.factory = factory;
		this.to = to;
	}

	@Override
	public Object convert(Object source) throws Exception {
		Class<?> from = source.getClass();
		IConverter converter = factory.getConverter(from, to);
		if (converter == null) {
			return null;
		}
		return converter.convert(source);
	}

}