package com.tibco.as.convert.map;

import java.util.Map;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;
import com.tibco.as.space.Tuple;

@SuppressWarnings("rawtypes")
public class MapToTupleConverter<T> implements
		IConverter<Map<String, T>, Tuple> {

	private Map<String, ITupleAccessor> accessors;
	private Map<String, IConverter> converters;

	public MapToTupleConverter(Map<String, ITupleAccessor> accessors,
			Map<String, IConverter> converters) {
		this.accessors = accessors;
		this.converters = converters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Tuple convert(Map<String, T> map) throws ConvertException {
		Tuple tuple = Tuple.create();
		for (String fieldName : accessors.keySet()) {
			T value = map.get(fieldName);
			if (value == null) {
				continue;
			}
			IConverter converter = converters.get(fieldName);
			Object converted = converter.convert(value);
			if (converted == null) {
				continue;
			}
			ITupleAccessor accessor = accessors.get(fieldName);
			accessor.set(tuple, converted);
		}
		return tuple;
	}

}
