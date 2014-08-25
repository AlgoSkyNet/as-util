package com.tibco.as.convert.converters;

import java.math.BigInteger;

import com.tibco.as.convert.IConverter;

public class LongToBigInteger implements IConverter<Long, BigInteger> {

	@Override
	public BigInteger convert(Long value) {
		return BigInteger.valueOf(value);
	}
	
}
