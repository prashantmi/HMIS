package test;

//
//Example of using the SMS platform from Java.
//
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HTTPSampleCode {
public static void main(String[] args) {
try {

	//http://smsgw.sms.gov.in/failsafe/HttpLink?username=XXXXXX&pin=XXXX&message=<FEFF092A093009400915094D09370923002009380902092609470936>&mnumber=mobilenumber&signature=<senderid>&msgType=UC
	
	String baseURL ="https://smsgw.sms.gov.in/failsafe/HttpLink?username=nims.sms&pin=Ty@%23n123&";
String replyTo = "NICSMS";
String recipient = "919873719322";
String messageBody = "0915094D092F093E00200917093E0902091709410932094000200915094B";


//URL encode message body
//messageBody = URLEncoder.encode(messageBody, "UTF-8");
//Construct URL
StringBuffer URI = new StringBuffer();
URI.append(baseURL);
URI.append("signature=" + replyTo);
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