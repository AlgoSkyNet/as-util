package com.tibco.as.convert.converters;

import java.math.BigInteger;

import com.tibco.as.convert.IConverter;

public class BytesToBigInteger implements IConverter {

	@Override
	public BigInteger convert(Object value) {
		return new BigInteger((byte[]) value);
	}
}
