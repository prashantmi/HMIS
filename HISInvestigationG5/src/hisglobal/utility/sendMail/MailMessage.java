package hisglobal.utility.sendMail;

import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
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

public class MailMessage{
	
	private String smtpHost;
	private String smtpPort;
	private boolean authorization;
	private String fromEmailAddress;
	private String [] toEmailAddress;
	private String [] attachments;
	private String userName;
	private String password;
	private String subject;
	private String body;
	
	
	public MailMessage(String smtpHost,String smtpPort,
			String username,String password,
			String fromEmailAddress, String [] toEmailAddress){
		
		this.smtpHost=smtpHost;
		this.smtpPort=smtpPort;
		this.userName=username;
		this.password=password;
		this.fromEmailAddress=fromEmailAddress;
		this.toEmailAddress=toEmailAddress;
		this.authorization=true;
	}
	
	public MailMessage(String smtpHost,String smtpPort,
			String username,String password,
			String fromEmailAddress, String toEmailAddress){
		this(smtpHost,smtpPort,username,password,fromEmailAddress,new String[]{toEmailAddress});
	}
	public MailMessage(String smtpHost,String smtpPort,
			String fromEmailAddress, String toEmailAddress){
		this(smtpHost,smtpPort,"","",fromEmailAddress,new String[]{toEmailAddress});
	}
	
	public void setRecipients(String [] recipients){
		this.toEmailAddress=recipients;
	}
	
	public void attachFiles(String [] attachments){
		this.attachments=attachments;
	}
	
	public void attachFiles(String attachments){
		String files[]=new String[1];
		files[0]=attachments;
		attachFiles(files);
		
	}
	
	public void setSubject(String subject){
		this.subject=subject;
	}
	
	public void setBody(String body){
		this.body=body;
	}
	
	public void setUserName(String userName){
		this.userName=userName;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	
	public void sendMail(){
		
		//String SSL_FACTORY ="javax.net.ssl.SSLSocketFactory"; 
		StringBuilder errorMessage=new StringBuilder();
		try{
			Properties props = new Properties(); 
			System.out.println("Mailing Server :: " +this.smtpHost);
            props.put("mail.smtp.host", this.smtpHost); //set the smtp mail server
            if(this.smtpPort==null)
            	smtpPort="25";
            props.put("mail.smtp.auth",authorization); 
            //debug:true will display the progress message on console
            props.put("mail.debug", "true"); 
            System.out.println("Mailing Server :: " +this.smtpPort);
            props.put("mail.smtp.port", smtpPort); 
            
            //props.put("mail.smtp.starttls.enable","true");
            //props.put("mail.smtp.socketFactory.port", SMTP_PORT); 
            //props.put("mail.smtp.socketFactory.class", SSL_FACTORY); 
            //props.put("mail.smtp.socketFactory.fallback", "false"); 
                        
            //****create the session
            if(userName.equals("")|| password.equals("")){
            	System.out.println("User Name or Password is empty");
            	errorMessage.append("User Name or Password is empty");
            }
            Session session = Session.getDefaultInstance(props,new SMTPAuthenticator()); 
            
            //***create the simple Message
            MimeMessage msg =new MimeMessage(session); 
            System.out.println("Send From email::: "+this.fromEmailAddress);
	        msg.setFrom(new InternetAddress(this.fromEmailAddress));
	        InternetAddress[] address=null;
	        
	        //creating the list of the recipient and adding it to the InternetAddress
	        if(toEmailAddress.length>0){
	        	address = new InternetAddress[toEmailAddress.length];
	        	for(int i=0;i<toEmailAddress.length;i++){
	        		address[i]=new InternetAddress(toEmailAddress[i]);
	        	}
	        }
	        else{
	        	//Send to address is not correct
	        	errorMessage.append("To Email Address is not valid");
	        	System.out.println("To Email Address is not valid");
	        }
	        
	        msg.addRecipients(Message.RecipientType.TO, address);
	 
	        msg.setSubject(subject);
	        msg.setSentDate(new Date());
	
	        //create a new BodyPart
	        MimeBodyPart bodyPart=new MimeBodyPart();
	        bodyPart.setText(this.body);
	        
	        //***Set message content
	        //msg.setText(this.body);
	        
	        //Transport transport=session.getTransport("smtp");
	        //transport.connect("smtp.gmail.com", "riaz1.ansari", "weee");
	        //transport.sendMessage(msg, msg.getAllRecipients());
	        
	        Multipart mulipart=null;
	        //***Adding the Attachment
	        //String [] files=new String []{"c:/abc.txt"};
	        if(attachments!=null && attachments.length>0){
	        	mulipart=new MimeMultipart();
	        	mulipart=addAtachments(this.attachments);
	        }	
	        
	        if(mulipart==null) mulipart=new MimeMultipart();
	        mulipart.addBodyPart(bodyPart);
	        msg.setContent(mulipart);
	        Transport.send( msg ); 
	        
	        System.out.println("Sucessfully Sent mail to User(s)");
		}
		catch (MessagingException e) {
			System.out.println(errorMessage);
			e.printStackTrace();
		}
		catch (Exception e) {
			System.out.println(errorMessage);
			e.printStackTrace();
		}
		
	}
	

	//add the attachment to the multipart 
	 protected Multipart addAtachments(String[] attachments) 
    throws MessagingException, AddressException 
	{ 
		Multipart multipart=new MimeMultipart(); 

		for(int i = 0; i<= attachments.length -1; i++) 
		{ 
		String filename = attachments[i]; 
		MimeBodyPart attachmentBodyPart = new MimeBodyPart(); 
		//use a JAF FileDataSource as it does MIME type detection 
		DataSource source = new FileDataSource(filename); 
		attachmentBodyPart.setDataHandler(new DataHandler(source)); 
		
		//assume that the filename you want to send is the same as the 
		//actual file name - could alter this to remove the file path 
		attachmentBodyPart.setFileName(filename); 
		//add the attachment 
		multipart.addBodyPart(attachmentBodyPart); 
		} 
		return multipart;
	}	
	 
	 
	 //creating the authenticator 
	 private class SMTPAuthenticator extends Authenticator {
		 public PasswordAuthentication getPasswordAuthentication() {
			 return new PasswordAuthentication(userName, password);
		 }
	 }
		
}

