package com.tibco.as.util.convert.impl;

public class StringToCharacter extends AbstractStringParser<Character> {

	@Override
	protected Character parse(String value) {
		return value.length() == 0 ? null : value.charAt(0);
	}

}
