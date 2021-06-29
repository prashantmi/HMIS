package bbpubliclogin;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.activation.*;
 
 
public class Email {
    
 //   static final Logger logger = Logger.getLogger(SSLMail.class);
//    private static final String SMTP_HOST_NAME = "164.100.14.6";
    private static final String SMTP_HOST_NAME = "mail.nic.in";
    private static final int SMTP_HOST_PORT = 465;
    private static final String SMTP_AUTH_USER = "noreply";//mail id
    private static final String SMTP_AUTH_PWD = "urpwd";//password

    
    // the following method is work with production server
    public static void sendMail(String to_address, String subject, String content, String attach_file) throws Exception{

		String from_address = SMTP_AUTH_USER+"@nims.edu.in";
    try{

     	Properties props = new Properties();
        props.put("mail.transport.protocol", "smtps");    
        props.put("mail.smtps.host", SMTP_HOST_NAME);
        props.put("mail.smtps.auth", "true");
      //  CICommon ci = new CICommon();
        // to be replaced with 
//		      System.setProperty("javax.net.ssl.trustStore","jssecacerts path of ur app server ");

      System.setProperty("javax.net.ssl.trustStore","C:/Java/jdk1.7.0_40/jre/lib/security/jssecacert");
         

        Session mailSession = Session.getDefaultInstance(props);
        mailSession.setDebug(false); //need to be false
        Transport transport = mailSession.getTransport();
        MimeMessage message = new MimeMessage(mailSession);
  
        // set the from and to address

        message.setFrom(new InternetAddress(from_address));

        message.setSubject(subject);
        message.setContent(content, "text/plain");
        message.addRecipient(Message.RecipientType.TO,new InternetAddress(to_address));

		//File Attachment

                // create and fill the first message part
                MimeBodyPart mbp1 = new MimeBodyPart();
               // mbp1.setText("Kindly read the attached pdf document." );
                 mbp1.setText(content );

                // create the second message part
                MimeBodyPart mbp2 = new MimeBodyPart();

                // attach the file to the message
//                logger.error("attached file ----"+attach_file);
                FileDataSource fds = new FileDataSource(attach_file);//filename);
                mbp2.setDataHandler(new DataHandler(fds));

                mbp2.setFileName(fds.getName());

                // create the Multipart and add its parts to it
                Multipart mp = new MimeMultipart();
                  mp.addBodyPart(mbp1);
                   mp.addBodyPart(mbp2);

                // add the Multipart to the message
                message.setContent(mp);

        transport.connect(SMTP_HOST_NAME, SMTP_HOST_PORT, SMTP_AUTH_USER, SMTP_AUTH_PWD);

        transport.sendMessage(message,message.getRecipients(Message.RecipientType.TO));
        try {
            //    ci.Deletefile(attach_file);
            } catch (Exception e) {
                System.out.println(e.toString()+"Error in Email");
            }
        transport.close();
    }catch(Exception e){
        System.out.println("error in emailpris"+e.toString());
//        logger.error("While sending mail--- ",e);
        throw e;
    }
    }
 

     

}
