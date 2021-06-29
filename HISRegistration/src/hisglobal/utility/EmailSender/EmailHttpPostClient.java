package hisglobal.utility.EmailSender;

import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.URLDataSource;
import javax.mail.AuthenticationFailedException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
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

import org.apache.log4j.Logger;



/**
 * @author s.singaravelan
 *
 */
public class EmailHttpPostClient {
	
	String SMTP_AUTH_USER = "";
	String SMTP_AUTH_PWD  = "";
	private Logger logger = Logger.getLogger(this.getClass());

	/**
	 * Method to Set the Value to Class variables
	 * @param userName
	 * @param password
	 */
	public void setauthDetails(String userName,String password){
		this.SMTP_AUTH_USER=userName;
		this.SMTP_AUTH_PWD=password;
	}	
	
	/**
	 * Method to create a Mail session after Authenticating the Username and Password
	 * @param userName
	 * @param password
	 * @return
	 * @throws AuthenticationFailedException
	 */
	public Session createMailSession(String userName,String password) throws AuthenticationFailedException{ 
		
		Properties props = setMailProperties();
		setauthDetails(userName, password);

		Authenticator auth = new SMTPAuthenticator();
		Session session = Session.getInstance(props,auth);
		return session;
	}
	
	/**
	 * Method to Set the Details to create a complete message
	 * @param mailSession
	 * @param fromMailId
	 * @param toMailId
	 * @param msgSubject
	 * @param msgBody
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 */
	public Message createMessage(Session mailSession,String fromMailId,String toMailId,String msgSubject,String msgBody) throws AddressException, MessagingException{
		
		Message message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(fromMailId));
		message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toMailId));
		message.setSubject(msgSubject);
		message.setText("Dear "+toMailId.substring(0,toMailId.indexOf("@"))+","
				+ "\n\n "+msgBody
				+ "\n\nRegards,"
				+ "\nNIMS HMIS Team");
		
		return message;
		
	}
	
	
	/**
	 * Method to Set the Details to create a complete message with attachment
	 * @param mailSession
	 * @param fromMailId
	 * @param toMailId
	 * @param msgSubject
	 * @param msgBody
	 * @param fileDetails (Key-filePath , Value-fileName)
	 * @return
	 * @throws AddressException
	 * @throws MessagingException
	 * @throws MalformedURLException
	 */
	public Message createMessageWithAttachement(Session mailSession,String fromMailId,String toMailId,String msgSubject,String msgBody,Map fileDetails) throws AddressException, MessagingException, MalformedURLException{
		
		Message message = new MimeMessage(mailSession);
		message.setFrom(new InternetAddress(fromMailId));
		message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toMailId));
		message.setSubject(msgSubject);
		
		
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setText("Dear "+toMailId.substring(0,toMailId.indexOf("@"))+","
				+ "\n\n "+msgBody
				+ "\n\nRegards.,"
				+ "\n"+fromMailId.substring(0,fromMailId.indexOf("@")));
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(messageBodyPart);
		Iterator fileIterator=fileDetails.entrySet().iterator();
		
		while(fileIterator.hasNext()){
			Entry thisEntry = (Entry) fileIterator.next();
			addAttachment(multipart, (String)thisEntry.getKey(), (String)thisEntry.getValue());
			
		}

        message.setContent(multipart);
			
		
		return message;
		
	}
	
	/**
	 * Method to Add the Attachments to the Mail
	 * @param multipart
	 * @param filPath
	 * @param filename 
	 * @variable  ftpServerURL<ftpServerUserName>:<ftpServerPassword>@<ftpServerURL>
	 */
	private void addAttachment(Multipart multipart,String filPath, String filename) throws MessagingException, MalformedURLException
	{
		String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE;
		filPath = "ftp://" + ftpServerURL+"/"+filPath+"/"+filename.substring(0,filename.indexOf(".")>0?filename.indexOf("."):filename.length());
		String ext=filename.indexOf(".")>0?filename.substring(filename.indexOf(".")+1, filename.length()):"";
		
		logger.info("Mail Atttachment URL :: "+filPath+" Attachment Extension :: " + ext);
		
		URL _fileURL = new URL(filPath);
	    DataSource source = new URLDataSource(_fileURL);
	    BodyPart messageBodyPart = new MimeBodyPart();        
	    messageBodyPart.setDataHandler(new DataHandler(source));
	    if(ext!=null&&ext!="")
	    	messageBodyPart.setHeader("Content-Type", getContentType(ext));
	    messageBodyPart.setFileName(filename);
	    multipart.addBodyPart(messageBodyPart);
	}
	
	/**
	 * Method to return the ContentType on the basis of the fileExtension
	 * @param fileExtension
	 * @return
	 */
	public String getContentType(String fileExtension)
	{
		String contentTYpe="";
		
		if(fileExtension.equalsIgnoreCase("pdf"))
			contentTYpe="application/pdf";
		else if(fileExtension.equalsIgnoreCase("doc")||fileExtension.equalsIgnoreCase("docx"))
			contentTYpe="application/msword";
		else if(fileExtension.equalsIgnoreCase("jpeg")||fileExtension.equalsIgnoreCase("jpg")||fileExtension.equalsIgnoreCase("png")||fileExtension.equalsIgnoreCase("gif"))
			contentTYpe="image/"+fileExtension.toLowerCase();
			
		return contentTYpe;
	}
	
	/**
	 * Method to Send Bulk Emails through the CDAC gateway	
	 * @param mailSession
	 * @param messages
	 * @throws MessagingException
	 */
	public void sendBulkEmails(Session mailSession,List<Message> messages) throws MessagingException{
		Transport t = mailSession.getTransport();
		t.connect();
		try {
		  for(Message m : messages) {
		    m.saveChanges();
		    t.sendMessage(m, m.getAllRecipients());
		  }
		} finally {
		  t.close();
		}
	}
	
	/**
	 * Method to Send the Single Mail through the CDAC gateway	
	 * @param mailSession
	 * @param fromMailId
	 * @param toMailId
	 * @param msgSubject
	 * @param msgBody
	 * @throws AuthenticationFailedException
	 */
	public void sendSingleMailthroughGateway(Session mailSession,String fromMailId,String toMailId,String msgSubject,String msgBody) throws AuthenticationFailedException{
			
		try {
 
			Message message = new MimeMessage(mailSession);
			message.setFrom(new InternetAddress(fromMailId));
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toMailId));
			message.setSubject(msgSubject);
			//message.setText("Dear Mail Receiver,"
			//	+ "\n\n "+msgBody);
			
			message.setText("Dear "+toMailId.substring(0,toMailId.indexOf("@"))+","
					+ "\n\n "+msgBody
					+ "\n\nRegards,"
					+ "\nNIMS HMIS Team");
 
			Transport.send(message); 
 
		}
		catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/**
	 * Method to Set the Default SMTP Properties
	 * @return Properties
	 */
	public static Properties setMailProperties()
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.host", "smtp.cdac.in");
		props.put("mail.smtp.port", "587");
		props.put("mail.transport.protocol","smtp");
		
		return props;
	}
	
	/**
	* SimpleAuthenticator is used to do simple authentication
	* when the SMTP server requires it.
	*/
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

	    public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}	
	

}
