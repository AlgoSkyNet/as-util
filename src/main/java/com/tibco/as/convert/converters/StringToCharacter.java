package com.tibco.as.convert.converters;

import com.tibco.as.convert.ConvertException;

public class StringToCharacter extends AbstractStringParser<Character> {

	@Override
	protected Character parse(String value) throws ConvertException {
		return value.length() == 0 ? null : value.charAt(0);
	}

}
