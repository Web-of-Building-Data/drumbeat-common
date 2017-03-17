package fi.aalto.cs.drumbeat.common.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class FileManager {
	
	public static final String FILE_NAME_SEPARATOR = File.separator;
	
	public static final String FILE_EXTENSION_XML = "xml";

	public static final String FILE_EXTENSION_GZIP = "gz";
	public static final String FILE_EXTENSION_ZIP = "zip";

	/**
	 * Creates a directory with a specified path. If some parent directory does not exist then it is created as well.
	 * 
	 * @param directoryPath
	 * @return
	 */
	public static File createDirectory(String directoryPath) {
		File directory = new File(directoryPath);
		if (!directory.exists()) {
			directory.mkdir();
		}
		return directory;
	}
	
	
	
	public static String buildFilePath(String directoryPath, String fileName) {
		return directoryPath != null ? String.format("%s%s%s", directoryPath, File.separatorChar, fileName) : fileName; 
	}

	public static String buildFilePath(File directory, String fileName) {
		return directory != null ? buildFilePath(directory.getPath(), fileName) : fileName;
	}
	
	public static File createFile(String filePath) throws IOException {
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (parentFile != null && !parentFile.exists()) {
			parentFile.mkdirs();
		}
		return file;
	}
	
	
	public static FileWriter createFileWriter(String filePath) throws IOException {		
		File file = new File(filePath);
		File parentFile = file.getParentFile();
		if (parentFile != null) {
			parentFile.mkdirs();
		}
		return new FileWriter(file);
	}

	public static FileOutputStream createFileOutputStream(String filePath) throws IOException {
		File file = new File(filePath);
		file.getParentFile().mkdirs();
		return new FileOutputStream(file);
	}
	
	public static String getReadableFileSize(long size) {
	    if(size <= 0) return "0";
	    final String[] units = new String[] { "B", "KB", "MB", "GB", "TB" };
	    int digitGroups = (int) (Math.log10(size)/Math.log10(1024));
	    return new DecimalFormat("#,##0.#").format(size/Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}
	
//	public static String createFileNameWithExtension(String filePath, String extension) {
//		
//		String tempFilePath = filePath;
//		
//		String[] tokens = extension.split("\\.");
//		
//		int firstExtentionToBeAdded = tokens.length;
//		
//		for (int i = tokens.length - 1; i >= 0; --i) {
//			tokens[i] = "." + tokens[i];
//			if (tempFilePath.endsWith(tokens[i])) {
//				tempFilePath = tempFilePath.substring(0, tempFilePath.length() - tokens[i].length());
//			} else {
//				firstExtentionToBeAdded = i;
//			}
//		}
//		
//		for (int i = firstExtentionToBeAdded; i < tokens.length; ++i) {
//			filePath += tokens[i];
//		}
//		
//		return filePath;
//	}
	
	/**
	 * Gets the user's current directory
	 * 
	 * @return
	 */
	public static String getCurrentDirectory() {
		return System.getProperty("user.dir");
	}
	
	/**
	 * Gets the file name for the file with the specified file path
	 * 
	 * @param filePath
	 * @return
	 */
	public static String getFileName(String filePath) {
		File file = new File(filePath);
		return file.getName();
	}
	
	/**
	 * Checks if a file has a specified extension
	 *  
	 * @param filePath
	 * @param extension
	 * @return
	 */
	public static boolean checkFileHasExtension(String filePath, String extension) {
		String fileName = getFileName(filePath);
		int indexOfPoint = fileName.lastIndexOf('.');
		String fileExtension = indexOfPoint >= 0 ? fileName.substring(indexOfPoint + 1) : fileName;
		return fileExtension.equalsIgnoreCase(extension);
	}
	
	/**
	 * Checks if a file has any of specified extensions.
	 * 
	 * @param filePath
	 * @param extensions
	 * @return
	 */
	public static boolean checkFileHasAnyOfExtensions(String filePath, String... extensions) {
		String fileName = getFileName(filePath);
		int indexOfPoint = fileName.lastIndexOf('.');
		String fileExtension = indexOfPoint >= 0 ? fileName.substring(indexOfPoint + 1) : fileName;

		for (String extension : extensions) {
			if (fileExtension.equalsIgnoreCase(extension)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Removes the file extension from the file path
	 * 
	 * @param filePath
	 * @return
	 */
	public static String removeFileExtension(String filePath) {
		File file = new File(filePath);
		File parent = file.getParentFile();
		String fileName = file.getName();
		
		int index = fileName.lastIndexOf('.');
		if (index >= 0) {
			fileName = fileName.substring(0, index);
		}
		return parent != null ? buildFilePath(parent, fileName) : fileName;
	}

	public static String replaceFileExtension(String filePath, String newExtension) {
		return removeFileExtension(filePath) + "." + newExtension;
	}

}
