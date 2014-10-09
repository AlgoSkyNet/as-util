package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class BytesToShort extends AbstractBlobTo {

	@Override
	protected Short convert(ByteBuffer buffer) {
		return buffer.getShort();
	}

}
