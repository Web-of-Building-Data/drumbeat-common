package fi.aalto.cs.drumbeat.common.uri;

import fi.aalto.cs.drumbeat.common.string.StringUtils;

/**
 * URI Utils
 * 
 * @author Nam Vu
 *
 */
public class UriUtils {

	/**
	 * Gets the local name of the URI (the substring after the last character
	 * '/' or the whole URI if no character '/' found).
	 * 
	 * @param uri
	 *            the URI
	 * 
	 * @return the URI's local name
	 */
	public static String getLocalName(String uri) {
		int index = uri.lastIndexOf(StringUtils.SLASH_CHAR);
		return index >= 0 ? uri.substring(index + 1) : uri;
	}

	/**
	 * Gets the path of the URI (the substring before the last slash character).
	 * 
	 * @param uri
	 *            the URI
	 * 
	 * @return the URI's local path
	 */
	public static String getPath(String uri) {
		int index = uri.lastIndexOf(StringUtils.SLASH_CHAR);
		for (--index; index >= 0; --index) {
			if (uri.charAt(index) != StringUtils.SLASH_CHAR) {
				return uri.substring(0, index + 1);
			}
		}
		return "";
	}

}
