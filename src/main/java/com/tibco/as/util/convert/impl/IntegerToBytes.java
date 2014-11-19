package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class IntegerToBytes extends AbstractToBlob {

	public IntegerToBytes() {
		super(Integer.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Object value) {
		return buffer.putInt((Integer) value);
	}

}