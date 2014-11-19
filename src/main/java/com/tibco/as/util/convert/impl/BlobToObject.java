package com.tibco.as.util.convert.impl;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.tibco.as.util.convert.IConverter;

public class BlobToObject implements IConverter {

	@Override
	public Object convert(Object value) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream((byte[]) value);
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}

}
