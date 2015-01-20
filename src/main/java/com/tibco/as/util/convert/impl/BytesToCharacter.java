package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToCharacter extends AbstractBlobConverter<Character> {

	@Override
	protected Character convert(ByteBuffer buffer) {
		return buffer.getChar();
	}

}
