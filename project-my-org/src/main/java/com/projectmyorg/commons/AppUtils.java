/**
 * 
 */
package com.projectmyorg.commons;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


/**
 * @author Shankar D
 *
 */
public final class AppUtils {
	
	private AppUtils() {
		throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
	}
	
	public static String strUUID() {
		return UUID.randomUUID().toString();
	}
	
	public static Pageable getPageable(int page, int size, String sortFieldName, String sirtDirection) {
		if (page > 0)
			page = page - 1;

		if (sortFieldName != null) {
			Sort sort;
			if (sirtDirection != null)
				sort = Sort.by(Sort.Direction.fromString(sirtDirection), sortFieldName);
			else
				sort = Sort.by(sortFieldName);

			return PageRequest.of(page, size, sort);
		}
		return PageRequest.of(page, size);
	}

}
