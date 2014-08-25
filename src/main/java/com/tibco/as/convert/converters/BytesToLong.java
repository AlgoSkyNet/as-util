package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class BytesToLong extends AbstractBlobTo<Long> {

	@Override
	protected Long convert(ByteBuffer buffer) {
		return buffer.getLong();
	}
}
