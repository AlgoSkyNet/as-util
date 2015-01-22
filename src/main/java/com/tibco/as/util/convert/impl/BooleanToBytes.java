package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BooleanToBytes extends AbstractToBlob<Boolean> {

	public BooleanToBytes() {
		super(1);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Boolean value) {
		return buffer.put((byte) (value ? 1 : 0));
	}

}
