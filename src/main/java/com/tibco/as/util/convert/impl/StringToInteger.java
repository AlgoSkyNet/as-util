package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class StringToInteger implements IConverter {

	@Override
	public Integer convert(Object source) {
		return Integer.valueOf((String) source);
	}

}
