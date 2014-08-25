package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class LongToBytes extends AbstractToBlob<Long> {

	public LongToBytes() {
		super(Long.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Long value) {
		return buffer.putLong(value);
	}

}