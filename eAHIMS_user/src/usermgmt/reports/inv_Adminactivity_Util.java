package usermgmt.reports;

import usermgmt.FuncLib;
import java.sql.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import HisGlobal.HisResultSet;
import HisGlobal.HisMethods;
import java.util.StringTokenizer;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.CallableStatement;
import HisGlobal.HisResultSet;


public class inv_Adminactivity_Util extends FuncLib
{
	public List modulecombo(String seatId) throws Exception
	{
		String query="";
		
		query	= " SELECT GNUM_MODULE_ID,GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST A "+
				  " WHERE GNUM_SEAT_ID='"+seatId+"' order by initcap(GSTR_MODULE_NAME)";
		
		List modList	= new ArrayList();
	
		modList	= (ArrayList) new HisGlobal.HisComboValue().getComboList(query);		
		
		return modList;		
	
	}//end of moduleCombo
	
	public List usercombo(inv_Adminactivity_ActionForm myForm) throws Exception
	{
		
		System.out.println("inside user combo------------------");
		String query ="";		
		/*query= 	" select GNUM_SEATID,"+
				" (select GSTR_SEAT_NAME from GBLT_SEAT_MST where GNUM_SEATID=a.GNUM_SEATID )seat_name  "+
				" from GBLT_ROLE_SEAT_MENU_DTL a where GNUM_MENU_ID='"+myForm.getMenu()+"'";*/
		
		query= 	" SELECT x.gnum_user_seatid, x.gstr_user_name "+
		   		" FROM gblt_user_mst x, gblt_role_seat_menu_dtl y "+
		   		" WHERE x.gnum_user_seatid = y.gnum_seatid and GNUM_MENU_ID='"+myForm.getMenu()+"'order by initcap(gstr_user_name) "; 
		
		System.out.println("user query "+query);				
		ArrayList userList	= new ArrayList();
		userList	= (ArrayList)new HisGlobal.HisComboValue().getComboList(query);	
			
	
		return  userList;
		
	
	}//end of usercombo
	
	public List menucombo(inv_Adminactivity_ActionForm myForm) throws Exception
	{
		
		String query ="";	
		String t_module = myForm.getMod();
		if(t_module.length()==1)
			t_module = "0"+t_module;
			
			query= " select GNUM_MENU_ID, initcap(GSTR_MENU_NAME)"+ 
				" from gblt_menu_mst"+  
				" where substr(GNUM_MENU_ID,2,2) = '"+t_module+"'"+
				" and GNUM_ISVALID=1"+
				" and GSTR_MENUCLASS_ID='L' order by initcap(GSTR_MENU_NAME)";
		
		
		List menuList	= new ArrayList();
		menuList	= (ArrayList) new HisGlobal.HisComboValue().getComboList(query);				
		return  menuList;
		
	
	}//end of menuCombo
	
