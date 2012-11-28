package com.todo.command;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.controller.GenerateRandomPassword;
import com.todo.controller.SendMail;
import com.todo.domain.User;
import com.todo.service.UserService;

@Service
public class ForgotPasswordCmd {
	
	@Autowired
	private UserService service;
	
	static String password = new String();
	
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	
	public Boolean forgotPassword(String username){
		map = new HashMap<String, ArrayList<String>>();
		User user = new User();
		GenerateRandomPassword randomPassword = new GenerateRandomPassword();
		user = service.read(username);
		if (user != null) {
			password = randomPassword.getAlphaNumeric(10);
			ArrayList<String> ar = new ArrayList<String>();
			ar.add(user.getMailId());
			map.put("TO", ar);
			user.setPassword(password);
			Boolean pwdUpdate = service.updatePwd(user);
			if (pwdUpdate) {
				SendMail sms = new SendMail();
				sms.Sendmail("Password for user '" + username + "' is '"
						+ password + "'.", map, "Change Password");
				return true;
			}
		}
		return false;
	}
}
