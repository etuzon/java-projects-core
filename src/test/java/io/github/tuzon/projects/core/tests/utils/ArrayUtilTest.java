package io.github.tuzon.projects.core.tests.utils;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.tuzon.projects.core.expections.InvalidValueException;
import io.github.tuzon.projects.core.tests.asserts.SoftAssert;
import io.github.tuzon.projects.core.tests.base.BaseTest;
import io.github.tuzon.projects.core.tests.exceptions.AutomationTestException;
import io.github.tuzon.projects.core.utils.ArrayUtil;

public class ArrayUtilTest extends BaseTest {
    String[] strArr = { "Zero", "One", "Two", "Three", "Four" };
    Integer[] intArr = { 1, 2, 3 };

    @DataProvider(name = "strArrIndex")
    public static Object[][] strArrIndex() {
        return new Object[][] { { "Zero", 0 }, { "One", 1 }, { "Two", 2 }, { "Three", 3 }, { "Four", 4 } };
    }

    @Test(dataProvider = "strArrIndex")
    public void getIndex_test(String arrElement, int index) throws AutomationTestException {
        int currentIndex;
        try {
            currentIndex = ArrayUtil.getIndex(strArr, arrElement);
        } catch (InvalidValueException e) {
            throw new AutomationTestException(e);
        }

        SoftAssert.assertTrueNow(
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
    public void isObjectInArray_Test(String element) throws AutomationTestException {
        SoftAssert.assertTrueNow(isObjectInArray(strArr, element), "[" + element + "] should be exist in strArr but it does not exist",
                "Verify that [" + element + "] exist in strArr");
    }

    @Test(dataProvider = "strArrObjectNegative")
    public void isObjectInArray_Negative_Test(String element) throws AutomationTestException {
        SoftAssert.assertTrueNow(isObjectInArray(strArr, element) == false,
                "[" + element + "] should not be exist in strArr but it exist",
                "Verify that [" + element + "] not exist in strArr");
    }

    private boolean isObjectInArray(String[] arr, String element) throws AutomationTestException {
        try {
            return ArrayUtil.isObjectInArray(strArr, element);
        } catch (InvalidValueException e) {
            throw new AutomationTestException(e);
        }
    }
}