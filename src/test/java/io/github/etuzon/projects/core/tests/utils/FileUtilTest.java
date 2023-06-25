package io.github.etuzon.projects.core.tests.utils;

import java.io.File;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.etuzon.projects.core.expections.InvalidValueException;
import io.github.etuzon.projects.core.utils.FileUtil;
import io.github.etuzon.projects.core.utils.ListUtil;
import io.github.etuzon.unit.tests.asserts.SoftAssertUnitTest;
import io.github.etuzon.unit.tests.base.BaseUnitTest;
import io.github.etuzon.unit.tests.exceptions.AutomationUnitTestException;

public class FileUtilTest extends BaseUnitTest {
	private static final String FILE_WITH_EXTENSION = "file.txt";
	private static final String FILE_WITHOUT_EXTENSION = "file";

	private static final String PARENT_DIRECTORY = "dir1";

	private static final String RELATIVE_DIRECTORY = PARENT_DIRECTORY + "/dir2";
	private static final String BACK_SLASH_RELATIVE_DIRECTORY = PARENT_DIRECTORY + "\\dir2";

	private static final String RELATIVE_DIRECTORY_END_WITH_SLASH = RELATIVE_DIRECTORY + "/";

	private static final String RELATIVE_PATH_OF_FILE_WITH_EXTENSION =
			RELATIVE_DIRECTORY_END_WITH_SLASH + FILE_WITH_EXTENSION;
	private static final String ABSOLUTE_PATH_OF_FILE_WITH_EXTENSION =
			"/" + RELATIVE_PATH_OF_FILE_WITH_EXTENSION;
	private static final String RELATIVE_PATH_OF_FILE_WITHOUT_EXTENSION =
			RELATIVE_DIRECTORY_END_WITH_SLASH + FILE_WITHOUT_EXTENSION;
	private static final String ABSOLUTE_PATH_OF_FILE_WITHOUT_EXTENSION =
			"/" + RELATIVE_PATH_OF_FILE_WITHOUT_EXTENSION;

	private static final String FILE_WITH_EXTENSION_IN_ROOT_DIRECTORY =
			"/" + FILE_WITH_EXTENSION;
	private static final String FILE_WITHOUT_EXTENSION_IN_ROOT_DIRECTORY =
			"/" + FILE_WITHOUT_EXTENSION;

	private static final String RELATIVE_DIRECTORY_WITH_BACK_SLASH = PARENT_DIRECTORY + "\\dir2";

	private static final String RELATIVE_DIRECTORY_END_WITH_BACK_SLASH =
			RELATIVE_DIRECTORY_WITH_BACK_SLASH + "\\";
	private static final String BACK_SLASH_RELATIVE_PATH_OF_FILE_WITH_EXTENSION =
			RELATIVE_DIRECTORY_END_WITH_BACK_SLASH + FILE_WITH_EXTENSION;
	private static final String BACK_SLASH_ABSOLUTE_PATH_OF_FILE_WITH_EXTENSION = "\\"
			+ BACK_SLASH_RELATIVE_PATH_OF_FILE_WITH_EXTENSION;
	private static final String BACK_SLASH_RELATIVE_PATH_OF_FILE_WITHOUT_EXTENSION =
			RELATIVE_DIRECTORY_END_WITH_BACK_SLASH + FILE_WITHOUT_EXTENSION;
	private static final String BACK_SLASH_ABSOLUTE_PATH_OF_FILE_WITHOUT_EXTENSION = "\\"
			+ BACK_SLASH_RELATIVE_PATH_OF_FILE_WITHOUT_EXTENSION;

	private static final String BACK_SLASH_FILE_WITH_EXTENSION_IN_ROOT_DIRECTORY =
			"\\" + FILE_WITH_EXTENSION;
	private static final String BACK_SLASH_FILE_WITHOUT_EXTENSION_IN_ROOT_DIRECTORY =
			"\\" + FILE_WITHOUT_EXTENSION;

	private String getFileListPath = null;

	@BeforeClass
	public void beforeClass() {
		File file = new File("src/test/resources/FileUtilTest/getFileList");
		getFileListPath = file.getAbsolutePath();
	}

	@DataProvider(name = "fileNameInPath")
	public static Object[][] fileNameInPath() {
		return new Object[][] {
				{ RELATIVE_PATH_OF_FILE_WITH_EXTENSION, FILE_WITH_EXTENSION },
				{ ABSOLUTE_PATH_OF_FILE_WITH_EXTENSION, FILE_WITH_EXTENSION },
				{ RELATIVE_PATH_OF_FILE_WITHOUT_EXTENSION, FILE_WITHOUT_EXTENSION },
				{ ABSOLUTE_PATH_OF_FILE_WITHOUT_EXTENSION, FILE_WITHOUT_EXTENSION },
				{ FILE_WITH_EXTENSION, FILE_WITH_EXTENSION },
				{ FILE_WITHOUT_EXTENSION, FILE_WITHOUT_EXTENSION },
				{ FILE_WITH_EXTENSION_IN_ROOT_DIRECTORY, FILE_WITH_EXTENSION },
				{ FILE_WITHOUT_EXTENSION_IN_ROOT_DIRECTORY, FILE_WITHOUT_EXTENSION },
				{ BACK_SLASH_RELATIVE_PATH_OF_FILE_WITH_EXTENSION, FILE_WITH_EXTENSION },
				{ BACK_SLASH_ABSOLUTE_PATH_OF_FILE_WITH_EXTENSION, FILE_WITH_EXTENSION },
				{ BACK_SLASH_RELATIVE_PATH_OF_FILE_WITHOUT_EXTENSION, FILE_WITHOUT_EXTENSION },
				{ BACK_SLASH_ABSOLUTE_PATH_OF_FILE_WITHOUT_EXTENSION, FILE_WITHOUT_EXTENSION },
				{ BACK_SLASH_FILE_WITH_EXTENSION_IN_ROOT_DIRECTORY, FILE_WITH_EXTENSION },
				{ BACK_SLASH_FILE_WITHOUT_EXTENSION_IN_ROOT_DIRECTORY, FILE_WITHOUT_EXTENSION }
		};
	}

