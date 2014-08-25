package com.tibco.as.convert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Attributes implements Cloneable {

	private Map<Attribute<?>, Map<String, Object>> map = new HashMap<Attribute<?>, Map<String, Object>>();

	public <T> T get(Attribute<T> attribute, String... names) {
		return get(attribute, Arrays.asList(names));
	}

	@Override
	public Attributes clone() throws CloneNotSupportedException {
		Attributes attributes = new Attributes();
		attributes.map.putAll(map);
		return attributes;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(Attribute<T> attribute, List<String> names) {
		Map<String, Object> attributes = map.get(attribute);
		if (attributes == null) {
			return null;
		}
		for (String name : names) {
			if (attributes.containsKey(name)) {
				return (T) attributes.get(name);
			}
		}
		return (T) attributes.get(null);
	}

	@SuppressWarnings("unchecked")
	public <T> T getSpecific(Attribute<T> attribute, String name) {
		Map<String, Object> attributes = map.get(attribute);
		if (attributes == null) {
			return null;
		}
		return (T) attributes.get(name);
	}

	public <T> void put(Attribute<T> attribute, T value) {
		put(attribute, value, null);
	}

	public <T> void put(Attribute<T> attribute, T value, String name) {
		Map<String, Object> attributes = map.get(attribute);
		if (attributes == null) {
			attributes = new HashMap<String, Object>();
			map.put(attribute, attributes);
		}
		attributes.put(name, value);
	}

	@SuppressWarnings("unchecked")
	public <T> void remove(Attribute<T> attribute, String name) {
		Map<String, T> attributes = (Map<String, T>) map.get(attribute);
		if (attributes == null) {
			return;
		}
		attributes.remove(name);
	}

	public void putAll(Attributes attributes) {
		for (Attribute<?> key : attributes.map.keySet()) {
			Map<String, Object> value;
			if (map.containsKey(key)) {
				value = map.get(key);
			} else {
				value = new HashMap<String, Object>();
				map.put(key, value);
			}
			value.putAll(attributes.map.get(key));
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Attributes getAttributes(String name) {
		Attributes attributes = new Attributes();
		for (Attribute key : map.keySet()) {
			Object value = get(key, name);
			attributes.put(key, value);
		}
		return attributes;
	}

}
