package com.tibco.as.convert;

public class ChainedConverter<S, P, T> implements IConverter<S, T> {

	private IConverter<S, P> codec1;
	private IConverter<P, T> codec2;

	public ChainedConverter(IConverter<S, P> codec1, IConverter<P, T> codec2) {
		this.codec1 = codec1;
		this.codec2 = codec2;
	}

	@Override
	public T convert(S value) throws ConvertException {
		if (value == null) {
			return null;
		}
		P value1 = codec1.convert(value);
		if (value1 == null) {
			return null;
		}
		return codec2.convert(value1);
	}

}
