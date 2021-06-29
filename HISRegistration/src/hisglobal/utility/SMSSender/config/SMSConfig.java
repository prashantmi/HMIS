 /*************************************************Start of program***************************************************\
 ## Copyright Information                       :     C-DAC, Noida  
 ## Project Name                                :     NIMS
 ## Name of Developer                           :     Sheeldarshi
 ## Module Name                                 :     
 ## Process/Database Object Name                :
 ## Purpose                                     :    This  is used to send SMS.
 ## Date of Creation                            :    26-Feb-2015
 
/*********************************************************************************************************************/

package hisglobal.utility.SMSSender.config;

import java.util.Date;

public class SMSConfig 
{
  
// AIIMS Patna Configuration
   public static final String sms_username = "aiims_patna";//"eraktkosh";
   public static final String sms_password = "aiimsp@1234";//"eraktkosh#123";//"cdac@2013";
   public static final String sms_senderId = "AIIMSP";//"EBLOOD";//"RAJSMS";
   public static final String sms_message  = "Test SMS from By Quartz Job, Sorry for inconvenience!"+new Date();
   public static final String sms_mobileNo = "9891102810";
   public static final String sms_url="https://msdgweb.mgov.gov.in/esms/sendsmsrequest";
   public static final String secureKey = "cc9538d1-f0f3-4cab-8ff2-117cbc3e34bc";//"c5d170b9-cde3-4933-a48b-54816a6a502b";
  

   //ERaktKosh Configuration
  /* public static final String   sms_username="eraktkosh";
   public static final String   sms_password="eraktkosh#123";
   public static final String  sms_message="Test SMS from By Quartz Job, Sorry for inconvenience!"+new Date();
   public static final String  sms_senderId="EBLOOD";
   public static final String  sms_mobileNo="7017045842";
   public static final String  secureKey="c5d170b9-cde3-4933-a48b-54816a6a502b";*/
 

}
