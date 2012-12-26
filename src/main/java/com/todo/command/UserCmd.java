/**
 * 
 */
package com.todo.command;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;
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
		String[] str = mailId.split("@");
		System.out.println(str[0]);
		return str[0];
	}

	/**
	 * @param newUser
	 * @return String
	 */
	public String getPassword(User user) {
		String password = null;
		map = new HashMap<String, ArrayList<String>>();
		GenerateRandomPassword randomPassword = new GenerateRandomPassword();
		PasswordEncoder encoder = new Md5PasswordEncoder();
		if (user != null) {
			password = randomPassword.getAlphaNumeric(10);
			ArrayList<String> ar = new ArrayList<String>();
			ar.add(user.getMailId());
			map.put("TO", ar);
			SendMail sms = new SendMail();
			sms.sendMail("Password for user '" + user.getUsername() + "' is '" + password + "'.", map,
					"Todo Project User Details");
		}
		return encoder.encodePassword(password, user.getUsername());

	}

}
