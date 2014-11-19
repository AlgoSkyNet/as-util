package com.tibco.as.util.convert.impl;

import java.math.BigInteger;

import com.tibco.as.util.convert.IConverter;

public class BytesToBigInteger implements IConverter {

	@Override
	public BigInteger convert(Object value) {
		return new BigInteger((byte[]) value);
	}
}
