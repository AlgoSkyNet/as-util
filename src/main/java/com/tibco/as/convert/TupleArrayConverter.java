package com.tibco.as.convert;

import java.util.Collection;

import com.tibco.as.convert.accessors.ITupleAccessor;
import com.tibco.as.space.Tuple;

public class TupleArrayConverter implements IConverter {

	private IConverter[] converters;
	private ITupleAccessor[] accessors;

	public TupleArrayConverter(Collection<ITupleAccessor> accessors,
			Collection<IConverter> converters) {
		this.accessors = accessors
				.toArray(new ITupleAccessor[accessors.size()]);
		this.converters = converters.toArray(new IConverter[converters.size()]);
	}

	@Override
	public Object[] convert(Object object) throws ConvertException {
		if (object == null) {
			return null;
		}
		Tuple tuple = (Tuple) object;
		Object[] result = new Object[accessors.length];
		for (int index = 0; index < accessors.length; index++) {
			ITupleAccessor accessor = accessors[index];
			if (accessor == null) {
				continue;
			}
			IConverter converter = converters[index];
			if (converter == null) {
				continue;
			}
			Object value = accessor.get(tuple);
			if (value == null) {
				continue;
			}
			result[index] = converter.convert(value);
		}
		return result;
	}
}
