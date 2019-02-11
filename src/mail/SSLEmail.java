package mail;

/*
 * SSL send mail
 * 
 * version: February 11, 2019 01:41 PM
 * version: February 11, 2019 01:41 PM
 * 
 * Author : Chao-Hsuan Ke
 * Institute: Delta Research Center
 * Company : Delta Electronics Inc. (Taiwan)
 * 
 */

/*
 * Reference
 * https://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp
 * 
 */

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class SSLEmail {

	/**
	   Outgoing Mail (SMTP) Server
	   requires TLS or SSL: smtp.gmail.com (use authentication)
	   Use Authentication: Yes
	   Port for SSL: 465
	 */
	public static void main(String[] args) {
		final String fromEmail = "your@gmail.com"; 			//requires valid gmail id (your gmail address)
		final String password = ""; 						// correct password for gmail id (your gmail password)
		final String toEmail = "your@gmail.com"; 			// can be any email id 
		
		System.out.println("SSLEmail Start");
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com"); 		//SMTP Host
		props.put("mail.smtp.socketFactory.port", "465"); 	//SSL Port
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory"); 			//SSL Factory Class
		props.put("mail.smtp.auth", "true"); 				//Enabling SMTP Authentication
		props.put("mail.smtp.port", "465"); 				//SMTP Port
		
		Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		};
		
		Session session = Session.getDefaultInstance(props, auth);
		System.out.println("Send");
	        EmailUtil.sendEmail(session, toEmail,"SSLEmail Testing Subject", "SSLEmail Testing Body");
	        //EmailUtil.sendAttachmentEmail(session, toEmail,"SSLEmail Testing Subject with Attachment", "SSLEmail Testing Body with Attachment");
	        //EmailUtil.sendImageEmail(session, toEmail,"SSLEmail Testing Subject with Image", "SSLEmail Testing Body with Image");
	}

}
