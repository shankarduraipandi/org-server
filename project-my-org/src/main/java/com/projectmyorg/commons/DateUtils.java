/**
 * 
 */
package com.projectmyorg.commons;

import java.util.Calendar;
import java.util.Date;

/**
 * @author Shankar D
 *
 */
public final class DateUtils {
	
	private DateUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
	
	public static Date currentDate() {
		return new Date();
	}
	
	public static Date addMonths(Date date, int monthsToAdd) {
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH, monthsToAdd);
			return c.getTime();
		} else
			return null;
	}

}
