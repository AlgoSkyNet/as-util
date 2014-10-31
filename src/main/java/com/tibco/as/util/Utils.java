package com.tibco.as.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;

import com.tibco.as.space.ASCommon;
import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;

public class Utils {

	public static boolean hasMethod(Class<?> clazz, String name) {
		for (Method method : clazz.getMethods()) {
			if (method.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static boolean hasMemberDefMethod(String name) {
		return hasMethod(MemberDef.class, name);
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

	public static Metaspace connect(String metaspaceName, Member member)
			throws ASException {
		return Metaspace.connect(metaspaceName, getMemberDef(member));
	}

	private static MemberDef getMemberDef(Member member) {
		MemberDef memberDef = MemberDef.create();
		if (member == null) {
			return memberDef;
		}
		if (member.getClusterSuspendThreshold() != null) {
			if (Utils.hasMemberDefMethod("setClusterSuspendThreshold")) {
				memberDef.setClusterSuspendThreshold(member
						.getClusterSuspendThreshold());
			}
		}
		if (member.getConnectTimeout() != null) {
			if (Utils.hasMemberDefMethod("setConnectTimeout")) {
				memberDef.setConnectTimeout(member.getConnectTimeout());
			}
		}
		if (member.getDataStore() != null) {
			memberDef.setDataStore(member.getDataStore());
		}
		if (member.getDiscovery() != null) {
			memberDef.setDiscovery(member.getDiscovery());
		}
		if (member.getIdentityPassword() != null) {
			if (Utils.hasMemberDefMethod("setIdentityPassword")) {
				memberDef.setIdentityPassword(member.getIdentityPassword()
						.toCharArray());
			}
		}
		if (member.getListen() != null) {
			memberDef.setListen(member.getListen());
		}
		if (member.getMemberName() != null) {
			memberDef.setMemberName(member.getMemberName());
		}
		if (member.getMemberTimeout() != null) {
			if (Utils.hasMemberDefMethod("setMemberTimeout")) {
				memberDef.setMemberTimeout(member.getMemberTimeout());
			}
		}
		if (member.getRemoteDiscovery() != null) {
			memberDef.setRemoteDiscovery(member.getRemoteDiscovery());
		}
		if (member.getRemoteListen() != null) {
			memberDef.setRemoteListen(member.getRemoteListen());
		}
		if (member.getRxBufferSize() != null) {
			memberDef.setRxBufferSize(member.getRxBufferSize());
		}
		if (member.getSecurityPolicyFile() != null) {
			if (Utils.hasMemberDefMethod("setSecurityPolicyFile")) {
				memberDef.setSecurityPolicyFile(member.getSecurityPolicyFile());
			}
		}
		if (member.getSecurityTokenFile() != null) {
			if (Utils.hasMemberDefMethod("setSecurityTokenFile")) {
				memberDef.setSecurityTokenFile(member.getSecurityTokenFile());
			}
		}
		if (member.getTransportThreadCount() != null) {
			memberDef.setTransportThreadCount(member.getTransportThreadCount());
		}
		if (member.getWorkerThreadCount() != null) {
			memberDef.setWorkerThreadCount(member.getWorkerThreadCount());
		}
		return memberDef;
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

	public static void copy(InputStream inp, OutputStream out)
			throws IOException {
		byte[] buff = new byte[4096];
		int count;
		while ((count = inp.read(buff)) != -1) {
			if (count > 0) {
				out.write(buff, 0, count);
			}
		}
	}

	public static File copy(String resource, File dir) throws IOException {
		return copy(resource, dir, resource);
	}

	public static InputStream getResourceAsStream(String resource) {
		return ClassLoader.getSystemClassLoader().getResourceAsStream(resource);
	}

	public static File createTempDirectory() throws IOException {
		File dir = File.createTempFile(Utils.class.getName(),
				String.valueOf(System.currentTimeMillis()));
		if (!dir.delete()) {
			throw new IOException(MessageFormat.format(
					"Could not delete temp file: {0}", dir.getAbsolutePath()));
		}
		if (!dir.mkdir()) {
			throw new IOException(MessageFormat.format(
					"Could not create temp directory: {0}",
					dir.getAbsolutePath()));
		}
		return dir;
	}

	public static File copy(String resource, File dir, String filename)
			throws IOException {
		File file = new File(dir, filename);
		copyToFile(resource, file);
		return file;
	}

	public static void copyToFile(String resource, File destination)
			throws IOException {
		OutputStream out = new FileOutputStream(destination);
		try {
			InputStream in = getResourceAsStream(resource);
			if (in == null) {
				throw new FileNotFoundException(resource);
			}
			try {
				Utils.copy(in, out);
			} finally {
				in.close();
			}
		} finally {
			out.close();
		}
	}

	public static Collection<String> getFieldNames(SpaceDef spaceDef) {
		Collection<String> fieldNames = new ArrayList<String>();
		for (FieldDef fieldDef : spaceDef.getFieldDefs()) {
			fieldNames.add(fieldDef.getName());
		}
		return fieldNames;
	}

}
