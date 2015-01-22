package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToShort extends AbstractBlobConverter<Short> {

	@Override
	protected Short convert(ByteBuffer buffer) {
		return buffer.getShort();
	}

}
