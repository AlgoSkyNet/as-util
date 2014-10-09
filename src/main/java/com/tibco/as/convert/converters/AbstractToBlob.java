package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

import com.tibco.as.convert.IConverter;

public abstract class AbstractToBlob implements IConverter {

	int capacity;

	public AbstractToBlob(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public byte[] convert(Object value) {
		return put(ByteBuffer.allocate(capacity), value).array();
	}

	protected abstract ByteBuffer put(ByteBuffer buffer, Object value);

}
