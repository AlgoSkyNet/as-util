package com.tibco.as.util.convert.impl;

public class StringToByte extends AbstractStringParser<Byte> {

	@Override
	protected Byte parse(String string) {
		return Byte.valueOf(string);
	}

}
