package com.tibco.as.convert.converters;

import java.math.BigInteger;

import com.tibco.as.convert.IConverter;

public class BytesToBigInteger implements IConverter<byte[], BigInteger> {

	@Override
	public BigInteger convert(byte[] value) {
		return new BigInteger(value);
	}
}
