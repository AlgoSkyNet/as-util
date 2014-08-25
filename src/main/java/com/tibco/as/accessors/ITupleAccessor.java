package com.tibco.as.accessors;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Tuple;

public interface ITupleAccessor extends IAccessor<Tuple> {

	FieldDef getFieldDef();

}
