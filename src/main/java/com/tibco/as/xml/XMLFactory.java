package com.tibco.as.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.tibco.as.accessors.AccessorFactory;
import com.tibco.as.accessors.ITupleAccessor;
import com.tibco.as.convert.ConvertException;
import com.tibco.as.convert.ConverterFactory;
import com.tibco.as.convert.ConverterFactory.Blob;
import com.tibco.as.convert.IConverter;
import com.tibco.as.convert.UnsupportedConversionException;
import com.tibco.as.convert.map.MapToTupleConverter;
import com.tibco.as.convert.map.TupleToMapConverter;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.IndexDef;
import com.tibco.as.space.KeyDef;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.SpaceDef;
import com.tibco.as.util.Utils;

public class XMLFactory {

	private static ConverterFactory xmlCF = new ConverterFactory();
	static {
		xmlCF.setBlobFormat(Blob.BASE64);
	}

	private static ConverterFactory jsonCF = new ConverterFactory();
	static {
		jsonCF.setBlobFormat(Blob.BASE64);
	}

	private static JAXBContext getContext() throws JAXBException {
		return JAXBContext.newInstance(ObjectFactory.class, Tuples.class, Tuple.class);
	}

	private static Marshaller getMarshaller() throws JAXBException {
		Marshaller marshaller = getContext().createMarshaller();
		marshaller.setEventHandler(new JAXBValidator());
		return marshaller;
	}

	private static Unmarshaller getUnmarshaller() throws JAXBException {
		Unmarshaller unmarshaller = getContext().createUnmarshaller();
		unmarshaller.setEventHandler(new JAXBValidator());
		return unmarshaller;
	}

	public static Object unmarshall(File file) throws JAXBException {
		return getUnmarshaller().unmarshal(file);
	}

	public static Object unmarshall(String string) throws JAXBException {
		return getUnmarshaller().unmarshal(new StringReader(string));
	}

	public static Object unmarshall(InputStream in) throws JAXBException {
		return getUnmarshaller().unmarshal(in);
	}

	public static String marshallToString(Object element) throws JAXBException {
		StringWriter writer = new StringWriter();
		getMarshaller().marshal(element, writer);
		return writer.toString();
	}

	public static Document marshallToDocument(Object element)
			throws JAXBException, ParserConfigurationException {
		Document document = getDocumentBuilder().newDocument();
		getMarshaller().marshal(element, document);
		return document;
	}

	public static Document parse(InputStream is) throws SAXException,
			IOException, ParserConfigurationException {
		return getDocumentBuilder().parse(is);
	}

	public static String toString(Document document)
			throws TransformerException {
		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer = tf.newTransformer();
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		StringWriter writer = new StringWriter();
		transformer
				.transform(new DOMSource(document), new StreamResult(writer));
		return writer.toString();
	}

	private static DocumentBuilder getDocumentBuilder()
			throws ParserConfigurationException {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		return dbf.newDocumentBuilder();
	}

	public static <T> void marshal(JAXBElement<?> element, File file)
			throws JAXBException {
		getMarshaller().marshal(element, file);
	}

	public static com.tibco.as.space.Tuple getTuple(Tuple xmlTuple,
			SpaceDef spaceDef) throws UnsupportedConversionException,
			ConvertException {
		return getTuple(xmlTuple.getMap(), spaceDef);
	}

	public static com.tibco.as.space.Tuple getTuple(Map<String, String> map,
			SpaceDef spaceDef) throws UnsupportedConversionException,
			ConvertException {
		return getMapToTupleConverter(spaceDef).convert(map);
	}

	public static com.tibco.as.space.Tuple getTupleJSON(
			Map<String, Object> map, SpaceDef spaceDef)
			throws UnsupportedConversionException, ConvertException {
		return getJSONMapToTupleConverter(map, spaceDef).convert(map);
	}

	public static Tuple getTuple(com.tibco.as.space.Tuple tuple,
			SpaceDef spaceDef) throws ConvertException,
			UnsupportedConversionException {
		return new Tuple(getMap(tuple, spaceDef));
	}

	public static Map<String, String> getMap(com.tibco.as.space.Tuple tuple,
			SpaceDef spaceDef) throws UnsupportedConversionException,
			ConvertException {
		return getTupleToMapConverter(spaceDef).convert(tuple);
	}

