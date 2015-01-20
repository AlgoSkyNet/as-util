package com.tibco.as.util.convert.impl;

import java.math.BigInteger;

public class BytesToBigInteger extends AbstractConverter<byte[], BigInteger> {

	@Override
	protected BigInteger doConvert(byte[] source) {
		return new BigInteger(source);
	}
}
