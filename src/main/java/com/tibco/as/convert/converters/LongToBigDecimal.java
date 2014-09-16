package com.tibco.as.convert.converters;

import java.math.BigDecimal;

import com.tibco.as.convert.IConverter;

public class LongToBigDecimal implements IConverter<Long, BigDecimal> {

	@Override
	public BigDecimal convert(Long value) {
		return BigDecimal.valueOf(value);
	}

}
