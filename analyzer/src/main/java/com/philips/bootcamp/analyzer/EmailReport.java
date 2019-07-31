package com.philips.bootcamp.analyzer;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.philips.bootcamp.utils.Values;
 
public class EmailReport {
 
	static Properties mailServerProperties;
	static Session getMailSession;
	static MimeMessage generateMailMessage;
	
	final static String USER_EMAIL = "@gmail.com";
	final static String USER_PASSWORD = "";	
	final static String RECIPIENT_EMAIL = "jeev.chiran@gmail.com";
 
	public static void executeSendEmail() throws AddressException, MessagingException {
		generateAndSendEmail();
		System.out.println("Report successfully sent to your mail");
	}
 
	public static void generateAndSendEmail() throws AddressException, MessagingException {
		
		try {
			mailServerProperties = System.getProperties();
			mailServerProperties.put("mail.smtp.port", "587");
			mailServerProperties.put("mail.smtp.auth", "true");
			mailServerProperties.put("mail.smtp.starttls.enable", "true");
 
			getMailSession = Session.getDefaultInstance(mailServerProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			
			// Add recipients for mail
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(RECIPIENT_EMAIL));			
			
			generateMailMessage.setSubject("Code report");
			
			MimeBodyPart messageBodyPart = new MimeBodyPart();  
			
			String filename = Values.FINAL_OUTPUT_FILE;
			DataSource source = new FileDataSource(filename);  
			messageBodyPart.setDataHandler(new DataHandler(source));  
			messageBodyPart.setFileName(filename); 
			
			MimeBodyPart messageBodyPart1 = new MimeBodyPart();  
			messageBodyPart1.setText("Your static code analyzer report is attached below." + "\n\n Regards, \nJS B2 G11");  
			        
			//create multipart object and add MimeBodyPart objects to this object      
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart1);
			multipart.addBodyPart(messageBodyPart);  
			
			generateMailMessage.setContent(multipart);                           
						
			Transport transport = getMailSession.getTransport("smtp");
 
			transport.connect("smtp.gmail.com", USER_EMAIL, USER_PASSWORD);
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
			
		} catch (Exception e) {
			System.out.println("error occured");
			e.printStackTrace();
		}		
		
	}
}