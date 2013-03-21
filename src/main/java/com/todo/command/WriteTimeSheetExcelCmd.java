/**
 * 
 */
package com.todo.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.todo.domain.Acf2Id;
import com.todo.domain.TimeSheetDetail;

/**
 * @author vinodkumara
 * 
 */
public class WriteTimeSheetExcelCmd extends AbstractExcelView {

	/**
	 * map.
	 */
	private static HashMap<String, HashMap<String, String>> map = new HashMap<String, HashMap<String, String>>();

	/**
	 * acf2IdLst.
	 */
	private List<Acf2Id> acf2IdLst;

	/**
	 * idMap.
	 */
	private HashMap<String, String> idMap = new HashMap<String, String>();

	/**
	 * headerMap.
	 */
	private HashMap<Integer, String> headerMap = new HashMap<Integer, String>();

	/**
	 * cellMap.
	 */
	private HashMap<String, String> cellMap = new HashMap<String, String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#
	 * buildExcelDocument(java.util.Map,
	 * org.apache.poi.hssf.usermodel.HSSFWorkbook,
	 * javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(@SuppressWarnings("rawtypes") Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		getRefData(request);
		HttpSession session = request.getSession();
		List<TimeSheetDetail> tasks = new ArrayList<TimeSheetDetail>();
		List<String> strings = new ArrayList<String>();
		tasks = (List<TimeSheetDetail>) session.getAttribute("timesheet");
		session.removeAttribute("timesheet");
		strings = (List<String>) session.getAttribute("header");
		session.removeAttribute("header");
		HSSFSheet sheet = null;
		int rowIndex = 1;
		int headerIndex = 2;
		try {
			sheet = workbook.createSheet("WebTime");

			HSSFRow headerRow = sheet.createRow(0);
			HSSFCellStyle cellStyle = setHeaderStyle(workbook);
			if (null != tasks && tasks.size() > 0) {
				formWebtime(tasks);
				HSSFCell headerCell = headerRow.createCell(0);
				headerCell.setCellStyle(cellStyle);
				headerCell.setCellValue("Name");
				HSSFCell acf2IdCell = headerRow.createCell(1);
				acf2IdCell.setCellStyle(cellStyle);
				acf2IdCell.setCellValue("Acf2ID");

				for (String str : strings) {
					HSSFCell headerCell1 = headerRow.createCell(headerIndex);
					headerCell1.setCellStyle(cellStyle);
					headerCell1.setCellValue(str);
					headerMap.put(headerIndex, str);
					headerIndex++;
				}
				Iterator<Entry<String, HashMap<String, String>>> iterator = map.entrySet().iterator();
				while (iterator.hasNext()) {
					HSSFRow dataRow = sheet.createRow(rowIndex);
					Map.Entry<String, HashMap<String, String>> entry = (Map.Entry<String, HashMap<String, String>>) iterator
							.next();
					dataRow.createCell(0).setCellValue(entry.getKey());
					dataRow.createCell(1).setCellValue(idMap.get(entry.getKey()));
					Iterator<Entry<String, String>> iterator1 = entry.getValue().entrySet().iterator();
					while (iterator1.hasNext()) {
						Map.Entry<String, String> entry2 = (Map.Entry<String, String>) iterator1.next();
						Iterator<Entry<Integer, String>> headIterator = headerMap.entrySet().iterator();
						while (headIterator.hasNext()) {
							Map.Entry<Integer, String> entry3 = (Map.Entry<Integer, String>) headIterator.next();
							if (entry3.getValue().equals(entry2.getKey())) {
								dataRow.createCell(entry3.getKey()).setCellValue(entry2.getValue());
							}
						}
					}
					rowIndex++;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
		}

	}

	/**
	 * @param tasks
	 */
	private void formWebtime(List<TimeSheetDetail> tasks) {
		map = new HashMap<String, HashMap<String, String>>();
		for (TimeSheetDetail timeSheetDetail : tasks) {
			if (!map.containsKey(timeSheetDetail.getUsername())) {
				cellMap = new HashMap<String, String>();
				for (TimeSheetDetail timeDetail : tasks) {
					if (!cellMap.containsKey(timeDetail.getDate())
							&& timeSheetDetail.getUsername().equals(timeDetail.getUsername())) {
						cellMap.put(timeDetail.getDate(), timeDetail.getBuffer());
					}
				}
				map.put(timeSheetDetail.getUsername(), cellMap);
			}
		}
	}

	/**
	 * @param workbook
	 * @return HSSFCellStyle
	 */
	private HSSFCellStyle setHeaderStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		return cellStyle;
	}

	/**
	 * @param request
	 */
	@SuppressWarnings("unchecked")
	private void getRefData(HttpServletRequest request) {
		ServletContext context = request.getSession().getServletContext();

		acf2IdLst = (List<Acf2Id>) context.getAttribute("acf2IdList");
		for (Iterator<Acf2Id> iterator = acf2IdLst.iterator(); iterator.hasNext();) {
			Acf2Id acf2Id = (Acf2Id) iterator.next();
			idMap.put(acf2Id.getUsername(), acf2Id.getAcf2Id());
		}
	}
}
