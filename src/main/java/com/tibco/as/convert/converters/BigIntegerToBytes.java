package com.tibco.as.convert.converters;

import java.math.BigInteger;

import com.tibco.as.convert.IConverter;

public class BigIntegerToBytes implements IConverter {

	@Override
	public byte[] convert(Object value) {
		return ((BigInteger) value).toByteArray();
	}

}
