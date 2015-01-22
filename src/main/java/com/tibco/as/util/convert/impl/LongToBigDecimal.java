package com.tibco.as.util.convert.impl;

import java.math.BigDecimal;

import com.tibco.as.util.convert.IConverter;

public class LongToBigDecimal implements IConverter {

	@Override
	public BigDecimal convert(Object value) {
		return BigDecimal.valueOf((Long) value);
	}

}
