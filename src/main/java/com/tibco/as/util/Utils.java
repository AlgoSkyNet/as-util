package com.tibco.as.util;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.tibco.as.space.ASCommon;
import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;

public class Utils {

	public static FieldDef[] getFieldDefs(SpaceDef spaceDef,
			String... fieldNames) {
		String[] names = getFieldNames(spaceDef.getFieldDefs(), fieldNames);
		FieldDef[] result = new FieldDef[names.length];
		for (int index = 0; index < names.length; index++) {
			result[index] = spaceDef.getFieldDef(names[index]);
		}
		return result;
	}

	public static List<String> getFieldNames(FieldDef... fieldDefs) {
		return getFieldNames(fieldDefs);
	}

	public static String[] getFieldNames(Collection<FieldDef> fieldDefs,
			String... fieldNames) {
		if (fieldNames == null || fieldNames.length == 0) {
			FieldDef[] fields = fieldDefs
					.toArray(new FieldDef[fieldDefs.size()]);
			String[] result = new String[fields.length];
			for (int index = 0; index < fields.length; index++) {
				result[index] = fields[index].getName();
			}
			return result;
		}
		return fieldNames;

	}

	public static String[] getFieldNames(SpaceDef spaceDef,
			String... fieldNames) {
		return getFieldNames(spaceDef.getFieldDefs(), fieldNames);
	}

	public static FieldDef[] getKeyFieldDefs(SpaceDef spaceDef) {
		Collection<String> fieldNames = spaceDef.getKeyDef().getFieldNames();
		String[] keyFieldNames = fieldNames.toArray(new String[fieldNames
				.size()]);
		FieldDef[] fieldDefs = new FieldDef[keyFieldNames.length];
		for (int index = 0; index < keyFieldNames.length; index++) {
			fieldDefs[index] = spaceDef.getFieldDef(keyFieldNames[index]);
		}
		return fieldDefs;
	}

	public static String[] getNonKeyFieldNames(SpaceDef spaceDef) {
		Collection<FieldDef> fieldDefs = spaceDef.getFieldDefs();
		Collection<String> keys = spaceDef.getKeyDef().getFieldNames();
		List<String> fieldNames = new ArrayList<String>();
		for (FieldDef fieldDef : fieldDefs) {
			String fieldName = fieldDef.getName();
			if (!keys.contains(fieldName)) {
				fieldNames.add(fieldName);
			}
		}
		return fieldNames.toArray(new String[fieldNames.size()]);
	}

	public static boolean hasMethod(Class<?> clazz, String name) {
		for (Method method : clazz.getMethods()) {
			if (method.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasSpaceDefMethod(String name) {
		return hasMethod(SpaceDef.class, name);
	}

	public static boolean hasFieldDefMethod(String name) {
		return hasMethod(FieldDef.class, name);
	}

	public static String getMetaspaceName(String metaspaceName) {
		return metaspaceName == null ? "ms" : metaspaceName;
	}

	public static Metaspace getMetaspace(String name) {
		return ASCommon.getMetaspace(getMetaspaceName(name));
	}

	public static String getSpaceURI(String metaspaceName, String spaceName) {
		return getMetaspaceName(metaspaceName) + "." + spaceName;
	}

	public static String getSpaceURI(Metaspace metaspace, String spaceName) {
		return getSpaceURI(metaspace.getName(), spaceName);
	}

	public static Space getSpace(String metaspaceName, String spaceName,
			DistributionRole distributionRole) throws ASException {
		Metaspace metaspace = getMetaspace(metaspaceName);
		if (distributionRole == null) {
			return metaspace.getSpace(spaceName);
		}
		return metaspace.getSpace(spaceName, distributionRole);
	}

}
