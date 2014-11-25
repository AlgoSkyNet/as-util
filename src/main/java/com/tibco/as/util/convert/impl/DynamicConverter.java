package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.ConverterFactory;
import com.tibco.as.util.convert.IConverter;
import com.tibco.as.util.convert.Settings;

public class DynamicConverter implements IConverter {

	private ConverterFactory factory = new ConverterFactory();
	private Settings settings;
	private Class<?> to;

	public DynamicConverter(Settings settings, Class<?> to) {
		this.settings = settings;
		this.to = to;
	}

	@Override
	public Object convert(Object source) throws Exception {
		Class<?> from = source.getClass();
		IConverter converter = factory.getConverter(settings, from, to);
		if (converter == null) {
			return null;
		}
		return converter.convert(source);
	}

}