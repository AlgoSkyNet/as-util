package com.tibco.as.util.convert;

public class ChainedConverter implements IConverter {

	private IConverter codec1;
	private IConverter codec2;

	public ChainedConverter(IConverter codec1, IConverter codec2) {
		this.codec1 = codec1;
		this.codec2 = codec2;
	}

	@Override
	public Object convert(Object value) {
		if (value == null) {
			return null;
		}
		Object value1 = codec1.convert(value);
		if (value1 == null) {
			return null;
		}
		return codec2.convert(value1);
	}

}
