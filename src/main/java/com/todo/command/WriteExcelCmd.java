package com.todo.command;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.IndexedColors;

import com.todo.domain.Task;

public class WriteExcelCmd {
	

	public void writeExcelFile(Task[] data, HashMap<String, String> map) {
		FileOutputStream fileOutputStream = null;
		HSSFWorkbook workbook = null;
		HSSFSheet sheet = null;
		int index = 1;
		try {
			workbook = new HSSFWorkbook();
			sheet = workbook.createSheet("TaskDetails");

			HSSFRow headerRow = sheet.createRow(0);
			HSSFCellStyle cellStyle = setHeaderStyle(workbook);
			if (data.length > 0) {
				HSSFCell headerCell = headerRow.createCell(0);
				headerCell.setCellStyle(cellStyle);
				headerCell.setCellValue("ProjectId");

				HSSFCell headerCell1 = headerRow.createCell(1);
				headerCell1.setCellStyle(cellStyle);
				headerCell1.setCellValue("TaskName");

				HSSFCell headerCell2 = headerRow.createCell(2);
				headerCell2.setCellStyle(cellStyle);
				headerCell2.setCellValue("TaskDescription");

				HSSFCell headerCell3 = headerRow.createCell(3);
				headerCell3.setCellStyle(cellStyle);
				headerCell3.setCellValue("TaskStatus");

				HSSFCell headerCell4 = headerRow.createCell(4);
				headerCell4.setCellStyle(cellStyle);
				headerCell4.setCellValue("Priority");

				HSSFCell headerCell5 = headerRow.createCell(5);
				headerCell5.setCellStyle(cellStyle);
				headerCell5.setCellValue("Author");

				HSSFCell headerCell6 = headerRow.createCell(6);
				headerCell6.setCellStyle(cellStyle);
				headerCell6.setCellValue("AssignedTo");
			}

			for (int i = 0; i < data.length; i++) {
				Task task = (Task) data[i];
				HSSFRow dataRow = sheet.createRow(index);
				dataRow.createCell(0).setCellValue(map.get(task.getProjectId().toString()));
				dataRow.createCell(1).setCellValue(task.getTaskname());
				dataRow.createCell(2).setCellValue(task.getTaskdesc());
				dataRow.createCell(3).setCellValue(task.getTaskstatus());
				dataRow.createCell(4).setCellValue(map.get(task.getPriority()));
				dataRow.createCell(5).setCellValue(task.getCreateduser());
				dataRow.createCell(6).setCellValue(task.getUsername());
				index++;
			}
			fileOutputStream = new FileOutputStream("C:\\Task.xls");
			workbook.write(fileOutputStream);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

	private HSSFCellStyle setHeaderStyle(HSSFWorkbook workbook) {
		HSSFFont font = workbook.createFont();
		font.setFontName(HSSFFont.FONT_ARIAL);
		font.setColor(IndexedColors.BLUE.getIndex());
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		HSSFCellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setFont(font);
		return cellStyle;
	}
}
