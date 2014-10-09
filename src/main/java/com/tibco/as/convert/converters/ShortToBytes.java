package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class ShortToBytes extends AbstractToBlob {

	public ShortToBytes() {
		super(Short.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Object value) {
		return buffer.putShort((Short) value);
	}

}