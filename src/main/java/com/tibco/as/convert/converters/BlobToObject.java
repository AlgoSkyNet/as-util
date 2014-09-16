package com.tibco.as.convert.converters;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class BlobToObject implements IConverter<byte[], Object> {

	@Override
	public Object convert(byte[] value) throws ConvertException {
		try {
			ObjectInputStream ois = new ObjectInputStream(
					new ByteArrayInputStream((byte[]) value));
			return ois.readObject();
		} catch (Exception e) {
			throw new ConvertException(e, value);
		}
	}

}
