package fi.aalto.cs.drumbeat.common.file;

import static org.junit.Assert.*;

import org.junit.Test;

public class FileManager_ReplaceFileExtension_Test {
	

	@Test
	public void test_getCurrentDirectory() {
		String currentDirectory = FileManager.getCurrentDirectory();
		assertNotNull(currentDirectory);
		assertEquals(currentDirectory, System.getProperty("user.dir"));
	}

	@Test
	public void test_removeLastExtension() {
		String currentDirectory = FileManager.getCurrentDirectory();
		assertNotNull(currentDirectory);
		assertEquals(System.getProperty("user.dir"), currentDirectory);
		
		String filePath = FileManager.buildFilePath(currentDirectory + ".test", "myFile.abc.xyz");
		String filePathWithRemovedExtension = FileManager.removeFileExtension(filePath);
		String fileName = FileManager.getFileName(filePathWithRemovedExtension);
		assertEquals("myFile.abc", fileName);
	}

}
