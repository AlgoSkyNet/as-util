package com.tibco.as.util.convert.impl;

public class StringToByte extends AbstractConverter<String, Byte> {

	@Override
	protected Byte doConvert(String source) {
		return Byte.valueOf(source);
	}

}
