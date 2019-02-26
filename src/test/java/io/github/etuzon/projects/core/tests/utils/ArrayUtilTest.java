package io.github.etuzon.projects.core.tests.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.etuzon.projects.core.expections.InvalidValueException;
import io.github.etuzon.projects.core.utils.ArrayUtil;
import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.base.BaseUnitTest;
import io.github.etuzon.unit.tests.exceptions.AutomationUnitTestException;

public class ArrayUtilTest extends BaseUnitTest {
    String[] strArr = { "Zero", "One", "Two", "Three", "Four" };
    Integer[] intArr = { 1, 2, 3 };

    @DataProvider(name = "strArrIndex")
    public static Object[][] strArrIndex() {
        return new Object[][] { { "Zero", 0 }, { "One", 1 }, { "Two", 2 }, { "Three", 3 }, { "Four", 4 } };
    }

    @Test(dataProvider = "strArrIndex")
    public void getIndex_test(String arrElement, int index) throws AutomationUnitTestException {
        int currentIndex;
        try {
            currentIndex = ArrayUtil.getIndex(strArr, arrElement);
        } catch (InvalidValueException e) {
            throw new AutomationUnitTestException(e);
        }

        SoftAssertUnitTest.assertTrueNow(
                currentIndex == index, "Element [" + arrElement + "] is in index [" + currentIndex
                        + "] and should be in index [" + index + "]",
                "Verify that element [" + arrElement + "] is in index [" + index + "]");
    }

    @DataProvider(name = "strArrObject")
    public static Object[][] strArrObject() {
        return new Object[][] { { "Zero" }, { "One" }, { "Four" } };
    }

    @DataProvider(name = "strArrObjectNegative")
    public static Object[][] strArrObjectNegative() {
        return new Object[][] { { "Zer" }, { "One1" }, { "123" }, { "" } };
    }

    @Test(dataProvider = "strArrObject")
    public void isObjectInArray_Test(String element) throws AutomationUnitTestException {
        SoftAssertUnitTest.assertTrueNow(isObjectInArray(strArr, element), "[" + element + "] should be exist in strArr but it does not exist",
                "Verify that [" + element + "] exist in strArr");
    }

    @Test(dataProvider = "strArrObjectNegative")
    public void isObjectInArray_Negative_Test(String element) throws AutomationUnitTestException {
        SoftAssertUnitTest.assertTrueNow(isObjectInArray(strArr, element) == false,
                "[" + element + "] should not be exist in strArr but it exist",
                "Verify that [" + element + "] not exist in strArr");
    }

    private boolean isObjectInArray(String[] arr, String element) throws AutomationUnitTestException {
        try {
            return ArrayUtil.isObjectInArray(strArr, element);
        } catch (InvalidValueException e) {
            throw new AutomationUnitTestException(e);
        }
    }
}