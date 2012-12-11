package com.todo.command;

import java.awt.Desktop;
import java.io.File;

public class OpenExcelCmd {
	
	public void downloadExcel(File string){
		try {
			Desktop.getDesktop().open(string);
		}
		catch(Exception k)
		{
		   
		}
	}

}
