package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

public class BytesToBase64 extends AbstractConverter<byte[], String> {

	@Override
	protected String doConvert(byte[] source) {
		return DatatypeConverter.printBase64Binary(source);
	}

}
