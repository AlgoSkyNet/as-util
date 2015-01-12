package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BooleanToBytes extends AbstractToBlob {

	public BooleanToBytes() {
		super(1);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Object value) {
		return buffer.put((byte) ((Boolean) value ? 1 : 0));
	}

}