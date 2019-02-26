package io.github.etuzon.projects.core.tests.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.etuzon.projects.core.utils.NumberUtil;
import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.base.BaseUnitTest;

public class NumberUtilTest extends BaseUnitTest {

	private final int number = 55;
	private final int min = -1;
	private final int max = 70;
	
	@Test
	public void isNumberInRange_test() {
		SoftAssertUnitTest.assertTrueNow(NumberUtil.isNumberInRange(number, min, max), 
				"Number [" + number + "] should be in range of [" + min + "," + max +"]",
				"Verify that Number [" + number + "] is in range of [" + min + "," + max + "]");
	}
	
	@Test
	public void isNumberInRange_negative_test() {
		final int outOfRange = this.min - 10;
		
		SoftAssertUnitTest.assertTrueNow(NumberUtil.isNumberInRange(number, outOfRange, outOfRange) == false, 
				"Number [" + number + "] should not be in range of [" + outOfRange + "," + outOfRange +"]",
				"Verify that Number [" + number + "] is not in range of [" + outOfRange + "," + outOfRange + "]");
	}
	
	@DataProvider(name = "numberToString")
	public static Object[][] numberToString() {
		return new Object[][] { { 51, 2, "51" }, { 43, 1, "3" }, { 24, 3, "024" } };
	}

	@Test(dataProvider = "numberToString")
	public void intToString_test(int number, int digits, String expectedResult) {		
		String result = NumberUtil.intToString(number, digits);
		SoftAssertUnitTest.assertTrueNow(expectedResult.equals(result), 
				"Result of intToString with input number [" + number + "] and digits amount [" + digits + "] is [" + result + "], and should be [" + expectedResult + "]", 
				"Verify that result of intToString with input number [" + number + "] and digits amount [" + digits + "] is [" + expectedResult + "]");		
	}
}