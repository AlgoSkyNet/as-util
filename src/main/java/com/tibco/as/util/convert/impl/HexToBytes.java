package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

public class HexToBytes extends AbstractConverter<String, byte[]> {

	@Override
	protected byte[] doConvert(String source) {
		return DatatypeConverter.parseHexBinary(source);
	}
}
