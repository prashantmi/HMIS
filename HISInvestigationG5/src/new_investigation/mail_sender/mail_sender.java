package new_investigation.mail_sender;

import hisglobal.utility.EmailSender.config.EmailConfig;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.sql.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.*;
import javax.servlet.http.*;

import javax.mail.BodyPart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;

/**
 * @author sanjeevsingh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class mail_sender 
{ 
	
	String SMTP_HOST_NAME = "";
//	String SMTP_AUTH_USER = "";
	String SMTP_AUTH_PWD  = "";
	String DefaultMail = "";
	String emailMsgTxt      = "";
	String emailSubjectTxt  = "";
	String SMTP_PORT_NAME = "";
	//String emailFromAddress = "annusaroha@cdacnoida.in";
	String emailFromAddress = "";
	String[] mailCC= new String[1];
	//String user_name="" ;
	
	//HttpSession session;
	//= request.getSession(true);
	
	 
	 
	public void TransferToMailServer(String mail[],String subj,String emailMsgTxt,HttpServletRequest request)throws MessagingException
	{	    System.out.println("------------------------------Inside TransferToMailServer function.");
				
	/*code added to get mail variables from web manager table*/

	try{
	
			//SMTP_HOST_NAME = "10.0.0.99";
			//SMTP_AUTH_USER = "annusaroha";
			//SMTP_AUTH_PWD  = "test";		
			//DefaultMail = "sanjeevsingh@cdacnoida.in";
	
			SMTP_HOST_NAME = EmailConfig.email_host;
			//SMTP_AUTH_USER = EmailConfig.email_username;
			SMTP_AUTH_PWD  = EmailConfig.email_password;	
			//DefaultMail = EmailConfig.email_defaultId;
			emailFromAddress=EmailConfig.email_fromAddress;

			SMTP_PORT_NAME = EmailConfig.email_port;
	
		  //code to send cc to the employee editing the code
			//
		    System.out.println("SMTP_HOST_NAME    "+SMTP_HOST_NAME);
		    
						HttpSession session=request.getSession(true);
						   System.out.println("------------------------------In try"+session.getAttribute("name"));
						  // if(session.getAttribute("name")!=null)
							//   System.out.println("session is"+session.getAttribute("name"));
						 String user_name=(String)session.getAttribute("name")==null?"Guest":session.getAttribute("name").toString();	 
						 System.out.println("------------------------------username is:"+user_name);
				
		  	 mailCC[0]=mail[0];
		  	System.out.println("cc to"+mailCC[0]);
	
		   
			postMail( mail, subj, emailMsgTxt, emailFromAddress,mailCC);	
	}
	catch(Exception e)
	{
		System.out.println("exception"+e);
	}
		    
		    
	
		
	}

  public void postMail( String recipients[ ], String subject,
							String message , String from, String[] mailCC) throws MessagingException
  {

	try
	{
  	
	boolean debug = true;

	 //Set the host smtp address
	 Properties props = new Properties();
	 props.put("mail.smtp.host", SMTP_HOST_NAME);
	 props.put("mail.smtp.auth", "true");
	 props.put("mail.smtp.port", SMTP_PORT_NAME); 
	Authenticator auth = new SMTPAuthenticator();
	Session session = Session.getDefaultInstance(props, auth);
	 
	session.setDebug(debug);	// create a message
	Message msg = new MimeMessage(session);

	// set the from and to address
	InternetAddress addressFrom = new InternetAddress(from);
	msg.setFrom(addressFrom);
	
	InternetAddress[] addressTo = new InternetAddress[recipients.length];
    InternetAddress[] addressCC = new InternetAddress[mailCC.length];
	//System.out.println("mailccdata:" + mailCC[0]);
	for (int i = 0; i < recipients.length; i++)
	{
		addressTo[i] = new InternetAddress(recipients[i]);
	}

	if(!(mailCC[0].length()==0)){
		
		for (int i = 0; i < mailCC.length; i++)
			{
				addressCC[i] = new InternetAddress(mailCC[i]);
				System.out.println("address"+ mailCC[i]);
			}
	}
	
	msg.addRecipients(Message.RecipientType.TO, addressTo);
	
	if(!(mailCC[0].length()==0)){
		
		msg.setRecipients(Message.RecipientType.CC, addressCC);
	}
	
	// Setting the Subject and Content Type
	msg.setSubject(subject);
	msg.setContent(message, "text/plain");
	Transport.send(msg);
 
  }
  catch(Exception e)
  {
	System.out.println(e);
  }
  }
  
  public void TransferToMailServerWithAttachment(String mail[],String subj,String emailMsgTxt,HttpServletRequest request)throws MessagingException
	{	    System.out.println("------------------------------Inside TransferToMailServerWithAttachment function.");
				
	/*code added to get mail variables from web manager table*/

	try{
	
			//SMTP_HOST_NAME = "10.0.0.99";
			//SMTP_AUTH_USER = "annusaroha";
			//SMTP_AUTH_PWD  = "test";		
			//DefaultMail = "sanjeevsingh@cdacnoida.in";
	
			SMTP_HOST_NAME = EmailConfig.email_host;
			//SMTP_AUTH_USER = EmailConfig.email_username;
			SMTP_AUTH_PWD  = EmailConfig.email_password;	
			//DefaultMail = EmailConfig.email_defaultId;
			emailFromAddress=EmailConfig.email_fromAddress;

	
	
		  //code to send cc to the employee editing the code
			//
		    System.out.println("SMTP_HOST_NAME    "+SMTP_HOST_NAME);
		    
						HttpSession session=request.getSession(true);
						   System.out.println("------------------------------In try"+session.getAttribute("name"));
						  // if(session.getAttribute("name")!=null)
							//   System.out.println("session is"+session.getAttribute("name"));
						 String user_name=(String)session.getAttribute("name")==null?"Guest":session.getAttribute("name").toString();	 
						 System.out.println("------------------------------username is:"+user_name);
				
		  	 mailCC[0]=mail[0];
		  	System.out.println("cc to"+mailCC[0]);
	
		   
		  	postMailWithAttachment( mail, subj, emailMsgTxt, emailFromAddress,mailCC);	
	}
	catch(Exception e)
	{
		System.out.println("exception"+e);
	}
		    
		    
	
		
	}

