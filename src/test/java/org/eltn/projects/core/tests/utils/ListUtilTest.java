package org.eltn.projects.core.tests.utils;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.eltn.projects.core.tests.asserts.SoftAssert;
import org.eltn.projects.core.tests.base.BaseTest;
import org.eltn.projects.core.utils.ListUtil;
import org.testng.annotations.Test;

public final class ListUtilTest extends BaseTest {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private String str1 = "t1";
	private String str2 = "t2";
	private final List<String> strList = ListUtil.asList(str1, str2);
	
	private final List<String> containList = Arrays.asList("123456", "abc", "TEST");
	
	@Test
	public void asList_test() {
		SoftAssert.assertTrueNow(strList != null, "Result of asList should not be null",
				"Verify that result of asList is not null");

		SoftAssert.assertTrueNow(
				strList.size() == 2, "Result list size of asList is [" + strList.size()
						+ "] and should be 2. Result list [" + Arrays.toString(strList.toArray()) + "]",
				"Verify that result list siez of asList is [2]");
		
		verifyElementInListInIndexEqualToString(strList, 0, str1);
		verifyElementInListInIndexEqualToString(strList, 1, str2);
	}
	
	@Test
	public void asList_with_startIndex_test() {
		final Integer[] arr = { 1, 3, 6, 8 };
		final int startIndex = 2;

		logger.info(
				"Run ListUtil.asList with input array [" + Arrays.toString(arr) + "] and index [" + startIndex + "]");
		List<Integer> resultList = ListUtil.asList(arr, startIndex);

		SoftAssert.assertTrueNow(resultList != null, "Result of asList should not be null",
				"Verify that result of asList is not null");

		final int resultListSize = 2;

		SoftAssert.assertTrueNow(resultList.size() == resultListSize,
				"Result list size of asList with input array [" + Arrays.toString(arr) + "] and index [" + startIndex
						+ "] should be [" + resultListSize + "]. result list [" + Arrays.toString(resultList.toArray())
						+ "]",
				"Verify that result of asList with input array [" + Arrays.toString(arr) + "] and index [" + startIndex
						+ "] is [" + resultListSize + "]. result list [" + Arrays.toString(resultList.toArray()) + "]");

		for (int i=0; i < resultListSize; i++) {
			SoftAssert.assertTrueNow(resultList.get(i).equals(arr[startIndex + i]),
					"Element [" + resultList.get(i).intValue() + "] in result list index [" + i
							+ "] is different from element [" + arr[startIndex + i] + "] in input array index ["
							+ (startIndex + i) + "]",
					"Verify that element [" + resultList.get(i).intValue() + "] in result list index [" + i
							+ "] is equal to element [" + arr[startIndex + i] + "] in input array index ["
							+ (startIndex + i) + "]");
		}
	}

	@Test
	public void asList_with_startIndex_outOfBound_negative_test() {
		final Integer[] arr = { 1, 3, 6, 8 };
		final int startIndex = 20;

		logger.info(
				"Run ListUtil.asList with input array [" + Arrays.toString(arr) + "] and index [" + startIndex + "]");
		List<Integer> resultList = ListUtil.asList(arr, startIndex);

		SoftAssert.assertTrueNow(resultList != null, "Result of asList should not be null",
				"Verify that result of asList is not null");

		SoftAssert.assertTrueNow(resultList.isEmpty(),
				"Result list size of asList with input array [" + Arrays.toString(arr) + "] and index [" + startIndex
						+ "] should be empty. result list [" + Arrays.toString(resultList.toArray()) + "]",
				"Verify that result of asList with input array [" + Arrays.toString(arr) + "] and index [" + startIndex
						+ "] is empty list");
	}
	
	@Test
	public void getContainList_test() {
		final List<String> inputStrList = Arrays.asList("abc", "123");

		final List<String> resultContainList = ListUtil.getContainList(inputStrList, containList);

		SoftAssert.assertTrueNow(resultContainList != null, "getContainList should not return null",
				"Verify that getContainList not return null");

		SoftAssert.assertTrueNow(resultContainList.isEmpty() == false,
				"Result of getContainList with input list [" + Arrays.toString(inputStrList.toArray())
						+ "] and input contain list [" + Arrays.toString(containList.toArray())
						+ "] should not return empty list",
				"Verify that result of getContainList with input list [" + Arrays.toString(inputStrList.toArray())
						+ "] and input contain list [" + Arrays.toString(containList.toArray())
						+ "] not return empty list");

		logger.info("Contain list result is [" + Arrays.toString(resultContainList.toArray()) + "]");

		for (String contain : resultContainList) {
			SoftAssert.assertTrueNow(inputStrList.contains(contain),
					"Input list [" + Arrays.toString(inputStrList.toArray()) + "] not contains [" + contain
							+ "] element in list [" + Arrays.toString(containList.toArray()) + "]",
					"Verify that input list [" + Arrays.toString(inputStrList.toArray()) + "] contains [" + contain
							+ "] element in list [" + Arrays.toString(containList.toArray()) + "]");
		}
	}

	@Test
	public void getContainList_not_contain_negative_test() {
		final List<String> inputStrList = Arrays.asList("not_contain");

		final List<String> resultContainList = ListUtil.getContainList(inputStrList, containList);

		SoftAssert.assertTrueNow(resultContainList != null, "getContainList should not return null",
				"Verify that getContainList not return null");

		SoftAssert.assertTrueNow(resultContainList.isEmpty(),
				"Result of getContainList with input list [" + Arrays.toString(inputStrList.toArray())
						+ "] and input contain list [" + Arrays.toString(containList.toArray()) + "] is ["
						+ Arrays.toString(resultContainList.toArray()) + "] and should be empty list",
				"Verify that result of getContainList with input list [" + Arrays.toString(inputStrList.toArray())
						+ "] and input contain list [" + Arrays.toString(containList.toArray()) + "] is an empty list");
	}

	@Test
	public void getMultilineStringFromList_test() {
		String multiLineStrResult = ListUtil.getMultilineStringFromList(strList);
		final String expectedMultilineResult = str1 + "\n" + str2;
		
		SoftAssert.assertTrueNow(expectedMultilineResult.equals(multiLineStrResult), "getMultilineStringFromList result is [" + multiLineStrResult + "] and should be [" + expectedMultilineResult + "]",
				"Verify that getMultilineStringFromList result is [" + expectedMultilineResult  + "]");
	}
	
	private void verifyElementInListInIndexEqualToString(List<String> list, int index, String str) {
		SoftAssert.assertTrueNow(
				list.get(index).equals(str), "Element [" + list.get(index) + "] in result list in index [" + index + "] should be [" + str + "]",
				"Verify that element in index [" + index + "] in result list is equal to [" + str + "]");
	}
}