 /*************************************************Start of program***************************************************\
 ## Copyright Information                       :     C-DAC, Noida  
 ## Project Name                                :     NIMS
 ## Name of Developer                           :     Sheeldarshi
 ## Module Name                                 :     
 ## Process/Database Object Name                :
 ## Purpose                                     :    This  is used to send SMS.
 ## Date of Creation                            :    26-Feb-2015
 
/*********************************************************************************************************************/
package hisglobal.utility.SMSSender;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

public class SMSHttpsNICPostClient 
{
//	rajmsdg-rmsc$cdac@2013$RAJSMS
	
	static String username = "nims.sms";
	static String password = "Ty@%23n123";
	static String senderid = "NICSMS";
	static String message = "Test SMS from NIMS, Sorry for inconvenience!";
	static String mobileNo = "919873719322";
	static String mobileNos = "9891102810, 9891102810,9891102810";
	// StartTime Format: YYYYMMDD hh:mm:ss
	static String scheduledTime = "20120801 15:40:00";
	static HttpURLConnection connection = null;

	/*public static void main(String[] args) {
		try {

		String baseURL =
		"https://smsgw.sms.gov.in/failsafe/HttpLink?username=nims.sms&pin=Ty@%23n123&";
		String replyTo = "NICSMS";
		String recipient = "919873719322";
		String messageBody = "Test message sent via SMS platform using Java";
		//URL encode message body
		messageBody = URLEncoder.encode(messageBody, "UTF-8");
		//Construct URL
		StringBuffer URI = new StringBuffer();
		URI.append(baseURL);
		URI.append("signature=" + replyTo);
		URI.append("&mnumber=" + recipient);
		URI.append("&message=" + messageBody);
		String result = "";
		//Open connection and send request
		System.out.println(URI.toString());
		URL url = new URL(URI.toString());
		URLConnection conn = url.openConnection();
		//Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(
		conn.getInputStream()));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
		sb.append(line);
		}
		rd.close();
		//Print results
		result = sb.toString();
		System.out.println("Result:" + result);
		} catch (Exception e) {
		e.printStackTrace();
		}
		}*/
	
	public static void sendTextSMSThroughNICSMSGateway(String username,String password, String senderId,String sms_URL,String mobileNo, String message) 
	{
		try {
			String recipient = "91"+mobileNo;
			String messageBody = "Test message sent via SMS platform using Java";
			//URL encode message body
			messageBody = URLEncoder.encode(message, "UTF-8");
			//Construct URL
			StringBuffer URI = new StringBuffer();
			URI.append(sms_URL);
			URI.append("username=" + username);
			URI.append("&pin=" + password);
			URI.append("&signature=" + senderId);
			URI.append("&mnumber=" + recipient);
			URI.append("&message=" + messageBody);
			String result = "";
			
			//Open connection and send request
			System.out.println(URI.toString());
			URL url = new URL(URI.toString());
			URLConnection conn = url.openConnection();
			//Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
			conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
			sb.append(line);
			}
			rd.close();
			//Print results
			result = sb.toString();
			System.out.println("Result:" + result);
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		
	}
	
	
	public static void sendUnicodeSMSThroughNICSMSGateway(String username,String password, String senderId,String sms_URL,String mobileNo, String message) 
	{
		try {
			String recipient = "91"+mobileNo;
			String messageBody = "0915094D092F093E00200917093E0902091709410932094000200915094B";
			
			//Construct URL
			StringBuffer URI = new StringBuffer();
			URI.append(sms_URL);
			URI.append("username=" + username);
			URI.append("&pin=" + password);
			URI.append("&signature=" + senderId);
			URI.append("&mnumber=" + recipient);
			URI.append("&message=" + messageBody);
			URI.append("&msgType=UC");
			String result = "";
			
			//Open connection and send request
			System.out.println(URI.toString());
			URL url = new URL(URI.toString());
			URLConnection conn = url.openConnection();
			//Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
			conn.getInputStream()));
			StringBuffer sb = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
			sb.append(line);
			}
			rd.close();
			//Print results
			result = sb.toString();
			System.out.println("Result:" + result);
			} catch (Exception e) {
			e.printStackTrace();
			}
		
		
		
	}
	
	
	
	
	}