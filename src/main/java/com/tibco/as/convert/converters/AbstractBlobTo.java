package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

import com.tibco.as.convert.IConverter;

public abstract class AbstractBlobTo implements IConverter {

	@Override
	public Object convert(Object value) {
		return convert(ByteBuffer.wrap((byte[]) value));
	}

	protected abstract Object convert(ByteBuffer buffer);

}
