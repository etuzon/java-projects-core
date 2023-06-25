package io.github.etuzon.projects.core.tests.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.etuzon.projects.core.utils.UrlUtil;
import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.base.BaseUnitTest;

public class UrlUtilTest extends BaseUnitTest {
	@DataProvider(name = "getHostFromUrl")
	public static Object[][] getHostFromUrl() {
		return new Object[][] {
				{ "http://host", "host" },
				{ "http://host.com", "host.com" },
				{ "host.com", "host.com" },
				{ "https://host:8000", "host"},
				{ "ftp://host/test", "host" },
				{ "ftp://host:6000/test", "host" }
		};
	}
	
	@Test(dataProvider = "getHostFromUrl")
	public void getHostFromUrl_test(String url, String expectedResult) {
		String result = UrlUtil.getHostFromUrl(url);
		
		SoftAssertUnitTest.assertTrueNow(
				result != null,
				"Result of getHostFromUrl should not be null",
				"Verify that result of getHostFromUrl is not null");
		
		SoftAssertUnitTest.assertTrueNow(expectedResult.equals(result), 
				"Result of getHostFromUrl is [" + result + "] and should be [" + expectedResult + "]");
	}
}