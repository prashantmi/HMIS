 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2008-2009 
 ## Client's Name						: Delhi
 ## Project Name						: AHIMS
 ## Phase								: Development
 ## Name of Developer		 			: Renuka Singh
 ## Module Name							: User Management
 ## Date of creation					: 12-11-08
 ## Purpose								: Admin change password
 ## Previous Form(Calling)				: login.jsp
 ## Functions Used						: Getter & Setters of variables
 ## Name of Tables used for reference 	: 
 ## Name of Tables used for data updation/insertion	: GBLT_HOSPITAL_MST
 ## Next Form	(Called)				: 
 ## Date of Modification				: 
 ## Unit Tested By	& Date				: Renuka Singh & 17-11-2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :Y
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :Y
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :N
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :Y
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:Y
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :Y
 				if No then Detail   	:
     7). Name of Objects Used		            	:
 		i). Object Name		 	:
 		ii) Purpose				:
 		iii). No. of times called		:
     8). Any suggestion					:
     9) Other Deviation					:
 ## Remark						:
 ## Finalization Date					:
 ## Future Alteration (1)				:
 ## Any major change
 	1) Reason					:
 	2) Time in days (Hour)			:
 	3) Change Raised By				:
 	4) Tested(Y/N)				:
 	5) Remark					:
 
*/   
 
package usermgmt.masters;

import usermgmt.*;
import java.sql.*;
import java.util.*;
import HisGlobal.*;
import usermgmt.masters.umgmtSessionConfigXmlHandler;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import login.*;

public class ChangeAdminPassBean {
	
	private String user = "";				//User Name
	private String passwrd = "";			//Old Password
	private String newPasswrd = "";			//New Password
	private String confirmPasswrd = "";		//Confirm Password
	private String status = "";				//status of updation
	private String hospitalcode="";
	boolean retValue = true;
	
/////////Getter methods////////////////////////////////	
	public String getUser() {
		return user;
	}
	
	public String getPasswrd() {
		return passwrd;
	}

	public String getNewPasswrd() {
		return newPasswrd;
	}
	
	public String getConfirmPasswrd() {
		return confirmPasswrd;
	}
	
	public String getStatus() {
		return status;
	}
	
	
/////////////Setter Methods//////////////////////////////////	
	
	public void setUser(String user) {
		this.user = user;
	}
		public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}
	
	public void setNewPasswrd(String newPasswrd) {
		this.newPasswrd = newPasswrd;
	}
	
	public void setConfirmPasswrd(String confirmPasswrd) {
		this.confirmPasswrd = confirmPasswrd;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getHospitalcode() {
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	
/////Function for changing admin password.
public boolean  changePass(HttpServletRequest request) throws Exception
{
	try{
	HisMethods connect = new HisMethods();
	String username =(String)request.getSession().getAttribute("username");
	String hospitalCode =(String)request.getSession().getAttribute("HOSPITAL_CODE");
	String strPass="", strPassOld="";
	String strPasswrd =request.getParameter("passwrd");
	String pwd = request.getParameter("pwd");
	/*retValue= PasswordHash.validatePassword(strPasswrd, pwd);
	System.out.println(retValue);
	if(retValue){
		
		}else{
		status="Wrong Old password";
		return retValue;
		}*/
    String strNewPasswrd = request.getParameter("newPasswrd");
   //strPass = PasswordHash.createHash(strNewPasswrd);
  //  strPassOld = SecurityUtil.encrypt(strNewPasswrd);
	String strUpdateQry = " UPDATE GBLT_HOSPITAL_MST SET GSTR_PASSWORD = '"+strNewPasswrd+"' WHERE GSTR_USER_NAME = '"+username+"' and GNUM_HOSPITAL_CODE='"+hospitalCode+"' ";
	System.out.println("strUpdateQry......."+strUpdateQry);
	retValue = connect.updateRecord(strUpdateQry);
	System.out.println("retValue......."+retValue);
	if(retValue){
	status="Password succesfully changed";
	
	}else{
	status="Wrong password";
	}
	}catch(Exception e){
		System.out.println("Exception Occured"+e);
	}
	
	return retValue;
}


		

}//End of Class