	@SuppressWarnings("rawtypes")
	public static MapToTupleConverter<String> getMapToTupleConverter(
			SpaceDef spaceDef) throws UnsupportedConversionException {
		Map<String, ITupleAccessor> accessors = getAccessors(spaceDef);
		Map<String, IConverter> converters = new HashMap<String, IConverter>();
		for (FieldDef fieldDef : spaceDef.getFieldDefs()) {
			IConverter converter = xmlCF.getConverter(String.class, fieldDef);
			converters.put(fieldDef.getName(), converter);
		}
		return new MapToTupleConverter<String>(accessors, converters);
	}

	@SuppressWarnings("rawtypes")
	public static MapToTupleConverter<Object> getJSONMapToTupleConverter(
			Map<String, Object> map, SpaceDef spaceDef)
			throws UnsupportedConversionException {
		Map<String, ITupleAccessor> accessors = getAccessors(spaceDef);
		Map<String, IConverter> converters = getJSONConverters(map, spaceDef);
		return new MapToTupleConverter<Object>(accessors, converters);
	}

	@SuppressWarnings("rawtypes")
	public static TupleToMapConverter<String> getTupleToMapConverter(
			SpaceDef spaceDef) throws UnsupportedConversionException {
		Map<String, ITupleAccessor> accessors = getAccessors(spaceDef);
		Map<String, IConverter> converters = new HashMap<String, IConverter>();
		for (FieldDef fieldDef : spaceDef.getFieldDefs()) {
			IConverter converter = xmlCF.getConverter(fieldDef, String.class);
			converters.put(fieldDef.getName(), converter);
		}
		return new TupleToMapConverter<String>(accessors, converters);
	}

	private static Map<String, ITupleAccessor> getAccessors(SpaceDef spaceDef) {
		Map<String, ITupleAccessor> accessors = new HashMap<String, ITupleAccessor>();
		for (FieldDef fieldDef : spaceDef.getFieldDefs()) {
			accessors.put(fieldDef.getName(), AccessorFactory.create(fieldDef));
		}
		return accessors;
	}

	@SuppressWarnings("rawtypes")
	private static Map<String, IConverter> getJSONConverters(
			Map<String, Object> map, SpaceDef spaceDef)
			throws UnsupportedConversionException {
		return getConverters(map, spaceDef, jsonCF);
	}

	@SuppressWarnings("rawtypes")
	private static Map<String, IConverter> getConverters(
			Map<String, Object> map, SpaceDef spaceDef, ConverterFactory factory)
			throws UnsupportedConversionException {
		Map<String, IConverter> converters = new HashMap<String, IConverter>();
		for (Entry<String, Object> entry : map.entrySet()) {
			String fieldName = entry.getKey();
			FieldDef fieldDef = spaceDef.getFieldDef(fieldName);
			IConverter converter = factory.getConverter(entry.getValue()
					.getClass(), fieldDef);
			converters.put(fieldName, converter);
		}
		return converters;
	}

