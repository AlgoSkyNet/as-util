package com.tibco.as.convert.converters;

import com.tibco.as.convert.IConverter;

public class CharacterToString implements IConverter<Character, String> {

	@Override
	public String convert(Character value) {
		return String.valueOf(value);
	}
}
