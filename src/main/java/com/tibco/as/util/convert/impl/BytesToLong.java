package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToLong extends AbstractBlobConverter<Long> {

	@Override
	protected Long convert(ByteBuffer buffer) {
		return buffer.getLong();
	}
}
