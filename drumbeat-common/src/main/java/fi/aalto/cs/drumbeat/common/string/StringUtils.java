package fi.aalto.cs.drumbeat.common.string;

import java.util.Map;

import org.apache.commons.lang3.text.StrSubstitutor;

public class StringUtils {

	public static final char APOSTROPHE_CHAR = '\'';
	public static final char CLOSING_CURLY_BRACKET_CHAR = '}';
	public static final char CLOSING_SQUARE_BRACKET_CHAR = ']';
	public static final char CLOSING_ROUND_BRACKET_CHAR = ')';
	public static final char COLON_CHAR = ':';
	public static final char COMMA_CHAR = ',';
	public static final char DOLLAR_CHAR = '$';
	public static final char DOT_CHAR = '.';
	public static final char OPENING_CURLY_BRACKET_CHAR = '{';
	public static final char OPENING_SQUARE_BRACKET_CHAR = '[';
	public static final char OPENING_ROUND_BRACKET_CHAR = '(';
	public static final char SEMICOLON_CHAR = ';';
	public static final char SHARP_CHAR = '#';
	public static final char SLASH_CHAR = '/';
	public static final char SPACE_CHAR = ' ';
	public static final char STAR_CHAR = '*';
	public static final char QUESTION_CHAR = '?';
	public static final char TABULAR_CHAR = '\t';

	public static final String APOSTROPHE = "\'";
	public static final String CLOSING_CURLY_BRACKET = "}";
	public static final String CLOSING_ROUND_BRACKET = ")";
	public static final String CLOSING_SQUARE_BRACKET = "]";
	public static final String COLON = ":";
	public static final String COMMA = ",";
	public static final String DOLLAR = "$";
	public static final String EMPTY = "";
	public static final String END_LINE = String.format("%n");
	public static final String EQUAL = "=";
	public static final String OPENING_CURLY_BRACKET = "{";
	public static final String OPENING_ROUND_BRACKET = "(";
	public static final String OPENING_SQUARE_BRACKET = "[";
	public static final String SEMICOLON = ";";
	public static final String SHARP = "#";
	public static final String SLASH = "/";
	public static final String SPACE = " ";
	public static final String STAR = "*";
	public static final String QUESTION = "?";
	public static final String UNDERSCORE = "_";
	public static final String TABULAR = "\t";

	/**
	 * Converts the specified array of objects to a string with the specified
	 * prefix and suffix
	 * 
	 * @param <T>
	 *            the class of items in the array
	 * 
	 * @param array
	 *            the array of objects
	 * 
	 * @param prefix
	 *            the result string prefix
	 * 
	 * @param suffix
	 *            the result string suffix
	 * 
	 * @return a string that contains all the objects
	 * 
	 */
	public static <T> String arrayToString(T[] array, CharSequence prefix, CharSequence suffix) {

		StringBuilder stringBuilder = new StringBuilder();

		if (prefix != null) {
			stringBuilder.append(prefix);
		}

		if (array != null & array.length > 0) {
			boolean added = false;
			for (T t : array) {
				stringBuilder.append(t.toString());
				stringBuilder.append(COMMA);
				added = true;
			}
			if (added) {
				stringBuilder.deleteCharAt(stringBuilder.length() - 1);
			}
		}

		if (suffix != null) {
			stringBuilder.append(suffix);
		}

		return stringBuilder.toString();
	}

	/**
	 * Merges a collection of objects to a string using a common string format
	 * for each item
	 * 
	 * @param collection
	 *            the collection of objects to merge
	 * @param prefix
	 *            the result string prefix
	 * @param suffix
	 *            the result string suffix
	 * @param itemFormat
	 *            the string format applied for each item
	 * @param separator
	 *            the separator between each item
	 * 
	 * @return the merged string.
	 */
	public static String collectionToString(Iterable<?> collection, CharSequence prefix, CharSequence suffix,
			String itemFormat, String separator) {

		StringBuilder stringBuilder = new StringBuilder();

		if (prefix != null) {
			stringBuilder.append(prefix);
		}

		if (separator == null) {
			separator = COMMA;
		}

		if (collection != null) {
			boolean added = false;
			for (Object item : collection) {
				if (itemFormat == null) {
					stringBuilder.append(item.toString());
				} else {
					stringBuilder.append(String.format(itemFormat, item));
				}
				stringBuilder.append(separator);
				added = true;
			}
			if (added) {
				stringBuilder.delete(stringBuilder.length() - separator.length(), stringBuilder.length());
			}
		}

		if (suffix != null) {
			stringBuilder.append(suffix);
		}

		return stringBuilder.toString();
	}

