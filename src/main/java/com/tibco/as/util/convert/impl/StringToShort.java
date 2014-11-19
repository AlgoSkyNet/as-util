package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class StringToShort implements IConverter {

	@Override
	public Short convert(Object source) {
		return Short.valueOf((String) source);
	}

}
