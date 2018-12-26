package org.eltn.projects.core.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class FileUtil {
	private FileUtil() {
		throw new UnsupportedOperationException("Util cannot be instantiated");
	}

	public static String getFileNameFromPath(String path) {
		int index = getLastSlashIndex(path);

		if (index == -1) {
			return path;
		} else if (index == path.length() - 1) {
			return "";
		}

		return path.substring(index + 1);
	}

	public static String getParentDirectory(String path) {
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

	public static void deleteOldFiles(String directory, int olderInDays) {
		File folder = new File(directory);
		File[] fileArr = folder.listFiles();

		for (File file : fileArr) {
			if (file.isFile()) {
				long diffInMs = new Date().getTime() - file.lastModified();
				if (diffInMs > (long) olderInDays * DateUtil.MS_IN_DAY) {
					file.delete();
				}
			}
		}
	}

	public static List<String> getFileList(String directory, String extensionArr[]) {
		List<String> fileNameList = new ArrayList<String>();

		File folder = new File(directory);
		File[] fileArr = folder.listFiles();

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