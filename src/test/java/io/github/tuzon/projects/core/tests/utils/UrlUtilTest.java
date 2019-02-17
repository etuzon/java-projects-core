package io.github.tuzon.projects.core.tests.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.tuzon.projects.core.tests.asserts.SoftAssert;
import io.github.tuzon.projects.core.tests.base.BaseTest;
import io.github.tuzon.projects.core.utils.UrlUtil;

public class UrlUtilTest extends BaseTest {
	@DataProvider(name = "getHostFromUrl")
	public static Object[][] getHostFromUrl() {
		return new Object[][] { { "http://host", "host" }, { "http://host.com", "host.com" }, 
			{ "host.com", "host.com" }, { "https://host:8000", "host"}, 
			{ "ftp://host/test", "host" }, { "ftp://host:6000/test", "host" } };
	}
	
	@Test(dataProvider = "getHostFromUrl")
	public void getHostFromUrl_test(String url, String expectedResult) {
		String result = UrlUtil.getHostFromUrl(url);
		
		SoftAssert.assertTrueNow(result != null, "Result of getHostFromUrl should not be null",
				"Verify that result of getHostFromUrl is not null");
		
		SoftAssert.assertTrueNow(expectedResult.equals(result), 
				"Result of getHostFromUrl is [" + result + "] and should be [" + expectedResult + "]");
	}
}