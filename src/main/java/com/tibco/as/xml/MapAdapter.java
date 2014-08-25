package com.tibco.as.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlValue;
import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.eclipse.persistence.oxm.annotations.XmlVariableNode;

import com.tibco.as.xml.MapAdapter.AdaptedMap;

public class MapAdapter extends XmlAdapter<AdaptedMap, Map<String, String>> {

	public static class AdaptedMap {

		@XmlVariableNode("key")
		List<AdaptedEntry> entries = new ArrayList<AdaptedEntry>();

	}

	public static class AdaptedEntry {

		@XmlTransient
		public String key;

		@XmlValue
		public String value;

	}

	@Override
	public AdaptedMap marshal(Map<String, String> map) throws Exception {
		AdaptedMap adaptedMap = new AdaptedMap();
		for (Entry<String, String> entry : map.entrySet()) {
			AdaptedEntry adaptedEntry = new AdaptedEntry();
			adaptedEntry.key = entry.getKey();
			adaptedEntry.value = entry.getValue();
			adaptedMap.entries.add(adaptedEntry);
		}
		return adaptedMap;
	}

	@Override
	public Map<String, String> unmarshal(AdaptedMap adaptedMap)
			throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		for (AdaptedEntry entry : adaptedMap.entries) {
			map.put(entry.key, entry.value);
		}
		return map;
	}
}