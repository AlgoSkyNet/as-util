package com.tibco.as.convert.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.IConverter;
import com.tibco.as.space.Tuple;

public class TupleToMapConverter implements IConverter {

	private Map<String, ITupleAccessor> accessors;
	private Map<String, IConverter> converters;

	public TupleToMapConverter(Map<String, ITupleAccessor> accessors,
			Map<String, IConverter> converters) {
		this.accessors = accessors;
		this.converters = converters;
	}

	@Override
	public Map<String, Object> convert(Object source) throws ConvertException {
		Tuple tuple = (Tuple) source;
		Map<String, Object> map = new HashMap<String, Object>();
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
			map.put(fieldName, converter.convert(value));
		}
		return map;
	}

}
