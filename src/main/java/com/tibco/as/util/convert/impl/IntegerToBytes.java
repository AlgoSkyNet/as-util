package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class IntegerToBytes extends AbstractToBlob<Integer> {

	public IntegerToBytes() {
		super(Integer.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Integer value) {
		return buffer.putInt(value);
	}

}