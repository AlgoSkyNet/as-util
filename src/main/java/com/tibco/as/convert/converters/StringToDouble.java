package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class StringToDouble implements IConverter {

	@Override
	public Double convert(Object source) {
		return Double.valueOf((String) source);
	}

}
