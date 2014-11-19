package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToInteger extends AbstractBlobTo {

	@Override
	protected Integer convert(ByteBuffer buffer) {
		return buffer.getInt();
	}

}
