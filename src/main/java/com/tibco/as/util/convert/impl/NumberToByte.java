package com.tibco.as.util.convert.impl;

public class NumberToByte extends AbstractConverter<Number, Byte> {

	@Override
	protected Byte doConvert(Number source) {
		return source.byteValue();
	}

}