public void postMailWithAttachment( String recipients[ ], String subject,
							String message , String from, String[] mailCC) throws MessagingException
{

	try
	{
	
	boolean debug = true;

	 //Set the host smtp address
	 Properties props = new Properties();
	 props.put("mail.smtp.host", SMTP_HOST_NAME);
	 props.put("mail.smtp.auth", "true");

	Authenticator auth = new SMTPAuthenticator();
	Session session = Session.getDefaultInstance(props, auth);
	 
	session.setDebug(debug);	// create a message
	Message msg = new MimeMessage(session);

	// set the from and to address
	InternetAddress addressFrom = new InternetAddress(from);
	msg.setFrom(addressFrom);
	
	InternetAddress[] addressTo = new InternetAddress[recipients.length];
  InternetAddress[] addressCC = new InternetAddress[mailCC.length];
	//System.out.println("mailccdata:" + mailCC[0]);
	for (int i = 0; i < recipients.length; i++)
	{
		addressTo[i] = new InternetAddress(recipients[i]);
	}

	if(!(mailCC[0].length()==0)){
		
		for (int i = 0; i < mailCC.length; i++)
			{
				addressCC[i] = new InternetAddress(mailCC[i]);
				System.out.println("address"+ mailCC[i]);
			}
	}
	
	msg.addRecipients(Message.RecipientType.TO, addressTo);
	
	if(!(mailCC[0].length()==0)){
		
		msg.setRecipients(Message.RecipientType.CC, addressCC);
	}
	
	  // Create the message part
    BodyPart messageBodyPart = new MimeBodyPart();
    
 // Create a multipart message
    Multipart multipart = new MimeMultipart();
	
	 // Part two is attachment
    messageBodyPart = new MimeBodyPart();
    String filename = "C:/Users/xyz/Desktop/dcds.oxps";
    DataSource source = new FileDataSource(filename);
    messageBodyPart.setDataHandler(new DataHandler(source));
    messageBodyPart.setFileName(filename);
    multipart.addBodyPart(messageBodyPart);

   /* // Send the complete message parts
    message.setContent(multipart);

    // Send message
    Transport.send(message);*/

	// Setting the Subject and Content Type
	msg.setSubject(subject);
	msg.setContent(message, "text/plain");
	msg.setContent(multipart);
	Transport.send(msg);
	
	System.out.println("Sent message successfully....");


}
catch(Exception e)
{
	System.out.println(e);
}
}
/**
* SimpleAuthenticator is used to do simple authentication
* when the SMTP server requires it.
*/
private class SMTPAuthenticator extends javax.mail.Authenticator
{

	public PasswordAuthentication getPasswordAuthentication()
	{
		String username = emailFromAddress;
		String password = SMTP_AUTH_PWD;
		return new PasswordAuthentication(username, password);
	}
}



}
