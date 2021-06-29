package bbpubliclogin;

import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;



public class SMSGateway {
	private static String username = "pgimsdg";
	private static String password = "ram@ram1";
	private static String senderid = "PGICHD";
	static String message = "Test SMS from MSDG, Hello how r u.....!!";
	static String mobileNo = "09971344266";
	static String mobileNos = "9868466942,9910021861";
	// StartTime Format: YYYYMMDD hh:mm:ss
	static String scheduledTime = "20120705 16:10:00"; 
	static HttpURLConnection connection = null;
    public static ArrayList<String> arrlistMobileNo = new ArrayList<String>();
	
    
    
public static String getUsername() {
		return username;
	}

	public static void setUsername(String username) {
		SMSGateway.username = username;
	}

	public static String getPassword() {
		return password;
	}

	public static void setPassword(String password) {
		SMSGateway.password = password;
	}

	public static String getSenderid() {
		return senderid;
	}

	public static void setSenderid(String senderid) {
		SMSGateway.senderid = senderid;
	}

// Method for sending single SMS.
public static HttpURLConnection sendSingleSMS(String username,
			String password, String senderId, 
String mobileNo, String message) {
try {
	
	URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
	connection = (HttpURLConnection) url.openConnection();
	connection.setDoInput(true);
	connection.setDoOutput(true);
	connection.setRequestMethod("POST");
	connection.setFollowRedirects(true);
	
//	System.out.println("in method of java.......");
	//System.out.println("in method of java.......");
		String smsservicetype = "singlemsg"; // For single message.
			String query = "username=" + URLEncoder.encode(username)
				+ "&password=" + URLEncoder.encode(password)
				+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
				+ "&content=" + URLEncoder.encode(message) + "&mobileno="
				+ URLEncoder.encode(mobileNo) + "&senderid="
				+ URLEncoder.encode(senderId);

		connection.setRequestProperty("Content-length", String
			.valueOf(query.length()));
		connection.setRequestProperty("Content-Type",
			"application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent",
			"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

		// open up the output stream of the connection
			DataOutputStream output = new DataOutputStream(connection
					.getOutputStream());

		// write out the data
		int queryLength = query.length();
		output.writeBytes(query);
		// output.close();

		// get ready to read the response from the cgi script
		DataInputStream input = new DataInputStream(connection
					.getInputStream());

		// read in each character until end-of-stream is detected
	/*	for (int c = input.read(); c != -1; c = input.read())
			System.out.print((char) c);
	*/	input.close();
		//System.out.println("Resp Code:" + connection.getResponseCode());
	//	System.out.println("Resp Message:"	+ connection.getResponseMessage());
	
	if(connection.getResponseCode() == 200)
	{
		/*String query1="update pgi_sms_push_dtl set IS_SENT = 1 where MOBILENO="+mobileNo;
    	Statement del1 = con.createStatement();
    	del1.execute(query1);
    	String query2="update pgi_sms_push_log set IS_SENT = 1 where MOBILENO="+mobileNo;
    	Statement del2 = con.createStatement();
    	del2.execute(query2);*/
		arrlistMobileNo.add(mobileNo);
		
	}
	
	
	
	} catch (Exception e) {
		System.out.println("Something bad just happened.");
		System.out.println(e);
		e.printStackTrace();
	}

	return connection;
}

//method of sending scheduled sms //
public  HttpURLConnection sendScheduledSMS(String username, String password, 
		String senderId, String mobileNos, String message, String scheduledTime) {

			try {
				URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
				connection = (HttpURLConnection) url.openConnection();
				connection.setDoInput(true);
				connection.setDoOutput(true);
				connection.setRequestMethod("POST");
				connection.setFollowRedirects(true);

				
				String smsservicetype = "schmsg"; // For Scheduled message.

				String query = "username=" + URLEncoder.encode(username)
					+ "&password=" + URLEncoder.encode(password)
					+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
					+ "&content=" + URLEncoder.encode(message)
		+"&bulkmobno="+ URLEncoder.encode(mobileNos, "UTF-8") 
		+ "&senderid=" + URLEncoder.encode(senderid) + "&time="
					+ URLEncoder.encode(scheduledTime, "UTF-8"); 

				connection.setRequestProperty("Content-length", String
					.valueOf(query.length()));
				connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
				connection.setRequestProperty("User-Agent",
					"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

				// open up the output stream of the connection
				DataOutputStream output = new DataOutputStream(connection
							.getOutputStream());

				// write out the data
				int queryLength = query.length();
				output.writeBytes(query);
				// output.close();

			//	System.out.println("Resp Code:" + connection.getResponseCode());
			//	System.out.println("Resp Message:"+ connection.getResponseMessage());

				// get ready to read the response from the cgi script
				DataInputStream input = new DataInputStream(connection
					.getInputStream());

				// read in each character until end-of-stream is detected
				/*for (int c = input.read(); c != -1; c = input.read())
					System.out.print((char) c);*/
					input.close();
					//System.out.println("Resp Code:" + connection.getResponseCode());
				//	System.out.println("Resp Message:"							+ connection.getResponseMessage());

				} catch (Exception e) {
					System.out.println("Something bad just happened.");
					System.out.println(e);
					e.printStackTrace();
				}
				return connection;
			}

//method for sending bulk SMS
public  HttpURLConnection sendBulkSMS(String username,
		String password, String senderId, String mobileNos, String message) {
try {
	URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
	connection = (HttpURLConnection) url.openConnection();
	connection.setDoInput(true);
	connection.setDoOutput(true);
	connection.setRequestMethod("POST");
	connection.setFollowRedirects(true);

	
		String smsservicetype = "bulkmsg"; // For bulk msg
		String query = "username=" + URLEncoder.encode(username)
			+ "&password=" + URLEncoder.encode(password)
			+ "&smsservicetype=" + URLEncoder.encode(smsservicetype)
			+ "&content=" + URLEncoder.encode(message)
+ "&bulkmobno="+ URLEncoder.encode(mobileNos, "UTF-8") 
+ "&senderid=" + URLEncoder.encode(senderid);

		connection.setRequestProperty("Content-length", String
			.valueOf(query.length()));
		connection.setRequestProperty("Content-Type",
			"application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent",
			"Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

		// open up the output stream of the connection
		DataOutputStream output = new DataOutputStream(connection
			.getOutputStream());

		// write out the data
		int queryLength = query.length();
		output.writeBytes(query);
		// output.close();

		System.out.println("Resp Code:" + connection.getResponseCode());
		System.out.println("Resp Message:" + connection.getResponseMessage());

		// get ready to read the response from the cgi script
		DataInputStream input = new DataInputStream(connection
			.getInputStream());

		// read in each character until end-of-stream is detected
		for (int c = input.read(); c != -1; c = input.read())
			System.out.print((char) c);
		input.close();
		System.out.println("Resp Code:" + connection.getResponseCode());
		System.out.println("Resp Message:"
				+ connection.getResponseMessage());

		} catch (Exception e) {
			System.out.println("Something bad just happened.");
			System.out.println(e);
			e.printStackTrace();
		}
		return connection;
	}
public static void sendSingleSMSThroughSMSGateway(String username,String password, String senderId,String mobileNo, String message) 
{
	DataOutputStream output =null;
	try 
	{
		URL url = new URL("http://msdgweb.mgov.gov.in/esms/sendsmsrequest");
		connection = (HttpURLConnection) url.openConnection();
		connection.setDoInput(true);
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setFollowRedirects(true);

		String smsservicetype = "singlemsg"; // For single message.
		String query = "username=" + URLEncoder.encode(username, "UTF-8")+ "&password=" + URLEncoder.encode(password, "UTF-8")+ "&smsservicetype=" + URLEncoder.encode(smsservicetype, "UTF-8")
		+ "&content=" + URLEncoder.encode(message, "UTF-8") + "&mobileno="+ URLEncoder.encode(mobileNo, "UTF-8") + "&senderid="+URLEncoder.encode(senderId, "UTF-8");

		connection.setRequestProperty("Content-length", String.valueOf(query.length()));
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		connection.setRequestProperty("User-Agent","Mozilla/4.0 (compatible; MSIE 5.0; Windows 98; DigExt)");

		output = new DataOutputStream(connection.getOutputStream());
		output.writeBytes(query);
		
		System.out.println("Resp Code:" + connection.getResponseCode());
		System.out.println("Resp Message:"+ connection.getResponseMessage());
	}
	catch (Exception e) 
	{
		System.out.println("Something bad just happened.");
		System.out.println(e);
		e.printStackTrace();
	}
	finally
	{
		if(output!=null)
		{
			try 
			{
				output.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}


}
