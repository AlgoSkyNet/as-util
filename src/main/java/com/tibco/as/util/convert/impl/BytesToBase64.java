package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.util.convert.IConverter;

public class BytesToBase64 implements IConverter {

	@Override
	public String convert(Object source) {
		return DatatypeConverter.printBase64Binary((byte[]) source);
	}

}
