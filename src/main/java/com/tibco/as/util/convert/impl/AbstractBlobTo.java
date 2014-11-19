package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

import com.tibco.as.util.convert.IConverter;

public abstract class AbstractBlobTo implements IConverter {

	@Override
	public Object convert(Object value) {
		return convert(ByteBuffer.wrap((byte[]) value));
	}

	protected abstract Object convert(ByteBuffer buffer);

}