	public List getAdminReport(inv_Adminactivity_ActionForm myForm)throws Exception
	{
		
		java.util.List rptList	= null;
		String query="";
		
		try{
			String activity_condition = "";
		if((myForm.getActivity2().equals("1"))&&(myForm.getActivity1().equals("1")))
			activity_condition = "";
		else if(myForm.getActivity1().equals("1"))					
			activity_condition = "AND upper(HAUSTR_TYPE_ACTIVITY)=upper('update')";	
		else if(myForm.getActivity2().equals("1"))					
			activity_condition = "AND upper(HAUSTR_TYPE_ACTIVITY)=upper('delete')";	
		
			
		query=	  " select to_char(HAUDT_ACTIVITY_DATE,'dd-mon-yy '),"+
				  " HAUSTR_TABLE_NAME, HAUSTR_IP_ADDRESS, HAUSTR_NEW_VAL, HAUSTR_OLD_VAL"+
				  " from HAUT_ADMIN_ACTIVITY"+ 
				  " where HAUSTR_MODULE_ID='"+myForm.getMod( )+"'"+ 
				  " and HAUNUMR_MENU_ID='"+myForm.getMenu( )+"'"+
				  " and HAUDT_ACTIVITY_DATE between to_date('"+myForm.getFromDate()+"','dd-mon-yyyy') and  to_date('"+myForm.getToDate()+"','dd-mon-yyyy')"+activity_condition+
				  " order by HAUDT_ACTIVITY_DATE";
		
		System.out.println("audit queyr "+query);
		
		
		rptList	= super.getDetails(query,5);
		
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
	}
	
	public List modulename(inv_Adminactivity_ActionForm myForm) throws Exception
	{
		String query="";
		
		query	= " SELECT GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST A "+
				  " WHERE GNUM_MODULE_ID='"+myForm.getMod( )+"'";
		
				
		
		List menuList	= null;
		
		List modulename=super.getDetails(query,1);
		return modulename;
	
	}//end of modulename list
	
	public List menuname(inv_Adminactivity_ActionForm myForm) throws Exception
	{
		String query ="";	
			
		
		query= " select GSTR_MENU_NAME from GBLT_MENU_MST where GNUM_MENU_ID='"+myForm.getMenu( )+"'";		
									
		List menuname 	= null;
		menuname =super.getDetails(query,1);				
		return  menuname;	
		
	}//end of menuname list
	
	
	public List tablename(inv_Adminactivity_ActionForm myForm) throws Exception
	{
		String query ="";			
		query= " select table_name,table_name  from tabs where table_name like 'GBLT%'";			
									
		
		ArrayList tablelist	= new ArrayList();
		tablelist	= (ArrayList)new HisGlobal.HisComboValue().getComboList(query);	
			
		return  tablelist;		
		
	}//end of menuname list
	
	
	
	public List getNewAdminReport(inv_Adminactivity_ActionForm myForm)throws Exception
	{
		
		java.util.List newrptList	= null;
		String query="";
		
		try{
		
		String activity_condition = "";			
		if((myForm.getActivity2().equals("1"))&&(myForm.getActivity1().equals("1")))
			activity_condition = "";
		else if(myForm.getActivity1().equals("1"))					
			activity_condition = "AND upper(HAUSTR_TYPE_ACTIVITY)=upper('update')";	
		else if(myForm.getActivity2().equals("1"))					
			activity_condition = "AND upper(HAUSTR_TYPE_ACTIVITY)=upper('delete')";	
		
			
		query=	  " select to_char(HAUDT_ACTIVITY_DATE,'dd-mon-yy '),"+
				  " HAUSTR_TABLE_NAME, HAUSTR_IP_ADDRESS, HAUSTR_NEW_VAL, HAUSTR_OLD_VAL"+
				  " from HAUT_ADMIN_ACTIVITY"+				  
				  " where HAUDT_ACTIVITY_DATE between to_date('"+myForm.getFromDate()+"','dd-mon-yyyy') and  to_date('"+myForm.getToDate()+"','dd-mon-yyyy')"+activity_condition+
				  " AND HAUSTR_TABLE_NAME= '"+myForm.getTable()+"' "+
				   " AND HAUSTR_SEAT_ID='"+myForm.getSeat()+"' "+				  
				  " order by HAUDT_ACTIVITY_DATE";
		
		
		System.out.println("query 2 "+query);
		
			 newrptList	= super.getDetails(query,5);
		 
		}
		catch(Exception e)
		{
			System.out.println("Exception in admin "+e);
		}
		 			
		return  newrptList;		
	
	}//end of function
	
	public List seatname(inv_Adminactivity_ActionForm myForm) throws Exception
	{
		String query ="";			
		query=	" select GNUM_USERID,GSTR_USER_NAME " +
				" FROM GBLT_USER_MST U,GBLT_SEAT_MST S "+
				" WHERE U.GNUM_USER_SEATID = S.GNUM_SEATID "+
				" AND S.GNUM_ISVALID = 1 ";	   
			   	
		
		ArrayList seatname	= new ArrayList();
		seatname	= (ArrayList)new HisGlobal.HisComboValue().getComboList(query);	
			
		return  seatname;		
		
	}//end of menuname list


}
