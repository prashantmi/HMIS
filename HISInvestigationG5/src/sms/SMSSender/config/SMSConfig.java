 /*************************************************Start of program***************************************************\
 ## Copyright Information                       :     C-DAC, Noida  
 ## Project Name                                :     NIMS
 ## Name of Developer                           :     Sheeldarshi
 ## Module Name                                 :     
 ## Process/Database Object Name                :
 ## Purpose                                     :    This  is used to send SMS.
 ## Date of Creation                            :    26-Feb-2015
 
/*********************************************************************************************************************/

package sms.SMSSender.config;

import java.util.Date;

public class SMSConfig 
{
	
	  
	   public static final String sms_username = "eraktkosh";//"pgimsdg";//"eraktkosh";//"rajmsdg-rmsc";
	   public static final String sms_password ="eraktkosh#123"; //"ram@ram1";//"eraktkosh#123";//"cdac@2013";
	   public static final String sms_senderId = "EBLOOD";//"PGICHD";//"EBLOOD";//"RAJSMS";
	   public  static final String sms_message  = "Test SMS from By Quartz Job, Sorry for inconvenience!"+new Date();
	   public static final String sms_mobileNo = "9891102810";
	   public static final  String sms_url="http://msdgweb.mgov.gov.in/esms/sendsmsrequest";//"http://msdgweb.mgov.gov.in/esms/sendsmsrequest";//"http://msdgweb.mgov.gov.in/esms/sendsmsrequest";
	 
  

}