	@Test(dataProvider = "fileNameInPath")
	public void getFileNameFromPath_test(String path, String filename) throws AutomationUnitTestException {
		SoftAssertUnitTest.assertTrueNow(
				getFileNameFromPath(path).equals(filename),
				"File name [" + filename + "] was not found from path [" + path + "]",
				"Verify that file name [" + filename + "] will be received from path [" + path + "]");
	}

	@DataProvider(name = "noFileNameInPath")
	public static Object[][] noFileNameInPath() {
		return new Object[][] {
				{ RELATIVE_DIRECTORY_END_WITH_SLASH },
				{ RELATIVE_DIRECTORY_END_WITH_BACK_SLASH },
				{ "" },
				{ "/" },
				{ "\\" }
		};
	}

	@Test(dataProvider = "noFileNameInPath")
	public void getFileNameFromPath__noFileName_negative_test(String path) throws AutomationUnitTestException {
		String filename = getFileNameFromPath(path);
		SoftAssertUnitTest.assertTrueNow(
				filename.isEmpty(),
				"File name should be empty but it contain text [" + filename + "]",
				"Verify that file name is empty for path [" + path + "] that is end with slash");
	}

	@DataProvider(name = "parentDirectory")
	public static Object[][] parentDirectory() {
		return new Object[][] {
				{ RELATIVE_DIRECTORY_END_WITH_SLASH, PARENT_DIRECTORY },
				{ RELATIVE_DIRECTORY_END_WITH_BACK_SLASH, PARENT_DIRECTORY },
				{ BACK_SLASH_RELATIVE_PATH_OF_FILE_WITH_EXTENSION, BACK_SLASH_RELATIVE_DIRECTORY },
				{ RELATIVE_PATH_OF_FILE_WITH_EXTENSION, RELATIVE_DIRECTORY },
				{ BACK_SLASH_RELATIVE_PATH_OF_FILE_WITH_EXTENSION, BACK_SLASH_RELATIVE_DIRECTORY },
				{ "/", "" },
				{ "\\", "" },
				{ PARENT_DIRECTORY, "" }
		};
	}

	@Test(dataProvider = "parentDirectory")
	public void getParentDirectory_test(String path, String parentDirectory) throws AutomationUnitTestException {
		String currentParentDirectory = getParentDirectory(path);
		SoftAssertUnitTest.assertTrueNow(
				parentDirectory.equals(currentParentDirectory),
				"Parent directory of path [" + path + "] should be ["
						+ parentDirectory + "] but it is [" + currentParentDirectory + "]",
				"Verify that parent directory of path [" + path + "] is [" + parentDirectory + "]");
	}

	@Test
	public void getFileList_test() {
		final String[] extensionArr = { "txt", "ext", "noext" };
		final int expectedFileListSize = 2;

		List<String> fileList = FileUtil.getFileList(getFileListPath, extensionArr);

		String file1 = "file1.txt";
		String file2 = "file3.ext";

		SoftAssertUnitTest.assertTrue(
				fileList.contains(file1),
				"File [" + file1 + "] was not found in path [" + getFileListPath + "]",
				"Verify that file [" + file1 + "] exists in path [" + getFileListPath + "]");
		SoftAssertUnitTest.assertTrue(fileList.contains(file2),
				"File [" + file2 + "] was not found in path [" + getFileListPath + "]",
				"Verify that file [" + file2 + "] exists in path [" + getFileListPath + "]");

		SoftAssertUnitTest.assertTrue(
				fileList.size() == expectedFileListSize,
				"File list size in path [" + getFileListPath + "] is ["
						+ fileList.size() + "] but should be ["
						+ expectedFileListSize + "].\nFile list:\n" + ListUtil.getMultilineStringFromList(fileList),
				"Verify that file list size is [" + expectedFileListSize + "]");
	}
	
	private String getFileNameFromPath(String path) throws AutomationUnitTestException {
	    try {
            return FileUtil.getFileNameFromPath(path);
        } catch (InvalidValueException e) {
            throw new AutomationUnitTestException(e);
        }
	}
	
	private String getParentDirectory(String path) throws AutomationUnitTestException {
	    try {
            return FileUtil.getParentDirectory(path);
        } catch (InvalidValueException e) {
            throw new AutomationUnitTestException(e);
        }
	}
}