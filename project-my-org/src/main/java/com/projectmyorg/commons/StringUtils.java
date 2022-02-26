/**
 * 
 */
package com.projectmyorg.commons;

/**
 * @author Shankar D
 *
 */
public final class StringUtils {

	private StringUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}

	public static boolean isEmpty(String source) {
		return source == null || source.length() == 0;
	}
}
