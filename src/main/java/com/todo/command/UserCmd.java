/**
 * 
 */
package com.todo.command;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.todo.controller.GenerateRandomPassword;
import com.todo.controller.SendMail;
import com.todo.domain.User;

/**
 * @author VINODKUMARA
 * 
 */
@Service
public class UserCmd {

	/**
	 * map.
	 */
	private HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

	/**
	 * @param mailId
	 * @return String
	 */
	public String getUserName(String mailId) {
		String str = mailId.substring(0,mailId.indexOf("@"));
		return str;
	}

	/**
	 * @param newUser
	 * @return String
	 */
	public String getPassword(User user) {
		String password = null;
		map = new HashMap<String, ArrayList<String>>();
		GenerateRandomPassword randomPassword = new GenerateRandomPassword();
		if (user != null) {
			password = randomPassword.getAlphaNumeric(10);
			ArrayList<String> ar = new ArrayList<String>();
			ar.add(user.getMailId());
			map.put("TO", ar);
			SendMail sms = new SendMail();
			sms.sendMail("You have been added into Todo"+"\n\n" + "Password for user '" + user.getUsername() + "' is '" + password + "'.", map,
					"Welcome to Todo");
		}
		return password;

	}
}
