package com.todo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	/**
	 * FROM.
	 */
	private final static String FROM = "todo-noreply@ebix.com";

	/**
	 * HOST.
	 */
	private final static String HOST = "192.168.32.9";
	

	public void Sendmail(String message, ArrayList<String> ar,
			String subject) {

		// Collect the necessary information to send a simple message
		// Make sure to replace the values for host, to, and from with
		// valid information.
		// host - must be a valid smtp server that you currently have
		// access to.
		// to - whoever is going to get your email
		// from - whoever you want to be. Just remember that many smtp
		// servers will validate the domain of the from address
		// before allowing the mail to be sent.

		

		String messageText = message;
		boolean sessionDebug = true;
		// Create some properties and get the default Session.
		Properties props = System.getProperties();
		props.put("mail.host", HOST);
		props.put("mail.transport.protocol", "smtp");
		Session session = Session.getDefaultInstance(props, null);
		// Set debug on the Session so we can see what is going on
		// Passing false will not echo debug info, and passing true
		// will.
		session.setDebug(sessionDebug);
		try {

			Message msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(FROM));

			InternetAddress[] addressTo = new InternetAddress[ar.size()];

			int i = 0;
			for (String email : ar) {
				addressTo[i++] = new InternetAddress(email);
			}

			msg.setRecipients(javax.mail.Message.RecipientType.TO, addressTo);

			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setText(messageText);

			Transport.send(msg);

		} catch (MessagingException mex) {
			mex.printStackTrace();

		}

	}

	public static void main(String args[]) {
		ArrayList<String> ar = new ArrayList<String>();
		ar.add("arjun.mani@ebix.com");

		SendMail sms = new SendMail();
		sms.Sendmail("Task ToDo", ar, "Task 'TODO'");
	}

}
