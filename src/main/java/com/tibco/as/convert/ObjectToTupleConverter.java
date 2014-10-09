package com.tibco.as.convert;

import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.space.Tuple;

public abstract class ObjectToTupleConverter implements IConverter {

	private ITupleAccessor[] accessors;

	public ObjectToTupleConverter(ITupleAccessor[] accessors) {
		this.accessors = accessors;
	}

	@Override
	public Tuple convert(Object object) throws ConvertException {
		Tuple tuple = Tuple.create();
		for (int index = 0; index < accessors.length; index++) {
			ITupleAccessor accessor = accessors[index];
			if (accessor == null) {
				continue;
			}
			Object value = get(object, index);
			if (value == null) {
				continue;
			}
			accessor.set(tuple, value);
		}
		return tuple;
	}

	protected abstract Object get(Object object, int index)
			throws ConvertException;

}
