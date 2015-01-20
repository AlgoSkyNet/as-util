package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public abstract class AbstractToBlob<S> extends AbstractConverter<S, byte[]> {

	int capacity;

	public AbstractToBlob(int capacity) {
		this.capacity = capacity;
	}

	@Override
	protected byte[] doConvert(S source) {
		return put(ByteBuffer.allocate(capacity), source).array();
	}

	protected abstract ByteBuffer put(ByteBuffer buffer, S value);

}
