package com.tibco.as.convert.format;

import javax.xml.bind.DatatypeConverter;

public class Base64Format extends BytesFormat {

	private static final long serialVersionUID = -5241322851358589993L;

	@Override
	protected String format(byte[] bytes) {
		return DatatypeConverter.printBase64Binary(bytes);
	}

	@Override
	protected byte[] parse(String string) {
		return DatatypeConverter.parseBase64Binary(string);
	}
}
