package com.tibco.as.convert;

import java.lang.reflect.Array;
import java.util.Collection;

import com.tibco.as.convert.accessors.ITupleAccessor;
import com.tibco.as.space.Tuple;

public class TupleConverter<T> implements IConverter {

	private IConverter[] converters;
	private ITupleAccessor[] accessors;
	private Class<?> componentType;

	public TupleConverter(Collection<ITupleAccessor> accessors,
			Collection<IConverter> converters, Class<?> componentType) {
		this.accessors = accessors
				.toArray(new ITupleAccessor[accessors.size()]);
		this.converters = converters.toArray(new IConverter[converters.size()]);
		this.componentType = componentType;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object[] convert(Object object) throws Exception {
		if (object == null) {
			return null;
		}
		Tuple tuple = (Tuple) object;
		T[] result = (T[]) Array.newInstance(componentType, accessors.length);
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
			result[index] = (T) converter.convert(value);
		}
		return result;
	}
}
