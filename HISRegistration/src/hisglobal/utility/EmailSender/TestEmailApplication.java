package hisglobal.utility.EmailSender;


import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;

class SMTPAuthenticator extends javax.mail.Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {
        String username = "s.singaravelan@cdac.in";
        String password = "SingCdac@39#";
        return new PasswordAuthentication(username, password);
    }
}	


public class TestEmailApplication {
	
	

	public static void main(String[] args) {
		
		 
	      // Recipient's email ID needs to be mentioned.
	      String to = "s.singaravelan@cdac.in";

	      // Sender's email ID needs to be mentioned
	      String from = "from-cdacnoida@cdac.in";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.put("mail.smtp.auth", "true");
	      properties.put("mail.smtp.host", "smtp.cdac.in");
	      properties.put("mail.smtp.port", "587");
	      properties.put("mail.transport.protocol","smtp");

	      // Get the default Session object.
	      Authenticator auth = new SMTPAuthenticator();
	      Session session = Session.getInstance(properties,auth);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("Attachment Mail Test!!!");

	         // Create the message part 
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Fill the message
	         messageBodyPart.setText("Dear Receiver,"+
	        		 				"\n\n This is to Inform you that the Test is Sucessfully Done!!"+
	        		 				"\n\n\nRegards.,"+
	        		 				"\nSingaravelan "+
	        		 				"\nPE-IT CDAC NOIDA");
	         
	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	         //String fullfilePath = "ftp://velan:cdac123@10.226.29.5/ftpserver/101/10115000/1011500004431/restws.pdf";
	         String fullfilePath1 = "ftp://velan:cdac123@10.226.29.5/ftpserver/101/10115000/1011500008437/11_Image_01";
	         String fullfilePath2 = "ftp://velan:cdac123@10.226.29.5/ftpserver/101/10115000/1011500009026/11_Image_01";
	         String fullfilePath3 = "ftp://velan:cdac123@10.226.29.5/ftpserver/101/10115000/1011500009042/11_Image_01";
	         String fullfilePath4 = "ftp://velan:cdac123@10.226.29.5/ftpserver/101/10115000/1011500009077/11_Image_01";


	         URL urlftp1 = new URL(fullfilePath1);
	         URL urlftp2 = new URL(fullfilePath2);
	         URL urlftp3 = new URL(fullfilePath3);
	         URL urlftp4 = new URL(fullfilePath4);

			 //URLConnection urlc = urlftp.openConnection();
			 
			 //InputStream inputStream = null;

			 //inputStream = urlc.getInputStream();
			 
			 //inputStream.
	         BodyPart messageBodyPart1 = new MimeBodyPart();
	         DataSource source1 = new URLDataSource(urlftp1);
	         messageBodyPart1.setDataHandler(new DataHandler(source1));
	         messageBodyPart1.setFileName("emailFile1");
	         
	         System.out.println("Souce 1 ContentType--" +source1.getContentType());
	         multipart.addBodyPart(messageBodyPart1);
	         
	         BodyPart messageBodyPart2 = new MimeBodyPart();
	         DataSource source2 = new URLDataSource(urlftp2);
	         messageBodyPart2.setDataHandler(new DataHandler(source2));
	         messageBodyPart2.setHeader("Content-Type", "application/pdf");
	         messageBodyPart2.setFileName("emailFile2");
	         
	         System.out.println("Souce 2 ContentType--" +source2.getContentType());
	         multipart.addBodyPart(messageBodyPart2);
	         
	         BodyPart messageBodyPart3 = new MimeBodyPart();
	         DataSource source3 = new URLDataSource(urlftp3);
	         messageBodyPart3.setDataHandler(new DataHandler(source3));
	         messageBodyPart3.setHeader("Content-Type", "application/msword");
	         messageBodyPart3.setFileName("emailFile3");
	         
	         System.out.println("Souce 3 ContentType--" +source3.getContentType());
	         multipart.addBodyPart(messageBodyPart3);
	         
	         BodyPart messageBodyPart4 = new MimeBodyPart();
	         DataSource source4 = new URLDataSource(urlftp4);
	         messageBodyPart4.setDataHandler(new DataHandler(source4));
	         messageBodyPart4.setFileName("emailFile3");
	         
	         System.out.println("Souce 4 ContentType--" +source4.getContentType());
	         multipart.addBodyPart(messageBodyPart3);

	         // Send the complete message parts
	         message.setContent(multipart );

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (Exception mex) {
	         mex.printStackTrace();
	      }

	}
	
	
}
