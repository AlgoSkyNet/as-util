package com.tibco.as.util.convert.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.tibco.as.util.convert.IConverter;

public class ObjectToBlob implements IConverter {

	@Override
	public byte[] convert(Object value) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(value);
		return baos.toByteArray();
	}

}