package hr.pis.empReg.transactions.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hr.pis.empReg.transactions.controller.actionsupport.EmpRegistrationSUP;
import hr.pis.empReg.transactions.controller.util.EmpRegistrationUTIL;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

public class EmpRegistrationACTION extends EmpRegistrationSUP 
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 private String flagGuestOrUser;
	 
	public String execute() throws Exception
	{
		System.out.println("EmployeeRegistrationAction :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return init();
	}
	 
	public String init()
	{
		   
		 System.out.println("EmployeeRegistrationAction :: init()");
		 message = "Inside list method";
		 this.clear();	
		 HttpSession ses=objRequest.getSession();
		 Status objStatus=new Status();
		 
		 // For User 
		 flagGuestOrUser="user";
		 
		 WebUTIL.refreshTransState(objRequest);
		 			
		/*if(((String)ses.getAttribute(PisConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null)
		{
			WebUTIL.refreshTransState(objRequest);
		}
		
		//NewRegistrationUTIL.setAllNewRegistrationEssentials(this, objRequest,objResponse,mapSesion);
		 	
	 	WebUTIL.setStatus(objRequest,objStatus);*/		 	
		 
		setCharacterEncoding();
		 
		 return "NEW";
			 
		
	}
	
	public void populateformvalues()
	{
		   
		 System.out.println("EmployeeRegistrationAction :: populateFormValues()");
		 message = "Inside populateFormValues_AJAX() method";
		 
		 setCharacterEncoding();
		 
		 EmpRegistrationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
		 	
	}
	
	 
	 public void getDeptDesigFinalStatus()
	 {
		   
		 System.out.println("EmployeeRegistrationAction :: getNatureOfJob()");
		 message = "Inside populateFormValues_AJAX() method";
			
		 EmpRegistrationUTIL.getDeptDesigFinalStatus_AJAX(this,objRequest,objResponse,mapSesion);
	 }
	 
	/* public void getDesig()
	 {
		   
		 System.out.println("EmployeeRegistrationAction :: getDesig()");
		 message = "Inside populateFormValues_AJAX() method";
			
		 EmpRegistrationUTIL.getDesig_AJAX(objRequest,objResponse);
	 }
	 
	 public void getfinalStatus()
	 {
		   
		 System.out.println("EmployeeRegistrationAction :: getDesig()");
		 message = "Inside populateFormValues_AJAX() method";
			
		 EmpRegistrationUTIL.getfinalStatus_AJAX(objRequest,objResponse);
	 }*/
	 
	public void save()
	{
		 System.out.println("EmployeeRegistrationAction :: Save");
		 setCharacterEncoding();
		 EmpRegistrationUTIL.saveEmployeeRegistration(this,objRequest,objResponse);
	}
	
	public void update()
	{
		 System.out.println("EmployeeRegistrationAction :: update");
		 setCharacterEncoding();
		 EmpRegistrationUTIL.updateEmployeeRegistration(this,objRequest,objResponse);
	}
	
	public void saveValidate()
	{
		 System.out.println("EmployeeRegistrationAction :: singleValidation");
		 setCharacterEncoding();
		 EmpRegistrationUTIL.validateEmployeeRegistrationDetails(this,objRequest,objResponse);
	}
	
	public void delete()
	{
		 System.out.println("EmployeeRegistrationAction :: delete");
		 setCharacterEncoding();
		 EmpRegistrationUTIL.deleteEmployeeRegistration(this,objRequest,objResponse);
	}
	
	public String openEmpPopup()
	{
		System.out.println("EmployeeRegistrationAction :: openEmpPopup()");
		setCharacterEncoding();
		//String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		String employeeUpdateFlag= (String)objRequest.getParameter("employeeUpdateFlag");
		//this.patPrimaryCatCode=patPrimarCatId==null?"":patPrimarCatId;
		this.strEmployeeUpdateFlag=(employeeUpdateFlag==null && employeeUpdateFlag.equals(""))?"0":employeeUpdateFlag;
		//System.out.println("patPrimarCatId :" + patPrimarCatId);
		System.out.println("this.strEmployeeUpdateFlag :" + this.strEmployeeUpdateFlag);
 
		return "popup";
	}
	
	public void getEmpDtl()
	{
		System.out.println("EmployeeRegistrationAction :: getEmpDtl()");
		setCharacterEncoding();
		message = "Inside getEmpDtl() method";
			
		EmpRegistrationUTIL.getEmpDtl(objRequest,objResponse);
	}
	
	public String unValidatedList() throws Exception
	{
		System.out.println("EmployeeRegistrationAction :: unValidatedList()");
		message = "Inside unValidatedList method";
		return "unValidatedList";
	}
	
	public void pendingList()
	{
		 System.out.println("EmployeeRegistrationAction :: pendingList()");
		 message = "Inside pendingList() method";
		
		 EmpRegistrationUTIL.getPendingEmployeeList_AJAX(this, objRequest,objResponse,mapSesion);
	}
	
	public void validatedList()
	{
		 System.out.println("EmployeeRegistrationAction :: validatedList()");
		 message = "Inside validatedList() method";
		
		 EmpRegistrationUTIL.getValidatedEmployeeList_AJAX(this, objRequest,objResponse,mapSesion);
	}
	
	public void rejectedList()
	{
		 System.out.println("EmployeeRegistrationAction :: rejectedList()");
		 message = "Inside rejectedList() method";
		
		 EmpRegistrationUTIL.getRejectedEmployeeList_AJAX(this, objRequest,objResponse,mapSesion);
	}
	
	public void getEmpDtlForValidation()
	{
		System.out.println("EmployeeRegistrationAction :: getEmpDtlForValidation()");
		setCharacterEncoding();
		message = "Inside getEmpDtlForValidation() method";
			
		EmpRegistrationUTIL.getEmpDtlForValidation(objRequest,objResponse);
	}
	
	public void getReportData()
	{
		System.out.println("EmployeeRegistrationAction :: getReportData()");
		setCharacterEncoding();
		message = "Inside getReportData() method";
			
		EmpRegistrationUTIL.getReportData(objRequest,objResponse);
	}
	
	public String openEmployeeDetailsPopup() throws Exception
	{
		System.out.println("EmployeeRegistrationAction :: openEmployeeDetailsPopup()");
		message = "Inside openEmployeeDetailsPopup method";
		return "openEmployeeDetailsPopup";
	}
	
	/*
	public void getValidatorDetails()
	{
		System.out.println("EmployeeRegistrationAction :: getValidatorDetails()");
		setCharacterEncoding();
		message = "Inside getValidatorDetails() method";
			
		EmpRegistrationUTIL.getValidatorDetails(objRequest,objResponse);
	}
	*/
	
	public String guestLogin()
	{
		 System.out.println("EmployeeRegistrationAction :: guestLogin()");
		 this.clear();	
		 WebUTIL.refreshTransState(objRequest);
		 setCharacterEncoding();
		 flagGuestOrUser="guest";
		 return "NEW";
	}
	
	public void validateGuestLoginDtl()
	{
		System.out.println("EmployeeRegistrationAction :: validateGuestLoginDtl()");
		setCharacterEncoding();
		message = "Inside validateGuestLoginDtl() method";
			
		EmpRegistrationUTIL.validateGuestLoginDtl(objRequest,objResponse);
	}
	
	public String tamperErrorPage()
	{
		 System.out.println("EmployeeRegistrationAction :: tamperErrorPage()");
		 WebUTIL.refreshTransState(objRequest);
		 return "tamperErrorPage";
	}
	
	public void setServletContext(ServletContext context) {
			this.objContext=context;
			
	}
	 
	public void setCharacterEncoding() 
	{
		System.out.println("Inside EmployeeRegistrationSUP::setCharacterEncoding function");
		try
		{
			objRequest.setCharacterEncoding("UTF-8");
		}
		catch(UnsupportedEncodingException e)
		{
			System.out.println("Error in Reset -> Character Encoding Try Block = ");
			e.printStackTrace();
		}
			
	}
	
	//business logic
	public String changeLocale() {
	
		return "CHANGELOCALE";

	}
	
	public String getFlagGuestOrUser() {
		return flagGuestOrUser;
	}

	public void setFlagGuestOrUser(String flagGuestOrUser) {
		this.flagGuestOrUser = flagGuestOrUser;
	}

}
