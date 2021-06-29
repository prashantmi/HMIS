package bbpubliclogin;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import new_investigation.SMS.SMSConfig;
import new_investigation.SMS.SMSHttpPostClient;


public class SendMessageToUser {

	
	public static void SendSMS(String message, String contactNumber)
	{
		
		  SMSConfig objSMSConfig=new SMSConfig(); 
		  /*SMSHttpPostClient.sendTextSMSThroughNICSMSGateway
		  (objSMSConfig.sms_username,
		  objSMSConfig.sms_password,objSMSConfig
		  .sms_senderId,objSMSConfig.sms_url,
		  contactNumber,message);*/
		  
		  SMSGateway.sendSingleSMS(objSMSConfig.sms_username,objSMSConfig.sms_password,objSMSConfig.sms_senderId,contactNumber,message);
				  
		  	 
	}
	
	
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
}
