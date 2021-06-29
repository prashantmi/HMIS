package usermgmt.reports;

import usermgmt.FuncLib;

import java.util.List;

import javax.servlet.http.HttpSession;

import usermgmt.umgmtGlobal;


public class inv_UserLogUtil extends FuncLib
{
	
	
	
	public List usercombo(String HospitalCode) throws Exception
	{
		
		String query="";
		//String Hospital_Code=umgmtGlobal.Hospital_Code;
		
		query	= "select GNUM_USERID , GSTR_USER_NAME from GBLT_USER_MST  where GNUM_HOSPITAL_CODE= '"+HospitalCode+"' and GNUM_ISVALID <> 0 order by initcap(GSTR_USER_NAME)";
		List userList	= null;		
		userList	= new  HisGlobal.HisComboValue().getComboList(query);
	
				  
		return userList;					  
		
	}//end of usercombo
	
	public List getUserReport( inv_UserLogActionForm myForm) throws Exception
	{
		java.util.List rptList	= null;
		String query="";

		String Hospital_Code=myForm.getHospital_Code();
		System.out.println("Hospital Code in Util Class"+Hospital_Code);
		
		try{
			
		
			
		query=" SELECT   u.gnum_userid, (SELECT gstr_user_name "+
		        " FROM gblt_user_mst "+
		        " WHERE gnum_userid = u.gnum_userid and gnum_hospital_code='"+Hospital_Code+"') user_name, "+				        
				" TO_CHAR (u.gdt_login_date, 'DD-Mon-YYYY hh:mi AM'), "+				
				" nvl(TO_CHAR (u.gdt_logutt_date, 'DD-Mon-YYYY hh:mi AM'),'*'), u.gstr_ip_number, "+				
				" u.gnum_seat_id, (SELECT gstr_seat_name "+
				" FROM gblt_seat_mst "+
				" WHERE gnum_seatid = u.gnum_seat_id and gnum_hospital_code='"+Hospital_Code+"') seat_name "+
				" FROM gblt_user_log u "+
				" WHERE u.gnum_userid = '"+myForm.getUser()+"' "+
				" AND to_date(to_char(u.gdt_login_date, 'dd-mon-yyyy'), 'dd-mon-yyyy') " +
				" >= TO_DATE ('"+myForm.getFromDate()+"', 'dd-mon-yyyy') "+
				" AND to_date(to_char(u.gdt_login_date, 'dd-mon-yyyy'), 'dd-mon-yyyy') " +
				" <= TO_DATE ('"+myForm.getToDate()+"', 'dd-mon-yyyy') and gnum_hospital_code='"+Hospital_Code+"'" +						 
				" ORDER BY u.gdt_login_date ";
			



		System.out.println("reprot query "+query);
					
				
		 rptList	= super.getDetails(query,7);
		 System.out.println("LIST WILL BE"+ rptList);
		}
		catch(Exception e)
		{
			System.out.println("Exception in admin "+e);
		}
		 			
		return  rptList;		
	
	}//end of function
	
	
 
	public List getSystemDate() throws Exception
	{
		
		String qdate="select to_char(sysdate,'DD-MM-YYYY'),to_char(sysdate,'HH:MI:SS AM') from dual";
		List lst = super.getDetails(qdate,2);		
		return lst;
		
	}//end of sysdate
	
	
	

}//end of userlog util class
