package com.tibco.as.convert.converters;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.tibco.as.convert.IConverter;

public class BlobToObject implements IConverter {

	@Override
	public Object convert(Object value) throws Exception {
		ByteArrayInputStream bais = new ByteArrayInputStream((byte[]) value);
		ObjectInputStream ois = new ObjectInputStream(bais);
		return ois.readObject();
	}

}
