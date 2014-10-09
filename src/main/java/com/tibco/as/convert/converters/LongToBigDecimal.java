package com.tibco.as.convert.converters;

import java.math.BigDecimal;

import com.tibco.as.convert.IConverter;

public class LongToBigDecimal implements IConverter {

	@Override
	public BigDecimal convert(Object value) {
		return BigDecimal.valueOf((Long) value);
	}

}
