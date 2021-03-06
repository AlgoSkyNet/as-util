package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class DoubleToBytes extends AbstractToBlob<Double> {

	public DoubleToBytes() {
		super(Double.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Double value) {
		return buffer.putDouble(value);
	}

}
