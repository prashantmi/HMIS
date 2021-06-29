package hr.pis.empReg.reports.controller.action;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpSession;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hr.pis.empReg.reports.controller.actionsupport.ListOfRegisteredEmpRptSUP;
import hr.pis.empReg.reports.controller.util.ListOfRegisteredEmpRptUTIL;

public class ListOfRegisteredEmpRptACTION extends ListOfRegisteredEmpRptSUP
{
	
	private static final long serialVersionUID = 1L;
	private String message;
	
	public String execute() throws Exception
	 {		
		System.out.println("ListOfRegisteredEmpRptACTION :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();		
		return init();
	 }
	 
	 public String init()
	 {
		 System.out.println("ListOfRegisteredEmpRptACTION :: init()");
			this.clear();
			HttpSession ses = objRequest.getSession();
			Status objStatus = new Status();
			WebUTIL.refreshTransState(objRequest);
			setCharacterEncoding();	
			this.strPageFlag="rptPage";
			return "NEW";
		
	 }
	 	
	 
	 public void print()
	 {
		 System.out.println("ListOfRegisteredEmpRptACTION :: print()");
		 message = "Inside print() method";
		 try
		 {
			
			 ListOfRegisteredEmpRptUTIL.createReport(this, objRequest,objResponse);
			
		 }
		 catch(Exception e)
		 {
			 System.out.println("Exceptino Occured in print method : ");
			 e.printStackTrace();
		 } 
	 }
	 
	 
	 public void populateFormValues()
	 {  
			 System.out.println("ListOfRegisteredEmpRptACTION :: populateFormValues()");
			 message = "Inside populateFormValues_AJAX() method";
			 setCharacterEncoding();
			 ListOfRegisteredEmpRptUTIL.populateFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);			
	 }
	 
		
		
	 public void setCharacterEncoding() 
		{
			System.out.println("ListOfRegisteredEmpRptACTION::setCharacterEncoding function");
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
