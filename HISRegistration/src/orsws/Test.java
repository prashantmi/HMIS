package orsws;

import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


public class Test
{
	public static void main(String[] args) 
	{
		try
		{
			ServicesProxy objServicesProxy = new ServicesProxy();
			//objServicesProxy.setEndpoint("http://ors.gov.in/orsapi/services");
			//PGI Details
			//String strResponseJsonFormat = objServicesProxy.getAppointmentDetailsDateWise("32a158fb8337290abeb2aa9a0005d4bb",91040002, "2018-07-11","");
			//AIIMS Patna Details
			String strResponseJsonFormat = objServicesProxy.getAppointmentDetailsDateWise("dG9rZW5Ad2ViQGFwcG9pbnQjbmlj",91100007, "2018-07-31","");
			
						
			//String count=objServicesProxy.getDateWiseAppointmentCount("dG9rZW5Ad2ViQGFwcG9pbnQjbmlj","2018-07-30");
			
			//System.out.println("totao aptient registered on 11th july is "+count);
			System.out.println(strResponseJsonFormat);
			NICAppointmentServiceUtil objNICAppointmentServiceUtil = new NICAppointmentServiceUtil();
			objNICAppointmentServiceUtil.insertIntoPreviousRegistration(strResponseJsonFormat);
			
			//TimerTask objTask = new AppointmentSchedularForNIC();
			//Timer objTimer = new Timer();
			//Date obj
			//objTimer.schedule(objTask, new Date(115, 8, 3, 1, 0), 1000*60*60*24);
			
//			   DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		        Calendar cal = Calendar.getInstance();
//		        cal.add(Calendar.DATE, -1);    
//		        System.out.println( dateFormat.format(cal.getTime()));
//		        
		        
//			JSONParser objJsonParser = new JSONParser();
//			JSONObject objJson = (JSONObject) objJsonParser.parse(strResponseJsonFormat);
//			System.out.println(objJson.get("details"));
//			JSONObject obj= (JSONObject)objJson.get("details");
//			System.out.println(obj.get("status"));
//			System.out.println(obj.get("status_code"));
//			System.out.println(obj.get("data"));
//			JSONArray lang= (JSONArray) obj.get("data");
//			for(int i=0; i<lang.size(); i++){
//				System.out.println("The " + i + " element of the array: "+lang.get(i));
//			}
//			Iterator i = lang.iterator();
//			// take each value from the json array separately
//			while (i.hasNext()) {
//				JSONObject innerObj = (JSONObject) i.next();
//				System.out.println("ors_ref_no "+ innerObj.get("ors_ref_no"));
//				System.out.println("dept_name "+ innerObj.get("dept_name"));
//				System.out.println("address "+ innerObj.get("address"));
//				System.out.println("name "+ innerObj.get("name"));
//				System.out.println("dob "+ innerObj.get("dob"));
//				System.out.println("gender "+ innerObj.get("gender"));
//				System.out.println("app_id "+ innerObj.get("app_id"));
//				System.out.println("app_date "+ innerObj.get("app_date"));
//				System.out.println("mobile "+ innerObj.get("mobile"));
//			}
//			System.out.println(obj.get("totalRecord"));
//			//System.out.println(objJson.get("status_code"));
//			//System.out.println(objJson.get("status"));
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
