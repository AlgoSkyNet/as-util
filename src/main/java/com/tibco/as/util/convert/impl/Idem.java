package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class Idem implements IConverter {

	@Override
	public Object convert(Object source) {
		return source;
	}

}
