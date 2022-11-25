package com.app.jspmail;


import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


/**
 * A utility class for sending e-mail messages
 *
 */
public class EmailUtility {
	public static void sendEmail(String host, String port,
			final String userName, final String password, String toAddress,
			String cc,String bcc,
			String subject, String message,  List<File> attachedFiles) throws AddressException,
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
		
		//we have to give to,cc,bcc otherwise we get AddressException
		 msg.setRecipients(Message.RecipientType.TO, toAddresses);
		 msg.addRecipients(Message.RecipientType.CC, ccAddresses);
		 msg.addRecipients(Message.RecipientType.BCC, bccAddresses);
		 
		 
		/*
		 * for(int i=0;i< toAddresses.length;i++) {
		 * 
		 * if(toAddresses[i].equals("") || toAddresses[i]==null ) {
		 * System.out.println("  toAddresses  :  " + toAddresses[i]); continue; }else {
		 * System.out.println("  else block of to: ");
		 * msg.addRecipient(Message.RecipientType.TO, toAddresses[i]); }
		 * 
		 * }
		 * 
		 * for(int i=0;i< ccAddresses.length;i++) {
		 * 
		 * if(ccAddresses[i].equals("" ) || ccAddresses[i]==null) {
		 * System.out.println("  ccAddresses  :  " + ccAddresses[i]); continue; }else {
		 * System.out.println("  else block of cc: ");
		 * msg.addRecipient(Message.RecipientType.CC, ccAddresses[i]); }
		 * 
		 * } for(int i=0;i< bccAddresses.length;i++) {
		 * 
		 * if(bccAddresses[i].equals("" ) || bccAddresses[i]==null) {
		 * System.out.println("  bccAddresses  :  " + bccAddresses[i]); continue; }else
		 * { System.out.println("  else block of bcc ");
		 * msg.addRecipient(Message.RecipientType.BCC, bccAddresses[i]); }
		 * 
		 * }
		 */
		
		msg.setSubject(subject);
		msg.setSentDate(new Date());
		msg.setText(message);
		
		 // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        
       
        // adds attachments
        if (attachedFiles != null && attachedFiles.size() > 0) {
            for (File aFile : attachedFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try {
                	System.out.println("In attache file ..... ");
                    attachPart.attachFile(aFile);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);  
               
		// sends the e-mail
		Transport.send(msg);

	}
}
