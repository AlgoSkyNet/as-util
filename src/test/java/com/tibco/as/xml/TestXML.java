package com.tibco.as.xml;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.UnsupportedConversionException;
import com.tibco.as.space.DateTime;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.SpaceDef;

public class TestXML {

	protected static final String SPACE_NAME = "MySpace";

	protected static final String FIELD_NAME1 = "field1";

	protected static final String FIELD_NAME2 = "field2";

	protected static final String FIELD_NAME3 = "field3";

	protected static final String FIELD_NAME4 = "field4";

	protected static final String FIELD_NAME5 = "field5";

	protected static final String FIELD_NAME6 = "field6";

	protected static final String FIELD_NAME7 = "field7";

	protected static final String FIELD_NAME8 = "field8";

	protected static final String FIELD_NAME9 = "field9";

	protected static final String FIELD_NAME10 = "field10";

	protected static final FieldDef FIELD1 = FieldDef.create(FIELD_NAME1,
			FieldType.LONG).setNullable(false);
	protected static final FieldDef FIELD2 = FieldDef.create(FIELD_NAME2,
			FieldType.STRING).setNullable(true);
	protected static final FieldDef FIELD3 = FieldDef.create(FIELD_NAME3,
			FieldType.DATETIME).setNullable(true);
	protected static final FieldDef FIELD4 = FieldDef.create(FIELD_NAME4,
			FieldType.BLOB).setNullable(true);
	protected static final FieldDef FIELD5 = FieldDef.create(FIELD_NAME5,
			FieldType.BOOLEAN).setNullable(true);
	protected static final FieldDef FIELD6 = FieldDef.create(FIELD_NAME6,
			FieldType.CHAR).setNullable(true);
	protected static final FieldDef FIELD7 = FieldDef.create(FIELD_NAME7,
			FieldType.DOUBLE).setNullable(true);
	protected static final FieldDef FIELD8 = FieldDef.create(FIELD_NAME8,
			FieldType.FLOAT).setNullable(true);
	protected static final FieldDef FIELD9 = FieldDef.create(FIELD_NAME9,
			FieldType.INTEGER).setNullable(true);
	protected static final FieldDef FIELD10 = FieldDef.create(FIELD_NAME10,
			FieldType.SHORT).setNullable(true);

	private final static byte[] BLOB = { -128, 0, 2, 3, 127 };

	private final static Date DATE = DatatypeConverter.parseDateTime(
			"2001-07-04T12:08:56.235-07:00").getTime();

	private static final DateTime DATETIME = DateTime.create(DATE.getTime());

	private static final Character CHARACTER = 'c';

	private static final Long LONG = 12345l;

	private static final String STRING = "12345";

	private static final Boolean BOOLEAN = true;

	private static final Double DOUBLE = 12345.12345;

	private static final Float FLOAT = 12.12345f;

	private static final Integer INTEGER = 12345;

	private static final Short SHORT = 12345;

	@Test
	public void testUnmarshallSpace() throws JAXBException {
		@SuppressWarnings("unchecked")
		JAXBElement<Space> element = (JAXBElement<Space>) XMLFactory
				.unmarshall(ClassLoader.getSystemResourceAsStream("space1.xml"));
		Space space1 = element.getValue();
		assertEquals("space1", space1.getName());
		List<Field> fields = space1.getFields();
		assertEquals(2, fields.size());
		assertEquals("field1", fields.get(0).getName());
		assertEquals("field2", fields.get(1).getName());
	}

	@Test
	public void testMarshallTuple() throws JAXBException, IOException,
			SAXException, ParseException, ParserConfigurationException,
			ConvertException, UnsupportedConversionException {
		com.tibco.as.space.Tuple tuple = createTuple();
		Tuple xmlTuple = XMLFactory.getTuple(tuple, getSpaceDef());
		Document doc = XMLFactory.marshallToDocument(xmlTuple);
		Element element = doc.getDocumentElement();
		element.normalize();
		Assert.assertEquals("tuple", element.getTagName());
		NodeList children = element.getChildNodes();
		for (int index = 0; index < children.getLength(); index++) {
			Node child = children.item(index);
			String value = child.getFirstChild().getNodeValue();
			String name = child.getNodeName();
			if (name.equals(FIELD_NAME1))
				Assert.assertEquals((long) LONG, Long.parseLong(value));
			else if (name.equals(FIELD_NAME2))
				Assert.assertEquals(STRING, value);
			else if (name.equals(FIELD_NAME3))
				Assert.assertEquals(DATE, DatatypeConverter
						.parseDateTime(value).getTime());
			else if (name.equals(FIELD_NAME4)) {
				Assert.assertArrayEquals(BLOB,
						DatatypeConverter.parseBase64Binary(value));
			}
		}
	}

