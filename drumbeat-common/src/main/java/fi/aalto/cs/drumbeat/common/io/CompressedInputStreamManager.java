package fi.aalto.cs.drumbeat.common.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipInputStream;

import org.apache.commons.io.FilenameUtils;

import fi.aalto.cs.drumbeat.common.string.StringUtils;

/**
 * An object which wraps an {@link InputStream}, which can be uncompressed or compressed with GZIP or ZIP formats.  
 *
 */
public class CompressedInputStreamManager {
	
	private final InputStream in;
	private final String fileExtension;
	private final String fileExtensionNoCompressionFormat;
	private String compressionFormat;
	
	
	public CompressedInputStreamManager(String filePath) throws FileNotFoundException {
		this(new FileInputStream(filePath), filePath);
	}

	public CompressedInputStreamManager(File file) throws FileNotFoundException {
		this(new FileInputStream(file), file.getName());
	}
	
	public CompressedInputStreamManager(InputStream in, String fileNameOrPathOrExtension) {
		this.in = in;
		
		String fileExtension = FilenameUtils.getExtension(fileNameOrPathOrExtension);
		switch (fileExtension) {
		case FileManager.FILE_EXTENSION_GZIP:
		case FileManager.FILE_EXTENSION_ZIP:
			compressionFormat = fileExtension;
			fileExtensionNoCompressionFormat = FilenameUtils.getExtension(FilenameUtils.getBaseName(fileNameOrPathOrExtension));
			fileExtension = fileExtensionNoCompressionFormat + "." + compressionFormat; 
			break;
		default:
			compressionFormat = StringUtils.EMPTY;
			fileExtensionNoCompressionFormat = fileExtension;
			break;
		}
		
		this.fileExtension = fileExtension;
		
	}
	
	public String getCompressionFormat() {
		return compressionFormat;
	}
	
	public String getFileExtension() {
		return fileExtension;
	}
	
	public String getFileExtensionNoCompressionFormat() {
		return fileExtensionNoCompressionFormat;
	}
	
	public InputStream getUncompressedInputStream() throws IOException {
		switch (compressionFormat) {
		case FileManager.FILE_EXTENSION_GZIP:
			return new GZIPInputStream(in);
		case FileManager.FILE_EXTENSION_ZIP:
			return new ZipInputStream(in);
		default:
			return in;
		}
	}		

}
