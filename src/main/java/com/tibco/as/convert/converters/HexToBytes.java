package com.tibco.as.convert.converters;

import javax.xml.bind.DatatypeConverter;

import com.tibco.as.convert.IConverter;

public class HexToBytes implements IConverter {

	@Override
	public byte[] convert(Object source) {
		return DatatypeConverter.parseHexBinary((String) source);
	}
}
