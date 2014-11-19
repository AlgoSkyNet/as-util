package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.util.convert.IConverter;

public class Base64ToBytes implements IConverter {

	@Override
	public byte[] convert(Object source) {
		return DatatypeConverter.parseBase64Binary((String) source);
	}

}
