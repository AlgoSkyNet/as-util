package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class CharacterToBytes extends
		AbstractToBlob<Character> {

	public CharacterToBytes() {
		super(Character.SIZE);
	}

	@Override
	protected ByteBuffer put(ByteBuffer buffer, Character value) {
		return buffer.putChar(value);
	}
	
}
