package com.tibco.as.convert;

import java.util.Collection;

import com.tibco.as.convert.accessors.ITupleAccessor;
import com.tibco.as.space.Tuple;

public class ArrayConverter<T> implements IConverter {

	private ITupleAccessor[] accessors;
	private IConverter[] converters;

	public ArrayConverter(Collection<ITupleAccessor> accessors,
			Collection<IConverter> converters) {
		this.accessors = accessors
				.toArray(new ITupleAccessor[accessors.size()]);
		this.converters = converters.toArray(new IConverter[converters.size()]);
	}

	@Override
	public Tuple convert(Object object) throws Exception {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) object;
		if (array == null) {
			return null;
		}
		Tuple tuple = Tuple.create();
		for (int index = 0; index < array.length; index++) {
			T value = array[index];
			if (value == null) {
				continue;
			}
			IConverter converter = converters[index];
			if (converter == null) {
				continue;
			}
			ITupleAccessor accessor = accessors[index];
			if (accessor == null) {
				continue;
			}
			Object converted = converter.convert(value);
			if (converted == null) {
				continue;
			}
			accessor.set(tuple, converted);
		}
		return tuple;
	}
}
