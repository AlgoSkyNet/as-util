package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class Idem implements IConverter {

	@Override
	public Object convert(Object source) {
		return source;
	}

}