	private com.tibco.as.space.Tuple createTuple() {
		com.tibco.as.space.Tuple tuple = com.tibco.as.space.Tuple.create();
		tuple.putLong(FIELD_NAME1, LONG);
		tuple.putString(FIELD_NAME2, STRING);
		tuple.putDateTime(FIELD_NAME3, DATETIME);
		tuple.putBlob(FIELD_NAME4, BLOB);
		tuple.putBoolean(FIELD_NAME5, BOOLEAN);
		tuple.putChar(FIELD_NAME6, CHARACTER);
		tuple.putDouble(FIELD_NAME7, DOUBLE);
		tuple.putFloat(FIELD_NAME8, FLOAT);
		tuple.putInt(FIELD_NAME9, INTEGER);
		tuple.putShort(FIELD_NAME10, SHORT);
		return tuple;
	}

	@Test
	public void testUnmarshallTuple() throws JAXBException, IOException,
			SAXException, ParseException, UnsupportedConversionException,
			ConvertException {
		Tuple xmlTuple = (Tuple) XMLFactory.unmarshall(ClassLoader
				.getSystemResourceAsStream("tuple.xml"));
		com.tibco.as.space.Tuple tuple = XMLFactory.getTuple(xmlTuple,
				getSpaceDef());
		assertEquals(tuple.getLong(FIELD_NAME1), LONG);
		assertEquals(tuple.getString(FIELD_NAME2), STRING);
		assertEquals(tuple.getDateTime(FIELD_NAME3), DATETIME);
		Assert.assertArrayEquals(tuple.getBlob(FIELD_NAME4), BLOB);
		assertEquals(tuple.getBoolean(FIELD_NAME5), BOOLEAN);
		assertEquals(tuple.getChar(FIELD_NAME6), CHARACTER);
		assertEquals(tuple.getDouble(FIELD_NAME7), DOUBLE);
		assertEquals(tuple.getFloat(FIELD_NAME8), FLOAT);
		assertEquals(tuple.getInt(FIELD_NAME9), INTEGER);
		assertEquals(tuple.getShort(FIELD_NAME10), SHORT);
	}

	@Test
	public void testMarshallSpace() throws Exception {
		Space space1 = new Space();
		space1.setName("space1");
		Field field1 = new Field();
		field1.setName("field1");
		field1.setKey(true);
		field1.setType(FieldType.LONG);
		field1.setDistribution(true);
		Field field2 = new Field();
		field2.setName("field2");
		field2.setType(FieldType.STRING);
		space1.getFields().add(field1);
		space1.getFields().add(field2);
		Index index1 = new Index();
		index1.setName("index1");
		index1.getFields().add("field1");
		Index index2 = new Index();
		index2.setName("index2");
		index2.getFields().add("field2");
		space1.getIndexes().add(index1);
		space1.getIndexes().add(index2);
		JAXBElement<Space> xmlSpace = new ObjectFactory().createSpace(space1);
		Document actual = XMLFactory.marshallToDocument(xmlSpace);
		actual.normalize();
		Document expected = XMLFactory.parse(ClassLoader
				.getSystemResourceAsStream("space1.xml"));
		expected.normalize();
		XMLUnit.setIgnoreAttributeOrder(true);
		XMLUnit.setIgnoreComments(true);
		XMLUnit.setIgnoreWhitespace(true);
		DetailedDiff myDiff = new DetailedDiff(XMLUnit.compareXML(expected,
				actual));
		assertEquals(myDiff.toString(), 0, myDiff.getAllDifferences().size());
	}

	protected SpaceDef getSpaceDef() {
		SpaceDef spaceDef = SpaceDef.create(SPACE_NAME, 0, Arrays.asList(
				FIELD1, FIELD2, FIELD3, FIELD4, FIELD5, FIELD6, FIELD7, FIELD8,
				FIELD9, FIELD10));
		spaceDef.setKey(FIELD1.getName());
		return spaceDef;
	}
}
