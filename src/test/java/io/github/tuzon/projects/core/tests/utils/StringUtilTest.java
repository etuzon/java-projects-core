package io.github.tuzon.projects.core.tests.utils;

import java.util.Arrays;
import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.tuzon.projects.core.enums.ExpectTypeEnum;
import io.github.tuzon.projects.core.objects.ExpectObject;
import io.github.tuzon.projects.core.tests.asserts.SoftAssert;
import io.github.tuzon.projects.core.tests.base.BaseTest;
import io.github.tuzon.projects.core.utils.ListUtil;
import io.github.tuzon.projects.core.utils.StringUtil;

public class StringUtilTest extends BaseTest {
	private static final ExpectObject expect1 = new ExpectObject(ExpectTypeEnum.CONTAINS, "123");
	private static final ExpectObject expect2 = new ExpectObject(ExpectTypeEnum.EQUALS, "123abc");
	private static final ExpectObject falseExpect1 = new ExpectObject(ExpectTypeEnum.CONTAINS, "124");
	private static final ExpectObject falseExpect2 = new ExpectObject(ExpectTypeEnum.EQUALS, "123aabc");

	private final String input = "123abc";

	private final String str = "str123_test";

	@Test
	public void getContainList_test() {
		final List<String> containList = Arrays.asList("1111", "123", "test", "333");

		List<String> result = StringUtil.getContainList(str, containList);

		SoftAssert.assertTrueNow(result != null, "Result of getContainList should not be null",
				"Verify that resulf of getContainList is not null");

		final int resultListSize = 2;

		SoftAssert.assertTrueNow(result.size() == resultListSize,
				"Result of getContainList list [" + Arrays.toString(result.toArray()) + "] size is [" + result.size()
						+ "] and should be [" + resultListSize + "]",
				"Verify that result of getContainList list size is [" + resultListSize + "]");
	}

	@DataProvider(name = "expect")
	public static Object[][] expect() {
		return new Object[][] { { expect1, true }, { expect2, true }, { falseExpect1, false },
				{ falseExpect2, false } };
	}

	@Test(dataProvider = "expect")
	public void isExpect_test(ExpectObject expect, boolean expectedResult) {
		SoftAssert.assertTrueNow(StringUtil.isExpect(input, Arrays.asList(expect)) == expectedResult,
				"isExpect result of input string [" + input + "] for expect [" + expect.getType() + ","
						+ expect.getStr() + "] is [" + expectedResult + "] and should be [" + !expectedResult + "]",
				"Verify isExpect result of input string [" + input + "] for expect [" + expect.getType() + ","
						+ expect.getStr() + "] value is [" + expectedResult + "]");
	}

	@Test
	public void removeEmptyElementsFromList_test() {
		String str1 = "1";
		String str2 = "2";
		List<String> list = ListUtil.asList(str1, "", str2, null);
		List<String> resultList = StringUtil.removeEmptyElementsFromList(list);

		final int resultListSize = 2;

		SoftAssert.assertTrueNow(resultList.size() == resultListSize,
				"Result list [" + Arrays.toString(resultList.toArray())
						+ "] of removeEmptyElementsFromList with input listlist [" + Arrays.toString(list.toArray())
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
		SoftAssert.assertTrueNow(expectedResult.equals(result),
				"Result of removeLastChar with input string [" + str + "] is [" + result + "] and should be ["
						+ expectedResult + "]",
				"Verify that result of removeLastChar with input string [" + str + "] is [" + expectedResult + "]");
	}

	@Test
	public void replace_test() {
		String str = "1" + StringUtil.REPLACE_STR + "3" + StringUtil.REPLACE_STR + "5" + StringUtil.REPLACE_STR;
		String arg1 = "2";
		String arg2 = "4";
		String arg3 = "6";

		final String expectedResult = "123456";
		String result = "";
		try {
			result = StringUtil.replace(str, arg1, arg2, arg3);
		} catch (Exception e) {
			SoftAssert.failNow("replace should not return exception [" + e.getMessage() + "]");
		}

		SoftAssert.assertTrueNow(expectedResult.equals(result),
				"Result of replace is [" + result + "] and should be [" + expectedResult + "]");
	}
	
	private void verifyElementInIndex(List<String> resultList, int index, String expectedElement) {
		SoftAssert.assertTrueNow(resultList.get(index).equals(expectedElement),
				"Result list [" + Arrays.toString(resultList.toArray()) + "] element in index [" + index + "] is ["
						+ resultList.get(index) + "] and should be [" + expectedElement + "]");
	}
}