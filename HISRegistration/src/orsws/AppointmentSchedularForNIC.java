package orsws;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

public class AppointmentSchedularForNIC extends TimerTask
{

	@Override
	public void run() 
	{
		try
		{
			String strDate = getYesterdayDateString();		
			ServicesProxy objServicesProxy = new ServicesProxy();
			//objServicesProxy.setEndpoint("http://ors.gov.in/orsapi/services");
			// String strResponseJsonFormat = objServicesProxy.getAppointmentDetailsDateWise("dG9rZW5Ad2ViQGFwcG9pbnQjbmlj",91040002, strDate,"");
			
			//PGI Details
		//	String strResponseJsonFormat = objServicesProxy.getAppointmentDetailsDateWise("32a158fb8337290abeb2aa9a0005d4bb",91040002, strDate,"");
			
			//AIIMS Patna Details
			String strResponseJsonFormat = objServicesProxy.getAppointmentDetailsDateWise("dG9rZW5Ad2ViQGFwcG9pbnQjbmlj",91100007, strDate,"");
			
			System.out.println(strResponseJsonFormat);
			
			//{"details":{"status_code":1,"data":[{"transaction_id":"NA","transaction_date":"NA","tran_date":"NA","transaction_status":"NA","address":"VPO Mandi Kalan^^^Haryana^126116^^91","scode":"6","gender":"1","request_amount":"NA","mobile":"8607909161","gurdian":"Pardeep Kumar","dept_name":"ENT","ors_ref_no":"991803367881","third_party_hmis_hospital_identifier":" ","relation":"C/O","dob":"09/10/2017","name":"Mr.^VIVEK^^KUMAR","app_date":"16/07/2018","receive_amount":"NA","aadhaar":"0","app_id":"18071104000200015"},{"transaction_id":"NA","transaction_date":"NA","tran_date":"NA","transaction_status":"NA","address":"House no99gali no8guru nanakpuru^^^Punjab^143001^^91","scode":"3","gender":"1","request_amount":"NA","mobile":"9779266297","gurdian":"Vikramjeet singh","dept_name":"Medicine","ors_ref_no":"991803368133","third_party_hmis_hospital_identifier":"two","relation":"C/O","dob":"24/06/2014","name":"Mr.^GURKIRAT^^SINGH","app_date":"12/07/2018","receive_amount":"NA","aadhaar":"0","app_id":"18071104000200032"},{"transaction_id":"NA","transaction_date":"NA","tran_date":"NA","transaction_status":"NA","address":"Delhi^^^Delhi^0^^91","scode":"7","gender":"2","request_amount":"NA","mobile":"9599566882","gurdian":"Mr K C Gotra","dept_name":"Orthopaedics","ors_ref_no":"991803368327","third_party_hmis_hospital_identifier":" ","relation":"C/O","dob":"13/04/1982","name":"Mrs.^GARIMA^^","app_date":"12/07/2018","receive_amount":"NA","aadhaar":"0","app_id":"18071104000200047"},{"transaction_id":"NA","transaction_date":"NA","tran_date":"NA","transaction_status":"NA","address":"#293/23, Nehru Garden Colony,Kaithal^^^Haryana^136027^^91","scode":"6","gender":"2","request_amount":"NA","mobile":"7015392779","gurdian":"Jai Parkash","dept_name":"Dermatology","ors_ref_no":"991803368717","third_party_hmis_hospital_identifier":" ","relation":"C/O","dob":"07/11/1987","name":"Mrs.^Kavita^^Rani","app_date":"18/07/2018","receive_amount":"NA","aadhaar":"XXXXXXXX4306","app_id":"18071104000200082"}],"totalRecord":4,"status":"success"}}
			//NICAppointmentServiceUtil objNICAppointmentServiceUtil = new NICAppointmentServiceUtil();
			
			
			//objNICAppointmentServiceUtil.insertIntoPreviousRegistration(strResponseJsonFormat);
			
			//System.out.println("***** NIC PORTAL ******** The Service for data transfer from NIC to portal done !!!");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String getYesterdayDateString() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);    
        return dateFormat.format(cal.getTime());
}

}
