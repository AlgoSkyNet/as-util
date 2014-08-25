package com.tibco.as.convert.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;
import com.tibco.as.space.Tuple;

@SuppressWarnings("rawtypes")
public class TupleToMapConverter<T> implements
		IConverter<Tuple, Map<String, T>> {

	private Map<String, ITupleAccessor> accessors;
	private Map<String, IConverter> converters;

	public TupleToMapConverter(Map<String, ITupleAccessor> accessors,
			Map<String, IConverter> converters) {
		this.accessors = accessors;
		this.converters = converters;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, T> convert(Tuple tuple) throws ConvertException {
		Map<String, T> map = new HashMap<String, T>();
		for (Entry<String, ITupleAccessor> entry : accessors.entrySet()) {
			Object value = entry.getValue().get(tuple);
			if (value == null) {
				continue;
			}
			String fieldName = entry.getKey();
			IConverter converter = converters.get(fieldName);
			if (converter == null) {
				continue;
			}
			map.put(fieldName, (T) converter.convert(value));
		}
		return map;
	}

}
