package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

public class BytesToHex extends AbstractConverter<byte[], String> {

	@Override
	protected String doConvert(byte[] source) {
		return DatatypeConverter.printHexBinary(source);
	}

}