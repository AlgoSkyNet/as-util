package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class CharacterToNumber implements
		IConverter<Character, Number> {

	@Override
	public Number convert(Character value) {
		return Character.getNumericValue(value);
	}
	
}
