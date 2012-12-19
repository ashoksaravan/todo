package com.todo.controller;

/**
 * @author vinodkumara
 *
 */
public class GenerateRandomPassword {
	
	/**
	 * ALPHA_NUM.
	 */
	private static final String ALPHA_NUM = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ$%@&!*?";

	/**
	 * @param len
	 * @return String
	 */
	public String getAlphaNumeric(int len) {
		StringBuffer sb = new StringBuffer(len);
		for (int i = 0; i < len; i++) {
			int ndx = (int) (Math.random() * ALPHA_NUM.length());
			sb.append(ALPHA_NUM.charAt(ndx));
		}
		return sb.toString();
	}
}
