package com.todo.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.todo.command.UserCmd;
import com.todo.domain.Role;
import com.todo.domain.UploadItem;
import com.todo.domain.User;
import com.todo.service.UserService;

@Controller
@RequestMapping("/upload")
public class UploadFormController {
	
	/**
	 * service.
	 */
	@Autowired
	private UserService service;
	
	/**
	 * userCmd.
	 */
	@Autowired
	UserCmd userCmd;
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String displayForm() {
		return "/admin/users";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String save(@ModelAttribute("uploadForm") UploadItem uploadForm,
			Model map) {
		try {
			List<MultipartFile> files = uploadForm.getFile();

			Collection<User> users = new ArrayList<User>();
			if (null != files && files.size() > 0) {

				MultipartFile multipartFile = files.get(0);
				InputStream inputStream = multipartFile.getInputStream();
				HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

				// Get first sheet from the workbook
				HSSFSheet sheet = workbook.getSheetAt(0);
				
				// Iterate through each rows from first sheet
				Iterator<Row> rowIterator = sheet.iterator();
				while (rowIterator.hasNext()) {
					Row row = rowIterator.next();
					User newUser = new User();
					// For each row, iterate through each columns
					Iterator<Cell> cellIterator = row.cellIterator();
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						
						switch (cell.getColumnIndex()) {
						case 0:
							newUser.setFirstName(cell.getStringCellValue());
							break;
						case 1:
							newUser.setLastName(cell.getStringCellValue());
							break;
						case 2:
							Role role = new Role();
							role.setRole(cell.getStringCellValue().equalsIgnoreCase("Admin")? 1 : 2);
							newUser.setRole(role);
							break;
						case 3:
							newUser.setMailId(cell.getStringCellValue());
							break;
						default:
							break;
						}
						
					}
					newUser.setUsername(newUser.getMailId().substring(0,newUser.getMailId().indexOf("@")));
					newUser.setPassword(userCmd.getPassword(newUser));
					newUser.setReqNewPwd(Boolean.TRUE);
					users.add(newUser);
				}
			}
			if(users.size() > 0){
				for (User user : users) {
					
					service.create(user);
				}
			}
			map.addAttribute("message", "Users Imported Successfully");
			return "/admin/users";
		} catch (IOException e) {
			map.addAttribute("message", e.getMessage());
			return "/admin/users";
		}
	}
}