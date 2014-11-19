package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class CharacterToBytes extends AbstractToBlob {

	public CharacterToBytes() {
		super(Character.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Object value) {
		return buffer.putChar((Character) value);
	}

}
