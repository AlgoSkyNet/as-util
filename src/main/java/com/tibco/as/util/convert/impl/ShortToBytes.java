package com.tibco.as.util.convert.impl;

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