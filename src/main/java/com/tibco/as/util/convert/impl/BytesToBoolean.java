package com.tibco.as.util.convert.impl;

import java.nio.ByteBuffer;

public class BytesToBoolean extends AbstractBlobTo {

	@Override
	protected Boolean convert(ByteBuffer buffer) {
		return buffer.get() != 0;
	}

}
