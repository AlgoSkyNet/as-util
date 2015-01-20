package com.tibco.as.util.convert.impl;

import java.math.BigDecimal;

public class StringToBigDecimal extends AbstractStringParser<BigDecimal> {

	@Override
	protected BigDecimal parse(String string) {
		return new BigDecimal(string);
	}

}