	/**
	 * Gets the string between opening and closing brackets.
	 * 
	 * @param s
	 *            The s to parse
	 * @return An array with two strings: the first one is the string inside
	 *         brackets, the second one is the rest part of the string. null -
	 *         if s is not started with opening bracket or closing bracket is
	 *         not found.
	 */
	public static String[] getStringBetweenBrackets(String s) {

		if (s.charAt(0) != OPENING_ROUND_BRACKET_CHAR) {
			return null;
		}

		int balanceOfBrackets = 1;

		for (int i = 1; i < s.length(); ++i) {

			switch (s.charAt(i)) {
			case OPENING_ROUND_BRACKET_CHAR:
				++balanceOfBrackets;
				break;
			case CLOSING_ROUND_BRACKET_CHAR:
				--balanceOfBrackets;
				break;
			}

			if (balanceOfBrackets == 0) {

				String stringInsideBrackets = s.substring(1, i);

				if (i + 1 < s.length()) {
					return new String[] { stringInsideBrackets, s.substring(i + 1) };
				} else {
					return new String[] { stringInsideBrackets };
				}
			}

		}

		return null;
	}

	/**
	 * Gets the string between opening and closing brackets.
	 * 
	 * @param stringBuilder
	 *            the StringBuilder instance to parse
	 * @return An array with two strings: the first one is the string inside
	 *         brackets, the second one is the rest part of the string. null -
	 *         if s is not started with opening bracket or closing bracket is
	 *         not found.
	 */
	public static String getStringBetweenBrackets(StringBuilder stringBuilder) {

		if (stringBuilder.charAt(0) != OPENING_ROUND_BRACKET_CHAR) {
			return null;
		}

		int balanceOfBrackets = 1;

		for (int i = 1; i < stringBuilder.length(); ++i) {

			switch (stringBuilder.charAt(i)) {
			case OPENING_ROUND_BRACKET_CHAR:
				++balanceOfBrackets;
				break;
			case CLOSING_ROUND_BRACKET_CHAR:
				--balanceOfBrackets;
				break;
			}

			if (balanceOfBrackets == 0) {
				String stringBetweenBrackets = stringBuilder.substring(1, i);
				stringBuilder.delete(0, i);
				return stringBetweenBrackets;
			}

		}

		return null;
	}

	/**
	 * Finds the index of the first non-white-space character after the
	 * specified start position.
	 * 
	 * @param stringBuilder
	 *            the StringBuilder object
	 * @param start
	 *            the start position
	 * @return the index of the first non-white space character or the size of
	 *         the stringBuilder if no non-white-space character found.
	 */
	public static int skipWhiteSpaces(StringBuilder stringBuilder, int start) {
		for (; start < stringBuilder.length(); ++start) {
			if (!Character.isWhitespace(stringBuilder.codePointAt(start))) {
				break;
			}
		}
		return start;
	}

	/**
	 * Checks whether the specified string is empty or null
	 * 
	 * @param s
	 * 		the string to check
	 * 
	 * @return
	 * 		<code>true</code> if the s is empty or null, otherwise <code>false</code>.
	 */
	public static boolean isEmptyOrNull(String s) {
		return s == null || s.length() == 0;
	}
	
	
	public static String substituteVariables(String format, Map<String, String> variableMap) {
		if (format != null) {
			return StrSubstitutor.replace(format, variableMap);		
		} else {
			return null;
		}
	}
	

}
