package com.tibco.as.convert.converters;

import java.nio.ByteBuffer;

public class BytesToBoolean extends AbstractBlobTo {

	@Override
	protected Boolean convert(ByteBuffer buffer) {
		return buffer.get() != 0;
	}

}
