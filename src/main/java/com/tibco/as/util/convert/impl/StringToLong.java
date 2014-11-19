package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class StringToLong implements IConverter {

	@Override
	public Long convert(Object source) {
		return Long.valueOf((String) source);
	}

}
