package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class ShortToBytes extends AbstractToBlob<Short> {

	public ShortToBytes() {
		super(Short.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Short value) {
		return buffer.putShort(value);
	}

}