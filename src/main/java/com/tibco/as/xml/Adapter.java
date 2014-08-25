package com.tibco.as.xml;

import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.IndexDef.IndexType;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.Member.ManagementRole;
import com.tibco.as.space.SpaceDef.CachePolicy;
import com.tibco.as.space.SpaceDef.DistributionPolicy;
import com.tibco.as.space.SpaceDef.EvictionPolicy;
import com.tibco.as.space.SpaceDef.LockScope;
import com.tibco.as.space.SpaceDef.PersistencePolicy;
import com.tibco.as.space.SpaceDef.PersistenceType;
import com.tibco.as.space.SpaceDef.ReplicationPolicy;
import com.tibco.as.space.SpaceDef.UpdateTransport;

public class Adapter {

	public static <T extends Enum<?>> String print(T value) {
		return value == null ? null : value.name().toLowerCase();
	}

	public static String parse(String name) {
		return name.toUpperCase();
	}

	public static String printPersistenceType(PersistenceType type) {
		return print(type);
	}

	public static PersistenceType parsePersistenceType(String name) {
		return PersistenceType.valueOf(parse(name));
	}

	public static String printIndexType(IndexType type) {
		return print(type);
	}

	public static IndexType parseIndexType(String name) {
		return IndexType.valueOf(parse(name));
	}

	public static String printCachePolicy(CachePolicy policy) {
		return print(policy);
	}

	public static CachePolicy parseCachePolicy(String name) {
		return CachePolicy.valueOf(parse(name));
	}

	public static String printDistributionPolicy(DistributionPolicy policy) {
		return print(policy);
	}

	public static DistributionPolicy parseDistributionPolicy(String name) {
		return DistributionPolicy.valueOf(parse(name));
	}

	public static String printEvictionPolicy(EvictionPolicy policy) {
		return print(policy);
	}

	public static EvictionPolicy parseEvictionPolicy(String name) {
		return EvictionPolicy.valueOf(parse(name));
	}

	public static String printUpdateTransport(UpdateTransport transport) {
		return print(transport);
	}

	public static UpdateTransport parseUpdateTransport(String name) {
		return UpdateTransport.valueOf(parse(name));
	}

	public static String printLockScope(LockScope scope) {
		return print(scope);
	}

	public static LockScope parseLockScope(String name) {
		return LockScope.valueOf(parse(name));
	}

	public static String printPersistencePolicy(PersistencePolicy policy) {
		return print(policy);
	}

	public static PersistencePolicy parsePersistencePolicy(String name) {
		return PersistencePolicy.valueOf(parse(name));
	}

	public static String printReplicationPolicy(ReplicationPolicy policy) {
		return print(policy);
	}

	public static ReplicationPolicy parseReplicationPolicy(String name) {
		return ReplicationPolicy.valueOf(parse(name));
	}

	public static String printFieldType(FieldType type) {
		return print(type);
	}

	public static FieldType parseFieldType(String name) {
		return FieldType.valueOf(parse(name));
	}

	public static String printDistributionRole(DistributionRole role) {
		return print(role);
	}

	public static DistributionRole parseDistributionRole(String name) {
		return DistributionRole.valueOf(parse(name));
	}

	public static String printManagementRole(ManagementRole role) {
		return print(role);
	}

	public static ManagementRole parseManagementRole(String name) {
		return ManagementRole.valueOf(parse(name));
	}

}