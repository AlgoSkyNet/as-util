package com.tibco.as.convert.converters;

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