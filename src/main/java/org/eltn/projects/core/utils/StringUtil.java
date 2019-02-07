package org.eltn.projects.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.eltn.projects.core.base.BaseObject;
import org.eltn.projects.core.expections.InvalidValueException;
import org.eltn.projects.core.objects.ExpectObject;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

/*****************************************
 * String utility.
 * 
 * @author Eyal Tuzon.
 *
 */
public final class StringUtil extends BaseObject {
    public static final String REPLACE_STR = "$REPLACE_STR$";

    private StringUtil() {
        throw new UnsupportedOperationException("Util cannot be instantiated");
    }

    /*****************************************
     * Remove empty elements from list.
     * 
     * @param list String List.
     * @return List that not contain empty String and null elements.
     */
    public static List<String> removeEmptyElementsFromList(List<String> list) {
        list.removeAll(Arrays.asList("", null));
        return list;
    }

    /*****************************************
     * Check if String meets at least one of excepts in expect list.
     * 
     * @param str Input String.
     * @param expectsList Expect list.
     * @return true if String meets at least one of excepts in expect list, else return false.
     */
    public static boolean isExpect(String str, List<ExpectObject> expectsList) {
        for (ExpectObject expect : expectsList) {
            if (expect.isExpect(str)) {
                return true;
            }
        }

        return false;
    }

    /*****************************************
     * Get String List for all the String elements from 'containList'
     * that are contained in 'str'.
     * 
     * @param str String
     * @param containList List of String elements that will be checked if they are contained in 'str'.
     * @return String List for all the String elements from 'containList'.
     */
    public static List<String> getContainList(String str, List<String> containList) {
        List<String> result = new ArrayList<String>();

        for (String contain : containList) {
            if (str.contains(contain)) {
                result.add(contain);
            }
        }

        return result;
    }

    /*****************************************
     * Split String when separator is char.
     * 
     * @param str String to be split.
     * @param ch Splitter char.
     * @return List of split String elements.
     */
    public static List<String> split(String str, char ch) {
        String arr[] = str.split(String.valueOf(ch), -1);
        return ListUtil.asList(arr);
    }

    /*****************************************
     * Replace String  templates with input agrs, when template is {@link StringUtil.REPLACE_STR}.
     * 
     * Example: str = "12345{@link StringUtil.REPLACE_STR}abc{@link StringUtil.REPLACE_STR}de"
     *          StringUtil.replace(str, "zxc", "qwe");
     *          output = "12345zxcabcqwede".
     * 
     * @param str String
     * @param args Arguments that will replace the templates StringUtil.REPLACE_STR
     * @return Replaced String
     * @throws InvalidValueException in case args == null, or there are no args,
     *         or the amount of args is different from the amount of {@link StringUtil.REPLACE_STR} in 'str'.
     */
    public static String replace(String str, String... args) throws InvalidValueException {
        if (args == null) {
            throw new InvalidValueException("args for [" + str + "] cannot be null");
        }

        int count = StringUtils.countMatches(str, REPLACE_STR);
        String argsArr[] = args.clone();

        if (argsArr.length == 0) {
            throw new InvalidValueException("Replace for [" + str + "] not contain any arguments to be replaced");
        }

        if (count != argsArr.length) {
            throw new InvalidValueException("Replace for [" + str + "] contain [" + argsArr.length
                    + "] args and should contain [" + count + "] args");
        }

        String result = str;

        for (String arg : argsArr) {
            int index = result.indexOf(REPLACE_STR);

            result = result.substring(0, index) + arg + result.substring(index + REPLACE_STR.length());
        }

        return result;
    }

    /*****************************************
     * Convert XML Element to String.
     * 
     * @param element JDOM XML Element.
     * @return String that represent JDOM XML Element.
     */
    public static String xmlToString(Element element) {
        return new XMLOutputter().outputString(element);
    }

    /*****************************************
     * Get Exception stacktrace in String representative.
     * 
     * @param e Exception
     * @return Exception stacktrace in String representative.
     */
    public static String getExceptionStacktrace(Exception e) {
        StringBuilder sb = new StringBuilder();

        for (StackTraceElement stackElement : e.getStackTrace()) {
            sb.append(stackElement.getClassName()).append(".").append(stackElement.getMethodName()).append("(")
                    .append(stackElement.getFileName()).append(":").append(stackElement.getLineNumber()).append(")\n");
        }

        return sb.toString();
    }

    /*****************************************
     * Remove last char from String.
     * 
     * @param str String.
     * @return Input String without last char. 
     *         In case input String is empty than result will be empty String.
     */
    public static String removeLastChar(String str) {
        if (str.length() <= 1) {
            return "";
        }

        return str.substring(0, str.length() - 1);
    }
}