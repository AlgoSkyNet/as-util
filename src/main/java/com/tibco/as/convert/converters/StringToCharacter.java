package com.tibco.as.convert.converters;

public class StringToCharacter extends AbstractStringParser {

	@Override
	protected Character parse(String value) {
		return value.length() == 0 ? null : value.charAt(0);
	}

}
