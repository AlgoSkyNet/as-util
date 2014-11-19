package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class BooleanToNumber implements IConverter {

	@Override
	public Number convert(Object value) {
		return (Boolean) value ? 1 : 0;
	}

}
