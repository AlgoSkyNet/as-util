package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class BytesToInteger extends AbstractBlobTo<Integer> {

	@Override
	protected Integer convert(ByteBuffer buffer) {
		return buffer.getInt();
	}

}
