package com.tibco.as.util.convert.impl;

import java.math.BigInteger;

import com.tibco.as.util.convert.IConverter;

public class LongToBigInteger implements IConverter {

	@Override
	public BigInteger convert(Object value) {
		return BigInteger.valueOf((Long) value);
	}
}
