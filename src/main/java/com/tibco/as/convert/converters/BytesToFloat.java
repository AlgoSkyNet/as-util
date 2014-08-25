package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class BytesToFloat extends AbstractBlobTo<Float> {

	@Override
	protected Float convert(ByteBuffer buffer) {
		return buffer.getFloat();
	}
	
}
