 /*************************************************Start of program***************************************************\
 ## Copyright Information                       :     C-DAC, Noida  
 ## Project Name                                :     NIMS
 ## Name of Developer                           :     Sheeldarshi
 ## Module Name                                 :     
 ## Process/Database Object Name                :
 ## Purpose                                     :    This  is used to send SMS.
 ## Date of Creation                            :    26-Feb-2015
 
/*********************************************************************************************************************/

package bbpubliclogin;

import java.util.Date;

public interface EmailConfig 
{
  
/*    String email_username = "eraktkosh@cdac.in";//"pgihis@pgimer.edu.in";//"rajmsdg-rmsc";
    String email_password = "bld-support@cdac123"; //"pgihis@@1234";//"cdac@2013";
    //String email_defaultId = "pgihis@pgimer.edu.in";
    String email_host="smtp.cdac.in"; //"mail.pgimer.edu.in";
    String email_fromAddress="eraktkosh@cdac.in";//"pgihis@pgimer.edu.in";
	String email_port = "587";
*/

	
    String email_username = "uruserid";//"pgihis@pgimer.edu.in";//"rajmsdg-rmsc";
    String email_password = "urpwd"; //"pgihis@@1234";//"cdac@2013";
    //String email_defaultId = "pgihis@pgimer.edu.in";
    String email_host="mail.nic.in"; //"mail.pgimer.edu.in";
    String email_fromAddress="uruserid@gov.in";//"pgihis@pgimer.edu.in";
	String email_port = "465";


/*    String email_username = "telanganareportt@gmail.com";//"pgihis@pgimer.edu.in";//"rajmsdg-rmsc";
    String email_password = "adm_gandhi"; //"pgihis@@1234";//"cdac@2013";
    //String email_defaultId = "pgihis@pgimer.edu.in";
    String email_host="localhost"; //"mail.pgimer.edu.in";
    String email_fromAddress="telanganareportt@gmail.com";//"pgihis@pgimer.edu.in";
	String email_port = "587";
*/
	
}
