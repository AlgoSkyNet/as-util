package com.tibco.as.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;

import com.tibco.as.space.ASCommon;
import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.Member.DistributionRole;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.space.Space;
import com.tibco.as.space.SpaceDef;

public class Utils {

	private final static char WILDCARD_SINGLE = '?';
	private final static char WILDCARD_MULTIPLE = '*';

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

	public static File copy(String resource, File dir) throws IOException {
		return copy(resource, dir, resource);
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

	public static void copyToFile(String resource, File file)
			throws IOException {
		OutputStream out = new FileOutputStream(file);
		try {
			InputStream in = ClassLoader.getSystemClassLoader()
					.getResourceAsStream(resource);
			if (in == null) {
				throw new FileNotFoundException(resource);
			}
			byte[] buff = new byte[4096];
			int count;
			try {
				while ((count = in.read(buff)) != -1) {
					if (count > 0) {
						out.write(buff, 0, count);
					}
				}
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

	public static boolean matches(String name, String pattern,
			boolean ignoreCase) {
		if (name == null && pattern == null) {
			return true;
		}
		if (name == null || pattern == null) {
			return false;
		}
		String[] wcs = splitOnTokens(pattern);
		boolean anyChars = false;
		int textIdx = 0;
		int wcsIdx = 0;
		Stack<int[]> backtrack = new Stack<int[]>();
		do {
			if (backtrack.size() > 0) {
				int[] array = backtrack.pop();
				wcsIdx = array[0];
				textIdx = array[1];
				anyChars = true;
			}
			while (wcsIdx < wcs.length) {
				if (wcs[wcsIdx].equals(String.valueOf(WILDCARD_SINGLE))) {
					textIdx++;
					if (textIdx > name.length()) {
						break;
					}
					anyChars = false;
				} else if (wcs[wcsIdx]
						.equals(String.valueOf(WILDCARD_MULTIPLE))) {
					anyChars = true;
					if (wcsIdx == wcs.length - 1) {
						textIdx = name.length();
					}
				} else {
					if (anyChars) {
						textIdx = checkIndexOf(name, textIdx, wcs[wcsIdx],
								ignoreCase);
						if (textIdx == -1) {
							break;
						}
						int repeat = checkIndexOf(name, textIdx + 1,
								wcs[wcsIdx], ignoreCase);
						if (repeat >= 0) {
							backtrack.push(new int[] { wcsIdx, repeat });
						}
					} else {
						if (!name.regionMatches(ignoreCase, textIdx,
								wcs[wcsIdx], 0, wcs[wcsIdx].length())) {
							break;
						}
					}
					textIdx += wcs[wcsIdx].length();
					anyChars = false;
				}

				wcsIdx++;
			}
			if (wcsIdx == wcs.length && textIdx == name.length()) {
				return true;
			}

		} while (backtrack.size() > 0);

		return false;
	}

	private static String[] splitOnTokens(String text) {
		if (text.indexOf(WILDCARD_SINGLE) == -1
				&& text.indexOf(WILDCARD_MULTIPLE) == -1) {
			return new String[] { text };
		}
		char[] array = text.toCharArray();
		ArrayList<String> list = new ArrayList<String>();
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < array.length; i++) {
			if (array[i] == WILDCARD_SINGLE || array[i] == WILDCARD_MULTIPLE) {
				if (buffer.length() != 0) {
					list.add(buffer.toString());
					buffer.setLength(0);
				}
				if (array[i] == WILDCARD_SINGLE) {
					list.add(String.valueOf(WILDCARD_SINGLE));
				} else if (list.isEmpty()
						|| i > 0
						&& list.get(list.size() - 1).equals(
								String.valueOf(WILDCARD_MULTIPLE)) == false) {
					list.add(String.valueOf(WILDCARD_MULTIPLE));
				}
			} else {
				buffer.append(array[i]);
			}
		}
		if (buffer.length() != 0) {
			list.add(buffer.toString());
		}
		return list.toArray(new String[list.size()]);
	}

	private static int checkIndexOf(String str, int strStartIndex,
			String search, boolean ignoreCase) {
		int endIndex = str.length() - search.length();
		if (endIndex >= strStartIndex) {
			for (int i = strStartIndex; i <= endIndex; i++) {
				if (str.regionMatches(ignoreCase, i, search, 0, search.length())) {
					return i;
				}
			}
		}
		return -1;
	}

	public static String readString(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		StringBuilder out = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			out.append(line);
			out.append("\n");
		}
		return out.toString();
	}

}
