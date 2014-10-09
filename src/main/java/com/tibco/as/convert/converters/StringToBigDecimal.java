package com.tibco.as.convert.converters;

import java.math.BigDecimal;

public class StringToBigDecimal extends AbstractStringParser {

	@Override
	protected BigDecimal parse(String string) {
		return new BigDecimal(string);
	}

}
