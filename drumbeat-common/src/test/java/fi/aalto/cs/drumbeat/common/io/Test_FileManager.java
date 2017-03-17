package fi.aalto.cs.drumbeat.common.io;

import static org.junit.Assert.*;

import org.junit.Test;

public class Test_FileManager {

	@Test
	public void test_getCurrentDirectory() {
		String currentDirectory = FileManager.getCurrentDirectory();
		assertNotNull(currentDirectory);
		assertEquals(currentDirectory, System.getProperty("user.dir"));
	}

//	@Test
//	public void test_countLines() throws IOException {
//		long begin = System.nanoTime();
//		int count = FileManager.countLines("C:/DRUM/!git_local/DRUM/Test/Models/2016-02-18_Turva-2016-04-06/2016-02-18 Turva/LVI (HVAC)/20150427/LVI_optimized.ifc" );
////		int count = FileManager.countLines("C:/DRUM/!git_local/DRUM/Test/Models/2016-02-18_Turva-2016-04-06/2016-02-18 Turva/RAK (Structural)/20151204/RAK_IFC_Turva.ifc");
//		long end = System.nanoTime();
//		double timeEllapsed = (end - begin) / 1.e9; 
//		System.out.printf("Number of lines: %,d%nTime ellapsed: %.3fs ", count, timeEllapsed);
//	}	
	
	@Test
	public void test_appendFileExtensions() {
		
		assertEquals(
				"sample.nt.gz",
				FileManager.appendFileExtensions("sample", "nt", "", "gz", null));
		
		assertEquals(
				"sample.nt.gz",
				FileManager.appendFileExtensions("sample.nt", "nt", "gz"));
		
		assertEquals(
				"sample.nt.gz",
				FileManager.appendFileExtensions("sample.nt.gz", "nt", "gz"));
		
		assertEquals(
				"sample.nt.gz.nt",
				FileManager.appendFileExtensions("sample.nt.gz", "nt"));

		assertEquals(
				"sample.nt.gz",
				FileManager.appendFileExtensions("sample.nt.gz", null, ""));

	}

}
