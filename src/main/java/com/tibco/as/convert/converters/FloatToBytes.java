package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class FloatToBytes extends AbstractToBlob {

	public FloatToBytes() {
		super(Float.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Object value) {
		return buffer.putFloat((Float) value);
	}

}