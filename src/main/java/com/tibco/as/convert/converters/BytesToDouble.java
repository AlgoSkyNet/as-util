package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class BytesToDouble extends AbstractBlobTo {

	@Override
	protected Double convert(ByteBuffer buffer) {
		return buffer.getDouble();
	}
}
