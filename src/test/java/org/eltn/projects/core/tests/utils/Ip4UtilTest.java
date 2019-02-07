package org.eltn.projects.core.tests.utils;

import java.util.Arrays;

import org.eltn.projects.core.expections.InvalidValueException;
import org.eltn.projects.core.tests.asserts.SoftAssert;
import org.eltn.projects.core.tests.base.BaseTest;
import org.eltn.projects.core.tests.exceptions.AutomationTestException;
import org.eltn.projects.core.utils.Ip4Util;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Ip4UtilTest extends BaseTest {

	@DataProvider(name = "validIp4")
	public static Object[][] validIp4() {
		return new Object[][] { { "1.10.123.1" }, { "255.255.255.255" }, { "0.0.0.0" } };
	}

	@Test(dataProvider = "validIp4")
	public void isValidIp_test(String ip) throws AutomationTestException {
		SoftAssert.assertTrueNow(isValidIp(ip), "IP [" + ip + "] is not a valid IPv4",
				"Verify that IP [" + ip + "] is a valid IPv4");
	}

	@DataProvider(name = "invalidIp4")
	public static Object[][] invalidIp4() {
		return new Object[][] { { "-1.10.123.1" }, { "255.255.255.256" }, { "a.0.0.0" }, { "0.0.0" }, { "0.0..0" },
				{ "" }, { "3.3" } };
	}

	@Test(dataProvider = "invalidIp4")
	public void isValidIp_negative_test(String ip) throws AutomationTestException {
		SoftAssert.assertTrueNow(isValidIp(ip) == false, "IP [" + ip + "] should not be a valid IPv4",
				"Verify that IP [" + ip + "] is invalid IPv4");
	}

	@Test
	public void isValidIp_null_negative_test() {
		try {
			Ip4Util.isValidIp(null);
			SoftAssert.failNow("InvalidValueException was not happen on null input parameter");
		} catch (InvalidValueException e) {
		}
	}

	@DataProvider(name = "ip4OctatArray")
	public static Object[][] ip4OctatArray() {
		return new Object[][] { { new int[] { 0, 0, 0, 0 }, "0.0.0.0" }, { new int[] { 255, 0, 4, 0 }, "255.0.4.0" } };
	}

	@Test(dataProvider = "ip4OctatArray")
	public void toIPv4_test(int[] octats, String ip) throws AutomationTestException {
		String currentIp = toIPv4(octats);
		SoftAssert.assertTrueNow(currentIp != null, "valueOf result for octats [" + Arrays.toString(octats) + "] is null",
				"Verify that valueOf result is not null");
		SoftAssert.assertTrueNow(ip.equals(currentIp),
				"IP for octats [" + Arrays.toString(octats) + "] is [" + currentIp + "] and should be [" + ip + "]",
				"Verify that IP for octats [" + Arrays.toString(octats) + "] is [" + ip + "]");
	}

	@DataProvider(name = "invalidIp4OctatArray")
	public static Object[][] invalidIp4OctatArray() {
		return new Object[][] { { new int[] { 0, 0, 0 } }, { new int[] { 0, 0, 0, 0, 0 } },
				{ new int[] { 256, 0, 0, 0 } }, { new int[] { 0, 0, -4, 0 } } };
	}

	@Test(dataProvider = "invalidIp4OctatArray")
	public void toIPv4_invalid_octats_negative_test(int[] octats) throws AutomationTestException {
		String ip = toIPv4(octats);
		SoftAssert.assertTrueNow(
				ip == null, "valueOf result for invalid octats [" + Arrays.toString(octats)
						+ "] is [" + ip + "] and should be null",
				"Verify that valueOf result for invalid octats is null");
	}
	
	@DataProvider(name = "ip4OctatArrayNotEqualToIp")
	public static Object[][] ip4OctatArrayNotEqualToIp() {
		return new Object[][] { { new int[] { 0, 0, 0, 0 }, "0.0.0.1" }, { new int[] { 255, 0, 4, 0 }, "255.1.4.0" } };
	}

	@Test(dataProvider = "ip4OctatArrayNotEqualToIp")
	public void toIPv4_not_equal_to_result_negative_test(int[] octats, String ip) throws AutomationTestException {
		String currentIp = toIPv4(octats);
		SoftAssert.assertTrueNow(currentIp != null, "valueOf result for octats [" + Arrays.toString(octats) + "] is null",
				"Verify that valueOf result is not null");
		SoftAssert.assertTrueNow(ip.equals(currentIp) == false,
				"IP for octats [" + Arrays.toString(octats) + "] should not be equal to IP [" + ip + "]",
				"Verify that IP for octats [" + Arrays.toString(octats) + "] is not equal to IP [" + ip + "]");
	}
	
	private boolean isValidIp(String ip) throws AutomationTestException {
	    try {
            return Ip4Util.isValidIp(ip);
        } catch (InvalidValueException e) {
           throw new AutomationTestException(e);
        }
	}
	
	private String toIPv4(int[] octats) throws AutomationTestException {
	    try {
            return Ip4Util.toIPv4(octats);
        } catch (InvalidValueException e) {
            throw new AutomationTestException(e);
        }
	}
}