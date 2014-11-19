package com.tibco.as.util.convert.impl;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.util.convert.IConverter;

public class HexToBytes implements IConverter {

	@Override
	public byte[] convert(Object source) {
		return DatatypeConverter.parseHexBinary((String) source);
	}
}
