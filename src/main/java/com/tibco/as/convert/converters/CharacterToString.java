package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class CharacterToString implements IConverter {

	@Override
	public String convert(Object value) {
		return String.valueOf(value);
	}
}
