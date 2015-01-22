package com.tibco.as.util.file;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;

public class TestFileUtils {

	private final static File FILE = new File(
			"/var/folders/tc/p_m_vk0d4yq3rdh3xw6qhf4w0000gq/T/com.tibco.as.util.Utils16978879243791375571414646018657/space1.csv");

	@Test
	public void testGetBaseName() {
		Assert.assertEquals("report.sdfsdf",
				FileUtils.getBaseName("report.sdfsdf.xls"));
		Assert.assertEquals("space1", FileUtils.getBaseName(FILE));
	}

	@Test
	public void testGetExtension() {
		Assert.assertEquals("csv", FileUtils.getExtension(FILE));
	}
}
