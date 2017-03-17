package fi.aalto.cs.drumbeat.common.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.FilenameUtils;

import fi.aalto.cs.drumbeat.common.string.StringUtils;

/**
 * An object which wraps an {@link OutputStream}, which can be uncompressed or compressed with GZIP or ZIP formats.  
 *
 */
public class CompressedOutputStreamManager {
	
	private final OutputStream out;
	private final String fileExtension;
	private final String fileExtensionNoCompressionFormat;
	private String compressionFormat;
	
	
	public CompressedOutputStreamManager(String filePath) throws IOException {
		this(FileManager.createFileOutputStream(filePath), filePath);
	}

	public CompressedOutputStreamManager(File file) throws FileNotFoundException {
		this(new FileOutputStream(file), file.getName());
	}
	
	public CompressedOutputStreamManager(OutputStream out, String fileNameOrPathOrExtension) {
		this.out = out;
		
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
	
	public OutputStream getCompressedOutputStream() throws IOException {
		switch (compressionFormat) {
		case FileManager.FILE_EXTENSION_GZIP:
			return new GZIPOutputStream(out);
		case FileManager.FILE_EXTENSION_ZIP:
			return new ZipOutputStream(out);
		default:
			return out;
		}
	}		

}
