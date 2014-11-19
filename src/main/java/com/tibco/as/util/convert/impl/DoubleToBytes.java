package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class DoubleToBytes extends AbstractToBlob {

	public DoubleToBytes() {
		super(Double.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Object value) {
		return buffer.putDouble((Double) value);
	}

}
