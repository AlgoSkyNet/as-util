package com.tibco.as.util.compare;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.space.Tuple;

public class TestTupleComparator {

	private static final String FIELD1 = "field1";
	private static final String FIELD2 = "field2";

	@Test
	public void testCompareTuples() {
		SpaceDef spaceDef = SpaceDef.create("TestSpace");
		spaceDef.getFieldDefs().add(FieldDef.create(FIELD1, FieldType.INTEGER));
		TupleComparator comparator = TupleComparator.create(spaceDef);
		Tuple tuple1 = Tuple.create();
		tuple1.putInt(FIELD1, 1);
		Tuple tuple2 = Tuple.create();
		tuple2.putInt(FIELD1, 2);
		Assert.assertTrue(comparator.compare(tuple1, tuple2) < 0);
		spaceDef.getFieldDefs().add(FieldDef.create(FIELD2, FieldType.STRING));
		comparator = TupleComparator.create(spaceDef);
		tuple1.putString(FIELD2, "bsdfsdf");
		tuple2.putInt(FIELD1, 1);
		tuple2.putString(FIELD2, "asdfsdfds");
		Assert.assertTrue(comparator.compare(tuple1, tuple2) > 0);
	}

	@Test
	public void testCompareBlobTuples() {
		SpaceDef spaceDef = SpaceDef.create("TestSpace");
		spaceDef.getFieldDefs().add(FieldDef.create(FIELD1, FieldType.BLOB));
		TupleComparator comparator = TupleComparator.create(spaceDef);
		Tuple tuple1 = Tuple.create();
		tuple1.putBlob(FIELD1, new byte[] { 1, 3 });
		Tuple tuple2 = Tuple.create();
		tuple2.putBlob(FIELD1, new byte[] { 1, 2 });
		Assert.assertTrue(comparator.compare(tuple1, tuple2) > 0);
	}

	@Test
	public void testSortTuples() {
		SpaceDef spaceDef = SpaceDef.create("TestSpace");
		spaceDef.getFieldDefs().add(FieldDef.create(FIELD1, FieldType.INTEGER));
		spaceDef.getFieldDefs().add(FieldDef.create(FIELD2, FieldType.STRING));
		Tuple tuple1 = Tuple.create();
		tuple1.putInt(FIELD1, 1);
		tuple1.putString(FIELD2, "abcdefg");
		Tuple tuple2 = Tuple.create();
		tuple2.putInt(FIELD1, 2);
		tuple2.putString(FIELD2, "abcdef");
		List<Tuple> tuples = new ArrayList<Tuple>();
		tuples.add(tuple1);
		tuples.add(tuple2);
		Collections.sort(tuples, TupleComparator.create(spaceDef, FIELD2));
		Assert.assertTrue(tuples.get(0) == tuple2);
	}
}
