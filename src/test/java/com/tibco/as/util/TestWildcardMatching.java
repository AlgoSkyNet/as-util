package com.tibco.as.util;

import org.junit.Assert;
import org.junit.Test;

public class TestWildcardMatching {

	@Test
	public void testWildcards() {
		Assert.assertTrue(Utils.matches("abc.csv", "abc.csv", false));
		Assert.assertFalse(Utils.matches("bcd.csv", "abc.csv", false));
		Assert.assertTrue(Utils.matches("ABC.csv", "abc.csv", true));
		Assert.assertTrue(Utils.matches("ABC.csv", "a??.csv", true));
		Assert.assertTrue(Utils.matches("test.csv", "*.csv", false));
		Assert.assertTrue(Utils.matches("test.csv", "????.csv", false));
		Assert.assertFalse(Utils.matches("test.csv", "???.csv", false));
	}
}
