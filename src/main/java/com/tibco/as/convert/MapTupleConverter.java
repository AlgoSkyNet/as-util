package com.tibco.as.convert;

import java.util.Map;

import com.tibco.as.convert.accessors.ITupleAccessor;
import com.tibco.as.space.Tuple;

public class MapTupleConverter implements IConverter {

	private Map<String, ITupleAccessor> accessors;
	private Map<String, IConverter> converters;

	public MapTupleConverter(Map<String, ITupleAccessor> accessors,
			Map<String, IConverter> converters) {
		this.accessors = accessors;
		this.converters = converters;
	}

	@Override
	public Tuple convert(Object source) throws ConvertException {
		Tuple tuple = Tuple.create();
		Map<?, ?> map = (Map<?, ?>) source;
		for (String fieldName : accessors.keySet()) {
			Object value = map.get(fieldName);
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
