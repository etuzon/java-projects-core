package org.eltn.projects.core.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.eltn.projects.core.base.ObjectBase;
import org.eltn.projects.core.expections.FileNotDeletedException;
import org.eltn.projects.core.expections.InvalidValueException;

/*********************************************
 * Operations on files and directories utility.
 * 
 * @author Eyal Tuzon
 *
 */
public final class FileUtil extends ObjectBase {
	private FileUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	/*********************************************
	 * Get file name from input path.
	 * 
	 * @param path Input path.
	 * @return File name.
	 * @throws InvalidValueException in case path is null.
	 */
	public static String getFileNameFromPath(String path) throws InvalidValueException {
	    validateNotNull(path);

		int index = getLastSlashIndex(path);

		if (index == -1) {
			return path;
		} else if (index == path.length() - 1) {
			return "";
		}

		return path.substring(index + 1);
	}

	/*********************************************
	 * Get parent directory of path.
	 * @param path Path.
	 * @return Parent directory of path.
	 * @throws InvalidValueException in case path is null.
	 */
	public static String getParentDirectory(String path) throws InvalidValueException {
	    validateNotNull(path);
	    
		if (path.isEmpty()) {
			return "";
		}

		int index = getLastSlashIndex(path);

		if (index == -1) {
			return "";
		}

		if (index == path.length() - 1) {
			path = path.substring(0, index);
			return (getParentDirectory(path));
		}

		return path.substring(0, index);
	}

	/*********************************************
	 * Delete files in directory that are older from input number of days.
	 * 
	 * @param directory Directory path.
	 * @param olderInDays Number of days that files should be older to be deleted.
	 * @throws FileNotDeletedException in case file is not deleted.
	 */
	public static void deleteOldFiles(String directory, int olderInDays) throws FileNotDeletedException {
		File folder = new File(directory);
		File[] fileArr = folder.listFiles();

		if (fileArr == null) {
			return;
		}

		List<String> filesFailedToBeDeletedList = new ArrayList<String>();

		for (File file : fileArr) {
			if (file.isFile()) {
				long diffInMs = new Date().getTime() - file.lastModified();
				if (diffInMs > (long) olderInDays * DateUtil.MS_IN_DAY) {
					try {
						file.delete();
					} catch (SecurityException e) {
						filesFailedToBeDeletedList.add(file.getName());
					}
				}
			}
		}

		if (filesFailedToBeDeletedList.isEmpty() == false) {
			throw new FileNotDeletedException(ListUtil.getMultilineStringFromList(filesFailedToBeDeletedList));
		}
	}

	/*********************************************
	 * Get file list in directory and have an extension from the extensions array.
	 * 
	 * @param directory Directory path.
	 * @param extensionArr Array of extensions.
	 * @return File list in directory that have extension from the extensions array.
	 */
	public static List<String> getFileList(String directory, String extensionArr[]) {
		List<String> fileNameList = new ArrayList<String>();

		File folder = new File(directory);
		File[] fileArr = folder.listFiles();

		if (fileArr == null) {
			return fileNameList;
		}

		for (File file : fileArr) {
			if (file.isFile()) {
				fileNameList.add(getFileNameContainExtension(file.getName(), extensionArr));
			}
		}

		fileNameList.removeAll(Arrays.asList(null, ""));

		return fileNameList;
	}

	private static String getFileNameContainExtension(String filename, String extensionArr[]) {
		filename = filename.trim();

		if ((filename.indexOf(".") > 0) && (filename.indexOf(".") < filename.length() - 1)) {
			for (String extension : extensionArr) {
				int index = filename.indexOf("." + extension);
				if (index > 0) {
					return filename;
				}
			}
		}

		return null;
	}

	private static int getLastSlashIndex(String path) {
		return Math.max(path.lastIndexOf("/"), path.lastIndexOf("\\"));
	}
}