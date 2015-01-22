package com.tibco.as.util.convert.impl;

import java.math.BigInteger;

public class BigIntegerToBytes extends AbstractConverter<BigInteger, byte[]> {

	@Override
	protected byte[] doConvert(BigInteger source) {
		return source.toByteArray();
	}

}
