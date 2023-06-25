package io.github.etuzon.projects.core.tests.utils;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.etuzon.projects.core.expections.InvalidValueException;
import io.github.etuzon.projects.core.utils.Ip4Util;
import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.base.BaseUnitTest;
import io.github.etuzon.unit.tests.exceptions.AutomationUnitTestException;

public class Ip4UtilTest extends BaseUnitTest {

	@DataProvider(name = "validIp4")
	public static Object[][] validIp4() {
		return new Object[][] { { "1.10.123.1" }, { "255.255.255.255" }, { "0.0.0.0" } };
	}

	@Test(dataProvider = "validIp4")
	public void isValidIp_test(String ip) throws AutomationUnitTestException {
		SoftAssertUnitTest.assertTrueNow(
				isValidIp(ip),
				"IP [" + ip + "] is not a valid IPv4",
				"Verify that IP [" + ip + "] is a valid IPv4");
	}

	@DataProvider(name = "invalidIp4")
	public static Object[][] invalidIp4() {
		return new Object[][] {
				{ "-1.10.123.1" },
				{ "255.255.255.256" },
				{ "a.0.0.0" },
				{ "0.0.0" },
				{ "0.0..0" },
				{ "" },
				{ "3.3" }
		};
	}

	@Test(dataProvider = "invalidIp4")
	public void isValidIp_negative_test(String ip) throws AutomationUnitTestException {
		SoftAssertUnitTest.assertTrueNow(
				!isValidIp(ip),
				"IP [" + ip + "] should not be a valid IPv4",
				"Verify that IP [" + ip + "] is invalid IPv4");
	}

	@Test
	public void isValidIp_null_negative_test() {
		try {
			Ip4Util.isValidIp(null);
			SoftAssertUnitTest.failNow(
					"InvalidValueException was not happen on null input parameter");
		} catch (InvalidValueException ignored) {
		}
	}

	@DataProvider(name = "ip4OctetArray")
	public static Object[][] ip4OctetArray() {
		return new Object[][] { { new int[] { 0, 0, 0, 0 }, "0.0.0.0" }, { new int[] { 255, 0, 4, 0 }, "255.0.4.0" } };
	}

	@Test(dataProvider = "ip4OctetArray")
	public void toIPv4_test(int[] octets, String ip) throws AutomationUnitTestException {
		String currentIp = toIPv4(octets);
		SoftAssertUnitTest.assertTrueNow(
				currentIp != null,
				"valueOf result for octets [" + Arrays.toString(octets) + "] is null",
				"Verify that valueOf result is not null");
		SoftAssertUnitTest.assertTrueNow(
				ip.equals(currentIp),
				"IP for octets [" + Arrays.toString(octets) + "] is ["
						+ currentIp + "] and should be [" + ip + "]",
				"Verify that IP for octets [" + Arrays.toString(octets) + "] is [" + ip + "]");
	}

	@DataProvider(name = "invalidIp4OctetArray")
	public static Object[][] invalidIp4OctetArray() {
		return new Object[][] {
				{ new int[] { 0, 0, 0 } },
				{ new int[] { 0, 0, 0, 0, 0 } },
				{ new int[] { 256, 0, 0, 0 } },
				{ new int[] { 0, 0, -4, 0 } }
		};
	}

	@Test(dataProvider = "invalidIp4OctetArray")
	public void toIPv4_invalid_octets_negative_test(int[] octets) throws AutomationUnitTestException {
		String ip = toIPv4(octets);
		SoftAssertUnitTest.assertTrueNow(
				ip == null,
				"valueOf result for invalid octets [" + Arrays.toString(octets)
						+ "] is [" + ip + "] and should be null",
				"Verify that valueOf result for invalid octets is null");
	}
	
	@DataProvider(name = "ip4OctetArrayNotEqualToIp")
	public static Object[][] ip4OctetArrayNotEqualToIp() {
		return new Object[][] {
				{ new int[] { 0, 0, 0, 0 }, "0.0.0.1" },
				{ new int[] { 255, 0, 4, 0 }, "255.1.4.0" }
		};
	}

	@Test(dataProvider = "ip4OctetArrayNotEqualToIp")
	public void toIPv4_not_equal_to_result_negative_test(int[] octets, String ip) throws AutomationUnitTestException {
		String currentIp = toIPv4(octets);
		SoftAssertUnitTest.assertTrueNow(
				currentIp != null,
				"valueOf result for octets [" + Arrays.toString(octets) + "] is null",
				"Verify that valueOf result is not null");
		SoftAssertUnitTest.assertTrueNow(
				!ip.equals(currentIp),
				"IP for octets [" + Arrays.toString(octets)
						+ "] should not be equal to IP [" + ip + "]",
				"Verify that IP for octets [" + Arrays.toString(octets)
						+ "] is not equal to IP [" + ip + "]");
	}
	
	private boolean isValidIp(String ip) throws AutomationUnitTestException {
	    try {
            return Ip4Util.isValidIp(ip);
        } catch (InvalidValueException e) {
           throw new AutomationUnitTestException(e);
        }
	}
	
	private String toIPv4(int[] octets) throws AutomationUnitTestException {
	    try {
            return Ip4Util.toIPv4(octets);
        } catch (InvalidValueException e) {
            throw new AutomationUnitTestException(e);
        }
	}
}