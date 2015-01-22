package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class FloatToBytes extends AbstractToBlob<Float> {

	public FloatToBytes() {
		super(Float.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Float value) {
		return buffer.putFloat(value);
	}

}