package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class BytesToCharacter extends AbstractBlobTo {

	@Override
	protected Character convert(ByteBuffer buffer) {
		return buffer.getChar();
	}

}
