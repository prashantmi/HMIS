package usermgmt.reports;

import org.apache.struts.action.*;
import org.apache.struts.actions.*;
import javax.servlet.http.*;
import javax.servlet.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

/*
http://localhost:8080/admin/usermgmt/reports/user_log.cnt
*/


public class inv_UserlogAction extends DispatchAction
{

	inv_UserLogUtil myUtil	= new inv_UserLogUtil();
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) 
	{
		
		try
		{		
			System.out.println("IN UNSPECIFIED");
			inv_UserLogActionForm myForm	=  (inv_UserLogActionForm) form;  	
			String seatid=(String)request.getSession().getAttribute("SEATID");
			
			String HospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
			System.out.println("seat id in action field "+seatid);
			System.out.println("Hospital Code in action field "+HospitalCode);
			
			myForm.setSeatId(seatid);
			myForm.setHospital_Code(HospitalCode);
			
			System.out.println("SeatId and Hos_Code"+myForm.getSeatId()+","+myForm.getHospital_Code());
			
			request.setAttribute("userList",myUtil.usercombo(HospitalCode));		
			System.out.println("IN userlist");
		}
		
		catch(Exception e)
		{
			System.out.println("Exception there in Action "+e);
		}
		
		
		return mapping.findForward("init");			
		
	}//end off unspecified
	
	public ActionForward USER_LIST(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) 
	{
		
		try
		{		
			
			
			inv_UserLogActionForm myForm	=  (inv_UserLogActionForm) form; 	
			
			System.out.println("IN USER_LIST "+myForm.getHmode());
			
			String seatid=(String)request.getSession().getAttribute("SEATID");
			String HospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
			
			myForm.setSeatId(seatid);
			myForm.setHospital_Code(HospitalCode);
			
			request.setAttribute("userList",myUtil.usercombo(HospitalCode));
			request.setAttribute("rptList",myUtil.getUserReport(myForm));	
			request.setAttribute("hmode",myForm.getHmode());
			request.setAttribute("fromDate",myForm.getFromDate());
			request.setAttribute("toDate",myForm.getToDate());
		
		}
		catch(Exception e)
		{
			System.out.println("Exception there in action "+e);
		}		
		
		
		return mapping.findForward("init");
	
	}//end of User_list	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) 
	{
		try
		{		
		
			inv_UserLogActionForm myForm	=  (inv_UserLogActionForm) form;
			
			String seatid=(String)request.getSession().getAttribute("SEATID");
			String HospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
			
			myForm.setSeatId(seatid);
			myForm.setHospital_Code(HospitalCode);
			
			request.setAttribute("userList",myUtil.usercombo(HospitalCode));		
			System.out.println("IN userlist");
		}
		
		catch(Exception e)
		{
			System.out.println("Exception there in Action "+e);
		}
		
		
		return mapping.findForward("init");			
		
	}//end of Cancel
	
	public ActionForward PRINT(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) 
	{
		
		try
		{		
			
			
			inv_UserLogActionForm myForm	=  (inv_UserLogActionForm) form; 
			
			String seatid=(String)request.getSession().getAttribute("SEATID");
			String HospitalCode=(String)request.getSession().getAttribute("HOSPITAL_CODE");
			
			myForm.setSeatId(seatid);
			myForm.setHospital_Code(HospitalCode);
			
			System.out.println("IN USER_LIST "+myForm.getHmode());
			
			request.setAttribute("userList",myUtil.usercombo(HospitalCode));
			request.setAttribute("rptList",myUtil.getUserReport(myForm));	
			request.setAttribute("systemDate",myUtil.getSystemDate());
			request.setAttribute("fromDate",myForm.getFromDate());
			request.setAttribute("toDate",myForm.getToDate());
		
		}
		catch(Exception e)
		{
			System.out.println("Exception there in action "+e);
		}		
		
		
		return mapping.findForward("rpt");
	
	}//end of User_list	


}//end of class userlog action
