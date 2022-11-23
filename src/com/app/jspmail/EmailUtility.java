package com.app.jspmail;


import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


/**
 * A utility class for sending e-mail messages
 * @author www.codejava.net
 *
 */
public class EmailUtility {
	public static void sendEmail(String host, String port,
			final String userName, final String password, String toAddress,
			String cc,String bcc,
			String subject, String message) throws AddressException,
			MessagingException {

		// sets SMTP server properties
		Properties properties = new Properties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", port);
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");

		// creates a new session with an authenticator
		Authenticator auth = new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(userName, password);
			}
		};

		Session session = Session.getInstance(properties, auth);

		// creates a new e-mail message
		Message msg = new MimeMessage(session);

		msg.setFrom(new InternetAddress(userName));
		InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
		InternetAddress[] ccAddresses = { new InternetAddress(cc) };
		InternetAddress[] bccAddresses= { new InternetAddress(bcc) };
		
	    System.out.println("toAddress " +  toAddresses +"---" + toAddress);
	    System.out.println("ccAddresses " +  ccAddresses +" ---"+  cc);
	    System.out.println("bccAddresses " +  bccAddresses +" --"+ bcc);
		
		msg.setRecipients(Message.RecipientType.TO, toAddresses);
		msg.addRecipients(Message.RecipientType.CC, ccAddresses);
		msg.addRecipients(Message.RecipientType.BCC, bccAddresses);
		
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);

		// sends the e-mail
		Transport.send(msg);

	}
}
