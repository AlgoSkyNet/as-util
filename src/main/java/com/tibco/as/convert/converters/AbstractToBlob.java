package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

import com.tibco.as.convert.IConverter;

public abstract class AbstractToBlob<T> implements IConverter<T, byte[]> {

	int capacity;

	public AbstractToBlob(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public byte[] convert(T value) {
		return put(ByteBuffer.allocate(capacity), value).array();
	}

	protected abstract ByteBuffer put(ByteBuffer buffer, T value);

}
