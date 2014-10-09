package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class LongToBytes extends AbstractToBlob {

	public LongToBytes() {
		super(Long.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Object value) {
		return buffer.putLong((Long) value);
	}

}