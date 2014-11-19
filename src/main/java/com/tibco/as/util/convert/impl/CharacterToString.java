package com.tibco.as.util.convert.impl;

import com.tibco.as.util.convert.IConverter;

public class CharacterToString implements IConverter {

	@Override
	public String convert(Object value) {
		return String.valueOf(value);
	}
}
