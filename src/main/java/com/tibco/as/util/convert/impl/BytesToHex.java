package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.util.convert.IConverter;

public class BytesToHex implements IConverter {

	@Override
	public String convert(Object source) {
		return DatatypeConverter.printHexBinary((byte[]) source);
	}

}