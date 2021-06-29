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
  
   public static final String sms_username = "rajmsdg-rmsc";//"rajmsdg-rmsc";
   public static final String sms_password = "cdac@2013";//"cdac@2013";
   public static final String sms_senderId = "RAJSMS";//"RAJSMS";
   public  static final String sms_message  = "Test SMS from By Quartz Job, Sorry for inconvenience!"+new Date();
   public static final String sms_mobileNo = "9891102810";
   public static final  String sms_url="http://msdgweb.mgov.gov.in/esms/sendsmsrequest";
  
   
   public static final String nic_sms_username = "nims.sms";
   public static final String nic_sms_password = "Ty@%23n123";
   public static final String nic_sms_senderId = "NICSMS";
   public  static final String nic_sms_message  = "Test SMS from By Quartz Job, Sorry for inconvenience!"+new Date();
   public static final String nic_sms_mobileNo = "919891102810";
   public static final  String nic_sms_url="https://smsgw.sms.gov.in/failsafe/HttpLink?";
 

}
