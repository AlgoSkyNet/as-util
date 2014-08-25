package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class BooleanToNumber implements IConverter<Boolean, Number> {

	@Override
	public Number convert(Boolean value) {
		return value ? 1 : 0;
	}
	
}
