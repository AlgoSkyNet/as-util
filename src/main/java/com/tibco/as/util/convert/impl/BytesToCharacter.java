package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToCharacter extends AbstractBlobTo {

	@Override
	protected Character convert(ByteBuffer buffer) {
		return buffer.getChar();
	}

}
