package io.github.etuzon.projects.core.tests.utils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.etuzon.projects.core.enums.ExpectTypeEnum;
import io.github.etuzon.projects.core.objects.ExpectObject;
import io.github.etuzon.projects.core.utils.ListUtil;
import io.github.etuzon.projects.core.utils.StringUtil;
import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.base.BaseUnitTest;

public class StringUtilTest extends BaseUnitTest {
	private static final ExpectObject expect1 = new ExpectObject(ExpectTypeEnum.CONTAINS, "123");
	private static final ExpectObject expect2 = new ExpectObject(ExpectTypeEnum.EQUALS, "123abc");
	private static final ExpectObject falseExpect1 = new ExpectObject(ExpectTypeEnum.CONTAINS, "124");
	private static final ExpectObject falseExpect2 = new ExpectObject(ExpectTypeEnum.EQUALS, "123aabc");

	@Test
	public void getContainList_test() {
		final List<String> containList = Arrays.asList("1111", "123", "test", "333");

		String str = "str123_test";
		List<String> result = StringUtil.getContainList(str, containList);

		SoftAssertUnitTest.assertTrueNow(
				!result.isEmpty(),
 	       		"Result of getContainList should not be null",
				"Verify that result of getContainList is not null");

		final int resultListSize = 2;

		SoftAssertUnitTest.assertTrueNow(
	   			result.size() == resultListSize,
				"Result of getContainList list [" + Arrays.toString(result.toArray())
			 	 + "] size is [" + result.size() + "] and should be [" + resultListSize + "]",
		    	"Verify that result of getContainList list size is [" + resultListSize + "]");
	}

	@DataProvider(name = "expect")
	public static Object[][] expect() {
		return new Object[][] {
				{ expect1, true },
				{ expect2, true },
				{ falseExpect1, false },
				{ falseExpect2, false }
		};
	}

	@Test(dataProvider = "expect")
	public void isExpect_test(ExpectObject expect, boolean expectedResult) {
		String input = "123abc";
		SoftAssertUnitTest.assertTrueNow(
				StringUtil.isExpect(input, Collections.singletonList(expect)) == expectedResult,
				"isExpect result of input string [" + input + "] for expect ["
				+ expect.type() + "," + expect.str() + "] is [" + expectedResult
				+ "] and should be [" + !expectedResult + "]",
				"Verify isExpect result of input string [" + input + "] for expect ["
				+ expect.type() + "," + expect.str() + "] value is [" + expectedResult + "]");
	}

	@Test
	public void removeEmptyElementsFromList_test() {
		String str1 = "1";
		String str2 = "2";
		List<String> list = ListUtil.asList(str1, "", str2, null);
		List<String> resultList = StringUtil.removeEmptyElementsFromList(list);

		final int resultListSize = 2;

		SoftAssertUnitTest.assertTrueNow(
				resultList.size() == resultListSize,
				"Result list [" + Arrays.toString(resultList.toArray())
				+ "] of removeEmptyElementsFromList with input list [" + Arrays.toString(list.toArray())
				+ "] size is [" + resultList.size() + "] and should be [" + resultListSize + "]");

		verifyElementInIndex(resultList, 0, str1);
		verifyElementInIndex(resultList, 1, str2);
	}

	@DataProvider(name = "removeLastResult")
	public static Object[][] removeLastResult() {
		return new Object[][] { { "123", "12" }, { "", "" }, { "1", "" } };
	}

	@Test(dataProvider = "removeLastResult")
	public void removeLastChar_test(String str, String expectedResult) {
		String result = StringUtil.removeLastChar(str);
		SoftAssertUnitTest.assertTrueNow(
				expectedResult.equals(result),
				"Result of removeLastChar with input string [" + str + "] is [" + result
				+ "] and should be [" + expectedResult + "]",
				"Verify that result of removeLastChar with input string ["
				+ str + "] is [" + expectedResult + "]");
	}

	@Test
	public void replace_test() {
		String str = "1" + StringUtil.REPLACE_STR + "3"
				     + StringUtil.REPLACE_STR + "5" + StringUtil.REPLACE_STR;
		String arg1 = "2";
		String arg2 = "4";
		String arg3 = "6";

		final String expectedResult = "123456";
		String result = "";
		try {
			result = StringUtil.replace(str, arg1, arg2, arg3);
		} catch (Exception e) {
			SoftAssertUnitTest.failNow(
					"replace should not return exception [" + e.getMessage() + "]");
		}

		SoftAssertUnitTest.assertTrueNow(
				expectedResult.equals(result),
				"Result of replace is [" + result + "] and should be [" + expectedResult + "]");
	}

	private void verifyElementInIndex(List<String> resultList, int index, String expectedElement) {
		SoftAssertUnitTest.assertTrueNow(
				resultList.get(index).equals(expectedElement),
				"Result list [" + Arrays.toString(resultList.toArray())
				+ "] element in index [" + index + "] is ["
				+ resultList.get(index) + "] and should be [" + expectedElement + "]");
	}
}