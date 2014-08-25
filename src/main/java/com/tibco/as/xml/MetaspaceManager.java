package com.tibco.as.xml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import com.tibco.as.space.ASException;
import com.tibco.as.space.Member;
import com.tibco.as.space.MemberDef;
import com.tibco.as.space.Metaspace;
import com.tibco.as.util.Utils;

public class MetaspaceManager {

	final private static ThreadLocal<DatatypeFactory> datatypeFactoryHolder = new ThreadLocal<DatatypeFactory>() {
		@Override
		protected DatatypeFactory initialValue() {
			try {
				return DatatypeFactory.newInstance();
			} catch (DatatypeConfigurationException e) {
				throw new IllegalStateException("failed to create "
						+ DatatypeFactory.class.getSimpleName(), e);
			}
		}
	};

	private static final String FILENAME = "metaspaces.xml";

	private static MetaspaceManager instance;

	private MetaspaceManager() {
	}

	@SuppressWarnings("unchecked")
	public static MetaspaceManager getInstance() throws JAXBException,
			IOException, ASException {
		if (instance == null) {
			instance = new MetaspaceManager();
			File file = new File(FILENAME);
			if (file.exists()) {
				JAXBElement<Metaspaces> element = (JAXBElement<Metaspaces>) XMLFactory
						.unmarshall(file);
				add(element.getValue());
			}
			InputStream in = ClassLoader.getSystemResourceAsStream(FILENAME);
			if (in != null) {

				JAXBElement<Metaspaces> element = (JAXBElement<Metaspaces>) XMLFactory
						.unmarshall(in);
				add(element.getValue());
			}
		}
		return instance;
	}

	private static void add(Metaspaces metaspaces) throws ASException {
		for (com.tibco.as.xml.Metaspace metaspace : metaspaces.getMetaspace()) {
			instance.add(metaspace);
		}
	}

	private Metaspaces metaspaces = new Metaspaces();

	private List<Metaspace> connectedMetaspaces = new ArrayList<Metaspace>();

	public void add(com.tibco.as.xml.Metaspace metaspace) throws ASException {
		metaspaces.getMetaspace().add(metaspace);
	}

	public Metaspace connect(com.tibco.as.xml.Metaspace metaspace)
			throws ASException {
		Metaspace ms = Utils.getMetaspace(metaspace.getName());
		if (ms == null) {
			ms = Metaspace
					.connect(metaspace.getName(), getMemberDef(metaspace));
			connectedMetaspaces.add(ms);
		}
		return ms;
	}

	private MemberDef getMemberDef(com.tibco.as.xml.Metaspace metaspace) {
		MemberDef memberDef = MemberDef.create();
		memberDef.setMemberName(metaspace.getMember());
		if (metaspace.isRemote()) {
			memberDef.setRemoteDiscovery(metaspace.getDiscovery());
			memberDef.setRemoteListen(metaspace.getListen());
		} else {
			memberDef.setDiscovery(metaspace.getDiscovery());
			memberDef.setListen(metaspace.getListen());
		}
		if (metaspace.getConnectTimeout() != null) {
			memberDef.setConnectTimeout(metaspace.getConnectTimeout());
		}
		return memberDef;
	}

	public List<Metaspace> getConnectedMetaspaces() {
		return new ArrayList<Metaspace>(connectedMetaspaces);
	}

	public Metaspace getMetaspace(String name) throws ASException,
			JAXBException, IOException {
		Metaspace ms = Utils.getMetaspace(name);
		if (ms == null) {
			ms = connect(getXMLMetaspace(name));
		}
		return ms;
	}

	private com.tibco.as.xml.Metaspace getXMLMetaspace(String name) {
		for (com.tibco.as.xml.Metaspace metaspace : metaspaces.getMetaspace()) {
			if (Utils.getMetaspaceName(name).equals(
					Utils.getMetaspaceName(metaspace.getName()))) {
				return metaspace;
			}
		}
		return new com.tibco.as.xml.Metaspace();
	}

	public static com.tibco.as.xml.Member getMember(Member member)
			throws DatatypeConfigurationException {
		com.tibco.as.xml.Member m = new com.tibco.as.xml.Member();
		m.setId(member.getId());
		m.setName(member.getName());
		m.setManagementRole(member.getManagementRole());
		m.setHostAddress(member.getHostAddress());
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(member.getJoinTime().getTime().getTime());
		m.setJoinTime(datatypeFactoryHolder.get().newXMLGregorianCalendar(
				calendar));
		m.setPort(member.getPort());
		return m;
	}

	public void closeMetaspace(String name) throws ASException {
		for (Metaspace metaspace : new ArrayList<Metaspace>(connectedMetaspaces)) {
			if (metaspace.getName().equals(Utils.getMetaspaceName(name))) {
				metaspace.closeAll();
				connectedMetaspaces.remove(metaspace);
			}
		}
		Metaspace ms = Utils.getMetaspace(name);
		if (ms == null) {
			return;
		}
		ms.closeAll();
	}

	public void closeAll() throws ASException {
		for (Metaspace metaspace : connectedMetaspaces) {
			metaspace.closeAll();
		}
		connectedMetaspaces.clear();
	}

}
