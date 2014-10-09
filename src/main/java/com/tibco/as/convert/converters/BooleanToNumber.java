package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class BooleanToNumber implements IConverter {

	@Override
	public Number convert(Object value) {
		return (Boolean) value ? 1 : 0;
	}

}
