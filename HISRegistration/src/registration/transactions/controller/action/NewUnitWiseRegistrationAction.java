package registration.transactions.controller.action;

/***************************Start of program*****************************\
## Copyright Information				: C-DAC, Noida  
## Project Name							: HIMS G5
## Name of Developer		 			: Singaravelan
## Module Name							: Registration
## Process/Database Object Name			:
## Purpose								: New Patient Registration on Unit Wise
## Date of Creation						: Aug-2015
## Modification Log						:				
##		Modify Date						: 
##		Reason	(CR/PRS)				: 
##		Modify By						: 
*/

import java.io.UnsupportedEncodingException;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.NewPatientRegistrationSUP;
import registration.transactions.controller.util.NewRegistrationUTIL;

public class NewUnitWiseRegistrationAction extends NewPatientRegistrationSUP 
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 
	public String execute() throws Exception
	 {
		System.out.println("NewUnitWiseRegistrationAction :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return init();
	 }
	 
	 public String init()
	 {
		   
		 System.out.println("NewUnitWiseRegistrationAction :: init()");
		 message = "Inside list method";
			
		this.clear();	
		this.setIsUnitWiseRegistration("1");
		HttpSession ses=objRequest.getSession();
		Status objStatus=new Status();
		 
		//CharacterEncoding.setCharacterEncoding(objRequest); 
		if(((String)ses.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null)
		{
			WebUTIL.refreshTransState(objRequest,"NewUnitWiseRegistrationAction");
		}
		
		NewRegistrationUTIL.setAllNewRegistrationEssentials(this, objRequest,objResponse,mapSesion,"NewUnitWiseRegistrationAction");
		 	
	 	WebUTIL.setStatus(objRequest,objStatus);		 	
		 	
		 return "NEW";
			 
		
	 }
	 
	 public void populateformvalues()
	 {
		   
		 System.out.println("NewUnitWiseRegistrationAction :: populateFormValues()");
		 message = "Inside populateFormValues_AJAX() method";
		 
		 //CharacterEncoding.setCharacterEncoding(objRequest); 	
		 NewRegistrationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
		 	
	 }
	 
	 public void getState()
	 {
		   
		 System.out.println("NewUnitWiseRegistrationAction :: getState()");
		 message = "Inside getState() method";
			
		 //CharacterEncoding.setCharacterEncoding(objRequest);  
		 NewRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("NewUnitWiseRegistrationAction :: getDistrict()");
		 message = "Inside getDistrict() method";
		
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 public void getRefDept()
	 {
		   
		 System.out.println("NewUnitWiseRegistrationAction :: getRefDept()");
		 message = "Inside getRefDept() method";
			
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getRefDept_AJAX(objRequest, objResponse);
	 }
	 
	 public void getVerDocExceptCatDoc()
	 {
		   
		 System.out.println("NewUnitWiseRegistrationAction :: getVerDocExceptCatDoc()");
		 message = "Inside getVerDocExceptCatDoc() method";
			
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getVerDocExceptCatDoc_AJAX(objRequest, objResponse);
	 }
	 
	 public String openPatPopup()
	 {
		 System.out.println("NewUnitWiseRegistrationAction :: openPatPopup()");
		 String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		 String alreadyRegisteredFlag= (String)objRequest.getParameter("alreadyRegisteredFlag");
		 this.patPrimaryCatCode=patPrimarCatId==null?"":patPrimarCatId;
		 this.alreadyRegisteredFlag=(alreadyRegisteredFlag==null && alreadyRegisteredFlag.equals(""))?"0":alreadyRegisteredFlag;
		 System.out.println("patPrimarCatId :" + patPrimarCatId);
		 System.out.println("this.alreadyRegisteredFlag :" + this.alreadyRegisteredFlag);
		 
		return "popup";
	 }
	 public String openAlreadyRegPatPopup()
	 {
		 System.out.println("NewUnitWiseRegistrationAction :: openPatPopup()");
		 String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		 String alreadyRegisteredFlag= (String)objRequest.getParameter("alreadyRegisteredFlag");
		 this.patPrimaryCatCode=patPrimarCatId==null?"":patPrimarCatId;
		 this.alreadyRegisteredFlag=(alreadyRegisteredFlag==null && alreadyRegisteredFlag.equals(""))?"0":alreadyRegisteredFlag;
		 System.out.println("patPrimarCatId :" + patPrimarCatId);
		 System.out.println("this.alreadyRegisteredFlag :" + this.alreadyRegisteredFlag);
		 
		return "alreadyRegPopup";
	 }
	 public String openMobileSearchPatPopup()
	 {
		 System.out.println("NewUnitWiseRegistrationAction :: openPatPopup()");
		 String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		 String alreadyRegisteredFlag= (String)objRequest.getParameter("alreadyRegisteredFlag");
		 this.patPrimaryCatCode=patPrimarCatId==null?"":patPrimarCatId;
		 this.alreadyRegisteredFlag=(alreadyRegisteredFlag==null && alreadyRegisteredFlag.equals(""))?"0":alreadyRegisteredFlag;
		 System.out.println("patPrimarCatId :" + patPrimarCatId);
		 System.out.println("this.alreadyRegisteredFlag :" + this.alreadyRegisteredFlag);
		return "mobilePopup";
	 }
	 
	 public void getPatDtlOnPatCatId()
	 {
		 System.out.println("NewUnitWiseRegistrationAction :: getPatDtlOnCatChange()");
		 message = "Inside getPatDtlOnCatChange() method";
		
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getPatDtlOnPatCatId(objRequest,objResponse);
	 }
	 
	 public void getPatDtlOnPatMobile()
	 {
		 System.out.println("NewUnitWiseRegistrationAction :: getPatDtlOnCatChange()");
		 message = "Inside getPatDtlOnCatChange() method";
		
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getPatDtlOnPatMobile(objRequest,objResponse);
	 }
	 
	 
	 public void save(){
		 
		 System.out.println("NewUnitWiseRegistrationAction :: Save");
		 this.setCallerName("NewUnitWiseRegistrationAction");
		 this.setIsUnitWiseRegistration("1");
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 if(!validateSave1())
			 NewRegistrationUTIL.saveNewPatientRegistration(this,objRequest,objResponse);
	 }
	 
	 public void saveAsNewPatient(){
		 
		 System.out.println("NewUnitWiseRegistrationAction :: SaveAsNewPatient");
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 this.setAsNewPatient("1");
		 this.setIsUnitWiseRegistration("1");
		 if(!validateSave1())
			 NewRegistrationUTIL.saveNewPatientRegistration(this,objRequest,objResponse);
	 }
	 
	 public boolean validateSave1() {
			System.out.println("NewUnitWiseRegistrationAction :: validateSave()");
			
			return NewRegistrationUTIL.validateSavePatDtl(this, objResponse);
			
		}
	 public void setServletContext(ServletContext context) {
			this.objContext=context;
			
		}
	
	 /* #Start					:Sheeldarshi 
		#Modify Date(CR/PRS)	:22ndMay'15 
		#Reason					:The Total amount collected by concerned operator should be displayed on registration & Billing Processes
	*/

	 public String CASHCOLLECTIONPOPUP() {

			//BillingFB formBean = (BillingFB) form;
			String target="cashCollectionPopup";
			NewRegistrationUTIL.getCashCollectionDetail(objRequest,objResponse, this);
			return "cashCollectionPopup";
			
		}
	}
