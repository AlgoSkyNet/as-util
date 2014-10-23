package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class StringToInteger implements IConverter {

	@Override
	public Integer convert(Object source) {
		return Integer.valueOf((String) source);
	}

}
