package MailSender;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/*import new_investigation.SMS.SMSConfig;
import new_investigation.SMS.SMSHttpPostClient;*/

import java.util.Properties;
import javax.servlet.http.HttpSession;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMessageToUser {

	
	/*public static void SendSMS(String message, String contactNumber)
	{
		
		  SMSConfig objSMSConfig=new SMSConfig(); 
		  SMSHttpPostClient.sendTextSMSThroughNICSMSGateway
		  (objSMSConfig.sms_username,
		  objSMSConfig.sms_password,objSMSConfig
		  .sms_senderId,objSMSConfig.sms_url,
		  contactNumber,message);
		  
		  SMSGateway.sendSingleSMS(objSMSConfig.sms_username,objSMSConfig.sms_password,objSMSConfig.sms_senderId,contactNumber,message);
				  
		  	 
	}*/
	
	
	public static void SendEmail(String strSubject, String body, String email, String baste64bytearray,HttpServletRequest _request)
	{
		  final String[] mailtTo={email}; 
		   final String mStrSubj = strSubject;
			 final String mBody = body;
			 final String base64bytearrayy = baste64bytearray;
			 final HttpServletRequest mRequest = _request;
		  final mail_sender mail = new mail_sender(); 
		  
			  new Thread(new Runnable() {
				    public void run() {
				    	try {
							mail.TransferToMailServerWithAttachment(mailtTo,mStrSubj,mBody,base64bytearrayy,mRequest);
						} catch (MessagingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
				    }
				}).start();
			  		  
	}
	
	

	// StartTime Format: YYYYMMDD hh:mm:ss
	static String scheduledTime = "20120801 15:40:00";
	//static HttpURLConnection connection = null;
	static final  String nic_sms_url="https://smsgw.sms.gov.in/failsafe/HttpLink?";
	static final String mailServer="mail.gov.in";
	static final String SMTP_HOST_NAME   = "mail.gov.in";
	static final String SMTP_AUTH_USER   = "itcell.nims";
	static final String SMTP_AUTH_PWD  	 = "CD@nims123";
	static final String emailMsgTxt      = "This message is from HMIS";
	static final String emailSubjectTxt  = "test mail";
	static final String emailFromAddress = "itcell@nims.edu.in";
	 public static boolean verbose =false ;
	
	
	/*public static void main(String[] args) {
	       // NICEmailClient e = new NICEmailClient();
	        //System.out.println(System.getProperty("user.dir"));
	        //e.sendEmail("test22@nic.in","from","i am sending u this mail from java application. this is for test, pls ignore.");
	        sendEmail2("ashupandey9415@gmail.com","from","i am sending u this mail from java application. this is for test, pls ignore.");

	    }*/
	
	public static boolean sendEmail2(String m_to,String m_subject,String m_text) {
		
		
		
		
		Properties props = new Properties();
        //props.put("mail.smtp.host", d_host);
        //props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.host", "mail.gov.in");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable","true");
       // System.setProperty("javax.net.ssl.trustStore","hisglobal/utility/EmailSender/cacerts");
       // System.setProperty("javax.net.ssl.trustStore","src/emailproject/jssecacerts");
        //System.setProperty("javax.net.ssl.trustStore","chain.p7b");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
		       	"javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "true");
        SecurityManager security = System.getSecurityManager();
        try 
        {
            Session session = Session.getInstance(props,
			       new javax.mail.Authenticator() {
                                 protected PasswordAuthentication
		                   getPasswordAuthentication() {
//                                      return new PasswordAuthentication(d_email
//					         ,d_password);
                                        return new PasswordAuthentication(SMTP_AUTH_USER,SMTP_AUTH_PWD);
                                 }
                               });
            //if (verbose)
                session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
	    // -- Set the BODY,SBJECT,FROM and TO fields --
            msg.setText(m_text);
            msg.setSubject(m_subject);
            msg.setFrom(new InternetAddress(emailFromAddress));
            msg.addRecipient(Message.RecipientType.TO,
			     new InternetAddress(m_to));
	    // -- Send the mail --
            Transport.send(msg);
        } catch (Exception mex) 
        {
            if (verbose)
                mex.printStackTrace();
            return false;
        }
        return true;
    }
}
