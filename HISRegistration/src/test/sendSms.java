package test;

//import handler.RequestHandler;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.text.*;
import java.io.*;
import java.util.*;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.io.*;
import java.net.URL;


public class sendSms {
	static final long serialVersionUID = 0;
    
	public static void main(String args[]){
		
		String urlString = null;
		String mobNo="919873919322";
		String sms="Hi ! Welcome to CDAC !!";
		String pwd="K!cEydi%2h";
		//$ - %24
		//# - %23
		//& - %26

		int size=140;
        if(size<160){
      //  System.out.println("abc-------1"+mobNo);
        	//urlString = "http://125.19.68.27:8084/messaging/bulksender.push?msisdn="+mob+"&username="+usr+"&password="+pswd+"&apicode="+api+"&cc="+cc+"&sendercli="+cli+"&message="+msg1;
       // urlString = "http://164.100.14.9/sendsms_nic/sendmsg.php?uname=satcom.auth&pass=Auth.satcom@123&send=SATCOM&dest="+mobNo+"&msg="+sms;
        urlString = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=nims.sms&pin="+pwd+"&message="+sms+"&mnumber="+mobNo+"&signature=NICSMS";
       // System.out.println("abc-------2");
        }else{
        //	System.out.println("abc-------3");
        	urlString = "http://164.100.14.9/sendsms_nic/sendmsg.php?uname=satcom.auth&pass=Auth.satcom@123&send=SATCOM&dest="+mobNo+"&msg="+sms+"&concat=1";
        }
        makeHTTPConnection(urlString,60000);
	}	

    
    /* This is called only one last time before the servlet is eligible for garbage collection. */
    //protected void destroy(){}
    
    private static String makeHTTPConnection(String spec1, int connectTimeOut) 
    {
    	long t = System.currentTimeMillis();
    	StringBuffer xmlBuf = new StringBuffer();
    	try 
    	{
    	URL url = new URL(spec1);
    	/*URLConnection con = url.openConnection();
    	con.connect();*/
    	sun.net.www.protocol.https.HttpsURLConnectionImpl conn=(sun.net.www.protocol.https.HttpsURLConnectionImpl) url.openConnection();
    	//sun.net.www.protocol.http.HttpURLConnection conn = (sun.net.www.protocol.http.HttpURLConnection) url.openConnection();
    	if(conn!= null){
    		String urlParameters = "";
    		System.out.println("Connection Created"+conn);
    		// Send post request
    		conn.setDoOutput(true);
    		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
    		wr.writeBytes(urlParameters);
    		wr.flush();
    		wr.close();

    		int responseCode = conn.getResponseCode();
    		System.out.println("\nSending 'POST' request to URL : " + spec1);
    		System.out.println("Post parameters : " + urlParameters);
    		System.out.println("Response Code : " + responseCode);
    	}else{
    	}
    	conn.setConnectTimeout(connectTimeOut);
    	conn.setReadTimeout(connectTimeOut);
    	BufferedReader in= new BufferedReader(new InputStreamReader(conn.getInputStream()));
       	String str;
        	while((str = in.readLine()) != null) {
        		xmlBuf.append(str);
        }
        	in.close();
    	}catch(Exception e){
    		System.out.println("Exception"+e.getMessage());
    	}
    	return new String(xmlBuf);
    }
    
}