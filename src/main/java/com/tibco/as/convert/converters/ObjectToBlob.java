package com.tibco.as.convert.converters;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;

public class ObjectToBlob implements IConverter<Object, byte[]> {

	@Override
	public byte[] convert(Object value) throws ConvertException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			oos.writeObject(value);
			return baos.toByteArray();
		} catch (IOException e) {
			throw new ConvertException(e, value);
		}
	}
	
}
