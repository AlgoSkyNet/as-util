package com.tibco.as.util.convert.impl;

import java.math.BigInteger;

import com.tibco.as.util.convert.IConverter;

public class BigIntegerToBytes implements IConverter {

	@Override
	public byte[] convert(Object value) {
		return ((BigInteger) value).toByteArray();
	}

}
