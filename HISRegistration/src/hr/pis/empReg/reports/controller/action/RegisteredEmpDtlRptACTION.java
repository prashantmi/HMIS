package hr.pis.empReg.reports.controller.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hr.pis.empReg.reports.controller.actionsupport.RegisteredEmpDtlRptSUP;
import hr.pis.empReg.reports.controller.util.RegisteredEmpDtlRptUTIL;

public class RegisteredEmpDtlRptACTION extends RegisteredEmpDtlRptSUP
{
	private static final long serialVersionUID = 1L;
	private String message;
	
	public String execute() throws Exception
	 {		
		System.out.println("RegisteredEmpDtlRptAction :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();		
		return init();
	 }
	 
	 public String init()
	 {
		 System.out.println("RegisteredEmpDtlRptAction :: init()");
			this.clear();
		/*	HttpSession ses = objRequest.getSession();
			Status objStatus = new Status();
			WebUTIL.refreshTransState(objRequest);
			setCharacterEncoding();			
			return "inputPage";  */
		
			
			 
			 String rptMode="1";
			 if(objRequest.getParameter("rptMode")!=null)
				 rptMode=objRequest.getParameter("rptMode").toString();
			 System.out.println("rptModeeeeeeeeeeeeeeeeee"+rptMode);
			 if(rptMode.equalsIgnoreCase("1")) {
				this.intRptMode="1";
				 print();
				 return null;
			 }
			 
			 else if(rptMode.equalsIgnoreCase("2")) {
				 this.intRptMode="2";
				 RegisteredEmpDtlRptUTIL.populateLoginEmpDetails(this, objRequest,objResponse);
				 return "NEW";
			 }
			 else{				 
				 this.intRptMode="3";
				 return "NEW";
			 }
	 }
			 
			 
			 
			 
			 
			 
			 

	 
	 
	 public void print() {
		 System.out.println("RegisteredEmpDtlRptAction :: print()");
		 message = "Inside print() method";
		 try
		 {
			 System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@");
			 RegisteredEmpDtlRptUTIL.createReport(this, objRequest,objResponse);
			 System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@#############");
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exceptino Occured in print method : ");
			 e.printStackTrace();
		 } 
	 }
	 
	
		
		
	 public void setCharacterEncoding() 
		{
			System.out.println("RegisteredEmpDtlRptAction::setCharacterEncoding function");
			try 
			{
				objRequest.setCharacterEncoding("UTF-8");
			} 
			catch (UnsupportedEncodingException e) 
			{
				System.out.println("Error in Reset -> Character Encoding Try Block = ");
				e.printStackTrace();
			}
		}

		// business logic
		public String changeLocale() {
			return "CHANGELOCALE";
		}
		
	



	
	

}
