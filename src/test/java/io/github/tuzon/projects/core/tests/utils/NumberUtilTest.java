package io.github.tuzon.projects.core.tests.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.tuzon.projects.core.tests.asserts.SoftAssert;
import io.github.tuzon.projects.core.tests.base.BaseTest;
import io.github.tuzon.projects.core.utils.NumberUtil;

public class NumberUtilTest extends BaseTest {

	private final int number = 55;
	private final int min = -1;
	private final int max = 70;
	
	@Test
	public void isNumberInRange_test() {
		SoftAssert.assertTrueNow(NumberUtil.isNumberInRange(number, min, max), 
				"Number [" + number + "] should be in range of [" + min + "," + max +"]",
				"Verify that Number [" + number + "] is in range of [" + min + "," + max + "]");
	}
	
	@Test
	public void isNumberInRange_negative_test() {
		final int outOfRange = this.min - 10;
		
		SoftAssert.assertTrueNow(NumberUtil.isNumberInRange(number, outOfRange, outOfRange) == false, 
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
		SoftAssert.assertTrueNow(expectedResult.equals(result), 
				"Result of intToString with input number [" + number + "] and digits amount [" + digits + "] is [" + result + "], and should be [" + expectedResult + "]", 
				"Verify that result of intToString with input number [" + number + "] and digits amount [" + digits + "] is [" + expectedResult + "]");		
	}
}