	public static Space getSpace(SpaceDef spaceDef) {
		Space space = new Space();
		space.setCachePolicy(spaceDef.getCachePolicy());
		space.setCapacity(spaceDef.getCapacity());
		space.setDistributionPolicy(spaceDef.getDistributionPolicy());
		space.setEvictionPolicy(spaceDef.getEvictionPolicy());
		if (Utils.hasSpaceDefMethod("getFileSyncInterval")) {
			space.setFileSyncInterval(spaceDef.getFileSyncInterval());
		}
		space.setForgetOldValue(spaceDef.isForgetOldValue());
		space.setHostAwareReplication(spaceDef.isHostAwareReplication());
		space.setKeyIndexType(spaceDef.getKeyDef().getIndexType());
		space.setLockScope(spaceDef.getLockScope());
		space.setLockTTL(spaceDef.getLockTTL());
		space.setLockWait(spaceDef.getLockWait());
		space.setMinSeederCount(spaceDef.getMinSeederCount());
		space.setName(spaceDef.getName());
		space.setPersistenceDistributionPolicy(spaceDef
				.getPersistenceDistributionPolicy());
		space.setPersistencePolicy(spaceDef.getPersistencePolicy());
		space.setPersistenceType(spaceDef.getPersistenceType());
		space.setPhaseCount(spaceDef.getPhaseCount());
		if (Utils.hasSpaceDefMethod("getPhaseRatio")) {
			space.setPhaseRatio(spaceDef.getPhaseRatio());
		}
		if (Utils.hasSpaceDefMethod("getQueryLimit")) {
			space.setQueryLimit(spaceDef.getQueryLimit());
		}
		if (Utils.hasSpaceDefMethod("getQueryTimeout")) {
			space.setQueryTimeout(spaceDef.getQueryTimeout());
		}
		space.setReadTimeout(spaceDef.getReadTimeout());
		space.setReplicationCount(spaceDef.getReplicationCount());
		space.setReplicationPolicy(spaceDef.getReplicationPolicy());
		if (Utils.hasSpaceDefMethod("isRouted")) {
			space.setRouted(spaceDef.isRouted());
		}
		space.setSpaceWait(spaceDef.getSpaceWait());
		space.setTtl(spaceDef.getTTL());
		space.setUpdateTransport(spaceDef.getUpdateTransport());
		space.setVirtualNodeCount(spaceDef.getVirtualNodeCount());
		space.setWriteTimeout(spaceDef.getWriteTimeout());
		Collection<String> distribution = new ArrayList<String>();
		if (Utils.hasSpaceDefMethod("getDistributionFields")) {
			if (spaceDef.getDistributionFields() != null) {
				distribution = spaceDef.getDistributionFields();
			}
		}
		Collection<String> keys = spaceDef.getKeyDef().getFieldNames();
		if (keys == null) {
			keys = new ArrayList<String>();
		}
		for (FieldDef fieldDef : spaceDef.getFieldDefs()) {
			Field field = new Field();
			String fieldName = fieldDef.getName();
			field.setName(fieldName);
			field.setType(fieldDef.getType());
			field.setNullable(fieldDef.isNullable());
			field.setKey(keys.contains(fieldName));
			field.setDistribution(distribution.contains(fieldName));
			if (Utils.hasFieldDefMethod("isEncrypted")) {
				field.setEncrypted(fieldDef.isEncrypted());
			}
			space.getFields().add(field);
		}
		for (IndexDef indexDef : spaceDef.getIndexDefList()) {
			Index index = new Index();
			index.setName(indexDef.getName());
			index.setType(indexDef.getIndexType());
			for (String fieldName : indexDef.getFieldNames()) {
				index.getFields().add(fieldName);
			}
			space.getIndexes().add(index);
		}
		return space;
	}

