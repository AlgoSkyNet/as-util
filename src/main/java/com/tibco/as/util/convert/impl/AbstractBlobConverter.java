package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public abstract class AbstractBlobConverter<T> extends AbstractConverter<byte[], T> {

	@Override
	protected T doConvert(byte[] source) {
		return convert(ByteBuffer.wrap(source));
	}

	protected abstract T convert(ByteBuffer buffer);

}
