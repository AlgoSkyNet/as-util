package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToFloat extends AbstractBlobConverter<Float> {

	@Override
	protected Float convert(ByteBuffer buffer) {
		return buffer.getFloat();
	}

}