	public static SpaceDef getSpaceDef(Space space) {
		SpaceDef spaceDef = SpaceDef.create();
		if (space.getCachePolicy() != null) {
			spaceDef.setCachePolicy(space.getCachePolicy());
		}
		if (space.getCapacity() != null) {
			spaceDef.setCapacity(space.getCapacity());
		}
		if (space.getDistributionPolicy() != null) {
			spaceDef.setDistributionPolicy(space.getDistributionPolicy());
		}
		if (space.getEvictionPolicy() != null) {
			spaceDef.setEvictionPolicy(space.getEvictionPolicy());
		}
		if (space.getFileSyncInterval() != null) {
			if (Utils.hasSpaceDefMethod("setFileSyncInterval")) {
				spaceDef.setFileSyncInterval(space.getFileSyncInterval());
			}
		}
		if (space.isForgetOldValue() != null) {
			spaceDef.setForgetOldValue(space.isForgetOldValue());
		}
		if (space.isHostAwareReplication() != null) {
			spaceDef.setHostAwareReplication(space.isHostAwareReplication());
		}
		if (space.getLockScope() != null) {
			spaceDef.setLockScope(space.getLockScope());
		}
		if (space.getLockTTL() != null) {
			spaceDef.setLockTTL(space.getLockTTL());
		}
		if (space.getLockWait() != null) {
			spaceDef.setLockWait(space.getLockWait());
		}
		if (space.getMinSeederCount() != null) {
			spaceDef.setMinSeederCount(space.getMinSeederCount());
		}
		if (space.getName() != null) {
			spaceDef.setName(space.getName());
		}
		if (space.getPersistenceDistributionPolicy() != null) {
			spaceDef.setPersistenceDistributionPolicy(space
					.getPersistenceDistributionPolicy());
		}
		if (space.getPersistencePolicy() != null) {
			spaceDef.setPersistencePolicy(space.getPersistencePolicy());
		}
		if (space.getPersistenceType() != null) {
			spaceDef.setPersistenceType(space.getPersistenceType());
		}
		if (space.getPhaseCount() != null) {
			spaceDef.setPhaseCount(space.getPhaseCount());
		}
		if (space.getPhaseRatio() != null) {
			if (Utils.hasSpaceDefMethod("setPhaseRatio")) {
				spaceDef.setPhaseRatio(space.getPhaseRatio());
			}
		}
		if (space.getQueryLimit() != null) {
			if (Utils.hasSpaceDefMethod("setQueryLimit")) {
				spaceDef.setQueryLimit(space.getQueryLimit());
			}
		}
		if (space.getQueryTimeout() != null) {
			if (Utils.hasSpaceDefMethod("setQueryTimeout")) {
				spaceDef.setQueryTimeout(space.getQueryTimeout());
			}
		}
		if (space.getReadTimeout() != null) {
			spaceDef.setReadTimeout(space.getReadTimeout());
		}
		if (space.getReplicationCount() != null) {
			spaceDef.setReplicationCount(space.getReplicationCount());
		}
		if (space.getReplicationPolicy() != null) {
			spaceDef.setReplicationPolicy(space.getReplicationPolicy());
		}
		if (space.isRouted() != null) {
			if (Utils.hasSpaceDefMethod("setRouted")) {
				spaceDef.setRouted(space.isRouted());
			}
		}
		if (space.getSpaceWait() != null) {
			spaceDef.setSpaceWait(space.getSpaceWait());
		}
		if (space.getTtl() != null) {
			spaceDef.setTTL(space.getTtl());
		}
		if (space.getUpdateTransport() != null) {
			spaceDef.setUpdateTransport(space.getUpdateTransport());
		}
		if (space.getVirtualNodeCount() != null) {
			spaceDef.setVirtualNodeCount(space.getVirtualNodeCount());
		}
		if (space.getWriteTimeout() != null) {
			spaceDef.setWriteTimeout(space.getWriteTimeout());
		}
		Collection<String> keys = new ArrayList<String>();
		Collection<String> distribution = new ArrayList<String>();
		for (Field field : space.getFields()) {
			FieldDef fieldDef = FieldDef.create(field.getName(),
					field.getType());
			if (field.isEncrypted() != null) {
				if (Utils.hasFieldDefMethod("setEncrypted")) {
					fieldDef.setEncrypted(field.isEncrypted());
				}
			}
			if (field.isNullable() != null) {
				fieldDef.setNullable(field.isNullable());
			}
			spaceDef.putFieldDef(fieldDef);
			if (Boolean.TRUE.equals(field.isKey())) {
				keys.add(field.getName());
			}
			if (Boolean.TRUE.equals(field.isDistribution())) {
				distribution.add(field.getName());
			}
		}
		KeyDef keyDef = spaceDef.getKeyDef();
		if (space.getKeyIndexType() != null) {
			keyDef.setIndexType(space.getKeyIndexType());
		}
		if (!keys.isEmpty()) {
			keyDef.setFieldNames(keys.toArray(new String[keys.size()]));
		}
		if (!distribution.isEmpty()) {
			if (Utils.hasSpaceDefMethod("setDistributionFields")) {
				spaceDef.setDistributionFields(distribution
						.toArray(new String[distribution.size()]));
			}
		}
		for (Index index : space.getIndexes()) {
			IndexDef indexDef = IndexDef.create(index.getName());
			if (index.getType() != null) {
				indexDef.setIndexType(index.getType());
			}
			indexDef.setFieldNames(index.getFields().toArray(
					new String[index.getFields().size()]));
			spaceDef.addIndexDef(indexDef);
		}
		return spaceDef;
	}

	public static com.tibco.as.xml.Metaspace getMetaspace(Metaspace metaspace) {
		com.tibco.as.xml.Metaspace xmlMetaspace = new com.tibco.as.xml.Metaspace();
		MemberDef memberDef = metaspace.getMemberDef();
		if (memberDef.getRemoteDiscovery() == null
				|| memberDef.getRemoteDiscovery().isEmpty()) {
			xmlMetaspace.setDiscovery(memberDef.getDiscovery());
		} else {
			xmlMetaspace.setDiscovery(memberDef.getRemoteDiscovery());
			xmlMetaspace.setRemote(true);
		}
		xmlMetaspace.setListen(memberDef.getListen());
		xmlMetaspace.setMember(memberDef.getMemberName());
		xmlMetaspace.setName(metaspace.getName());
		xmlMetaspace.setConnectTimeout(memberDef.getConnectTimeout());
		return xmlMetaspace;
	}

}
