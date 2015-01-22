package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

public class Base64ToBytes extends AbstractConverter<String, byte[]> {

	@Override
	protected byte[] doConvert(String source) {
		return DatatypeConverter.parseBase64Binary(source);
	}

}
