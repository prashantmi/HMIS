package hisglobal.utility.EmailSender;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.io.*;

public class NICEmailClient {
    public static String d_email,d_password,d_host,d_port,m_to;
    public static boolean verbose =false ;
    public NICEmailClient() {}
    public boolean sendEmail(String m_to,String m_subject,String m_text) {
        Properties props = new Properties();
        //props.put("mail.smtp.host", d_host);
        //props.put("mail.smtp.port", d_port);
        props.put("mail.smtp.host", "mail.nic.in");
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
        try {
            Session session = Session.getInstance(props,
			       new javax.mail.Authenticator() {
                                 protected PasswordAuthentication
		                   getPasswordAuthentication() {
//                                      return new PasswordAuthentication(d_email
//					         ,d_password);
                                        return new PasswordAuthentication("dispatch.hyd@nims.edu.in"//"itcell.nims"
                                                 ,"CD@nims123");
                                 }
                               });
            //if (verbose)
                session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
	    // -- Set the BODY,SBJECT,FROM and TO fields --
            msg.setText(m_text);
            msg.setSubject(m_subject);
            //By Mukund on 16.08.17
            //msg.setFrom(new InternetAddress("itcell@nims.edu.in"));
            msg.setFrom(new InternetAddress("dispatch.hyd@nims.edu.in"));
            msg.addRecipient(Message.RecipientType.TO,
			     new InternetAddress(m_to));
	    // -- Send the mail --
            Transport.send(msg);
        } catch (Exception mex) {
            if (verbose)
                mex.printStackTrace();
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        NICEmailClient e = new NICEmailClient();
        System.out.println(System.getProperty("user.dir"));
        //e.sendEmail("test22@nic.in","from","i am sending u this mail from java application. this is for test, pls ignore.");
        e.sendEmail("garimagotra@cdac.in","from","i am sending u this mail from java application. this is for test, pls ignore.");

    }
}

