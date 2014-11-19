package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class StringToDouble implements IConverter {

	@Override
	public Double convert(Object source) {
		return Double.valueOf((String) source);
	}

}
