package com.tibco.as.util;

import org.junit.Assert;
import org.junit.Test;

public class TestUtils {

	@Test
	public void testGetExtension() {
		Assert.assertEquals("xls", Utils.getExtension("report.sdfsdf.xls"));
	}

	@Test
	public void testGetBaseName() {
		Assert.assertEquals("report.sdfsdf",
				Utils.getBaseName("report.sdfsdf.xls"));
	}
}
