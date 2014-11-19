package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public abstract class AbstractStringParser implements IConverter {

	@Override
	public Object convert(Object object) throws Exception {
		String string = (String) object;
		if (string.isEmpty()) {
			return null;
		}
		return parse(string);
	}

	protected abstract Object parse(String string) throws Exception;

}
