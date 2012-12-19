package com.todo.command;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

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

import com.todo.domain.Priority;
import com.todo.domain.Project;
import com.todo.domain.Task;
import com.todo.domain.TaskStatus;

/**
 * @author vinodkumara
 *
 */
public class WriteExcelCmd extends AbstractExcelView {

	/**
	 * priorities.
	 */
	private List<Priority> priorities;
	
	/**
	 * projects.
	 */
	private List<Project> projects;
	
	/**
	 * status.
	 */
	private List<TaskStatus> status;

	/**
	 * map.
	 */
	private HashMap<String, String> map = new HashMap<String, String>();

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.document.AbstractExcelView#buildExcelDocument(java.util.Map, org.apache.poi.hssf.usermodel.HSSFWorkbook, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@SuppressWarnings("unchecked")
	protected void buildExcelDocument(@SuppressWarnings("rawtypes") Map model, HSSFWorkbook workbook,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		getRefData(request);
		HttpSession session = request.getSession();
		List<Task> tasks = (List<Task>) session.getAttribute("tasks");
		HSSFSheet sheet = null;
		int index = 1;
		try {
			sheet = workbook.createSheet("TaskDetails");

			HSSFRow headerRow = sheet.createRow(0);
			HSSFCellStyle cellStyle = setHeaderStyle(workbook);
			if (tasks.size() > 0) {
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
			for (Task task : tasks) {

				HSSFRow dataRow = sheet.createRow(index);
				dataRow.createCell(0).setCellValue(map.get(task.getProjectId().toString()));
				dataRow.createCell(1).setCellValue(task.getTaskname());
				dataRow.createCell(2).setCellValue(task.getTaskdesc());
				dataRow.createCell(3).setCellValue(map.get(task.getTaskstatus()));
				dataRow.createCell(4).setCellValue(map.get(task.getPriority()));
				dataRow.createCell(5).setCellValue(task.getCreateduser());
				dataRow.createCell(6).setCellValue(task.getUsername());
				index++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
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
		
		priorities = (List<Priority>) context.getAttribute("priorityList");
		for (Iterator<Priority> iterator = priorities.iterator(); iterator.hasNext();) {
			Priority priority = (Priority) iterator.next();
			map.put(priority.getValue(), priority.getDesc());

		}
		projects = (List<Project>) context.getAttribute("projectList");
		for (Iterator<Project> iterator = projects.iterator(); iterator.hasNext();) {
			Project project = (Project) iterator.next();
			map.put(project.getProjectId().toString(), project.getProjectDesc());
		}
		
		status = (List<TaskStatus>) context.getAttribute("taskStatusList");
		for (Iterator<TaskStatus> iterator = status.iterator(); iterator.hasNext();) {
			TaskStatus taskStatus = (TaskStatus) iterator.next();
			map.put(taskStatus.getValue(), taskStatus.getDesc());
		}

	}

}
