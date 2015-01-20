package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToDouble extends AbstractBlobConverter<Double> {

	@Override
	protected Double convert(ByteBuffer buffer) {
		return buffer.getDouble();
	}
}
