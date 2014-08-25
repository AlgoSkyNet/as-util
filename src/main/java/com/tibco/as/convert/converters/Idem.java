package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

@SuppressWarnings("rawtypes")
public class Idem implements IConverter {

	@Override
	public Object convert(Object source) {
		return source;
	}

}
