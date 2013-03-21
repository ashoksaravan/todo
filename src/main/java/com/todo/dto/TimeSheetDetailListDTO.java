/**
 * 
 */
package com.todo.dto;

import java.util.List;

import com.todo.domain.TimeSheetDetail;

/**
 * @author vinodkumara
 *
 */
public class TimeSheetDetailListDTO {
	/**
	 * rows.
	 */
	private List<TimeSheetDetail> rows;

	/**
	 * total.
	 */
	private Integer total;

	/**
	 * page.
	 */
	private Integer page;

	/**
	 * records.
	 */
	private Integer records;

	/**
	 * @return the rows
	 */
	public List<TimeSheetDetail> getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<TimeSheetDetail> rows) {
		this.rows = rows;
	}

	/**
	 * @return the total
	 */
	public Integer getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the page
	 */
	public Integer getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(Integer page) {
		this.page = page;
	}

	/**
	 * @return the records
	 */
	public Integer getRecords() {
		return records;
	}

	/**
	 * @param records the records to set
	 */
	public void setRecords(Integer records) {
		this.records = records;
	}

}
