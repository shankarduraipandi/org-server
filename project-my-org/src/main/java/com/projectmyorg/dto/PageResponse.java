/**
 * 
 */
package com.projectmyorg.dto;

import com.projectmyorg.dto.response.BaseResponse;

/**
 * @author Shankar D
 *
 */
public class PageResponse extends BaseResponse {
	
	private Object data;
	private long totalRecordCounts;
	private int totalPages;
	
	/**
	 * @param data
	 * @param l
	 * @param totalPages
	 */
	public PageResponse(Object data, long totalRecordCounts, int totalPages) {
		super();
		this.data = data;
		this.totalRecordCounts = totalRecordCounts;
		this.totalPages = totalPages;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * @return the totalRecordCounts
	 */
	public long getTotalRecordCounts() {
		return totalRecordCounts;
	}
	/**
	 * @param totalRecordCounts the totalRecordCounts to set
	 */
	public void setTotalRecordCounts(long totalRecordCounts) {
		this.totalRecordCounts = totalRecordCounts;
	}
	/**
	 * @return the totalPages
	 */
	public int getTotalPages() {
		return totalPages;
	}
	/**
	 * @param totalPages the totalPages to set
	 */
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	
}