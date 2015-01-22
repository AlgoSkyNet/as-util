package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToInteger extends AbstractBlobConverter<Integer> {

	@Override
	protected Integer convert(ByteBuffer buffer) {
		return buffer.getInt();
	}

}
