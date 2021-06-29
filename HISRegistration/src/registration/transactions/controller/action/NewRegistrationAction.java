package registration.transactions.controller.action;

/***************************Start of program*****************************\
## Copyright Information				: C-DAC, Noida  
## Project Name							: HIMS G5
## Name of Developer		 			: Aadil Wasi
## Module Name							: Registration
## Process/Database Object Name			:
## Purpose								: New Patient Registration
## Date of Creation						: Dec-2013
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

public class NewRegistrationAction extends NewPatientRegistrationSUP 
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 
	public String execute() throws Exception
	 {
		System.out.println("NewRegistrationAction :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return init();
	 }
	 
	 public String init()
	 {
		   
		 System.out.println("NewRegistrationAction :: init()");
		 message = "Inside list method";
			
		this.clear();	
		this.setIsUnitWiseRegistration("0");
		HttpSession ses=objRequest.getSession();
		Status objStatus=new Status();
		 
		//CharacterEncoding.setCharacterEncoding(objRequest); 
		if(((String)ses.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null)
		{
			WebUTIL.refreshTransState(objRequest,"NewRegistrationAction");
		}
		
		NewRegistrationUTIL.setAllNewRegistrationEssentials(this, objRequest,objResponse,mapSesion,"NewRegistrationAction");
		 	
	 	WebUTIL.setStatus(objRequest,objStatus);		 	
		 	
		 return "NEW";
			 
		
	 }
	 
	 public void populateformvalues()
	 {
		   
		 System.out.println("NewRegistrationAction :: populateFormValues()");
		 message = "Inside populateFormValues_AJAX() method";
		 
		 //CharacterEncoding.setCharacterEncoding(objRequest); 	
		 NewRegistrationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
		 	
	 }
	 
	 public void getState()
	 {
		   
		 System.out.println("NewRegistrationAction :: getState()");
		 message = "Inside getState() method";
			
		 //CharacterEncoding.setCharacterEncoding(objRequest);  
		 NewRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("NewRegistrationAction :: getDistrict()");
		 message = "Inside getDistrict() method";
		
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 public void getRefDept()
	 {
		   
		 System.out.println("NewRegistrationAction :: getRefDept()");
		 message = "Inside getRefDept() method";
			
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getRefDept_AJAX(objRequest, objResponse);
	 }
	 
	 public void getVerDocExceptCatDoc()
	 {
		   
		 System.out.println("NewRegistrationAction :: getVerDocExceptCatDoc()");
		 message = "Inside getVerDocExceptCatDoc() method";
			
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getVerDocExceptCatDoc_AJAX(objRequest, objResponse);
	 }
	 
	 public String openPatPopup()
	 {
		 System.out.println("NewRegistrationAction :: openPatPopup()");
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
		 System.out.println("NewRegistrationAction :: openPatPopup()");
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
		 System.out.println("NewRegistrationAction :: openPatPopup()");
		 String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		 String alreadyRegisteredFlag= (String)objRequest.getParameter("alreadyRegisteredFlag");
		 this.patPrimaryCatCode=patPrimarCatId==null?"":patPrimarCatId;
		 this.alreadyRegisteredFlag=(alreadyRegisteredFlag==null && alreadyRegisteredFlag.equals(""))?"0":alreadyRegisteredFlag;
		 System.out.println("patPrimarCatId :" + patPrimarCatId);
		 System.out.println("this.alreadyRegisteredFlag :" + this.alreadyRegisteredFlag);
		return "mobilePopup";
	 }
	 public String openWhiteCardPopup()
	 {
		return "WhiteCardSearchPopup";
	 }
	 public void getPatDtlOnPatCatId()
	 {
		 System.out.println("NewRegistrationAction :: getPatDtlOnCatChange()");
		 message = "Inside getPatDtlOnCatChange() method";
		
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getPatDtlOnPatCatId(objRequest,objResponse);
	 }
	 
	 public void getPatDtlOnPatMobile()
	 {
		 System.out.println("NewRegistrationAction :: getPatDtlOnCatChange()");
		 message = "Inside getPatDtlOnCatChange() method";
		
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 NewRegistrationUTIL.getPatDtlOnPatMobile(objRequest,objResponse);
	 }
	 
	 
	 public void save(){
		 
		 System.out.println("NewRegistrationAction :: Save");
		 this.setCallerName("NewRegistrationAction");
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 if(!validateSave1())
			 NewRegistrationUTIL.saveNewPatientRegistration(this,objRequest,objResponse);
	 }
	 
	 public void saveAsNewPatient(){
		 
		 System.out.println("NewRegistrationAction :: SaveAsNewPatient");
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 this.setAsNewPatient("1");
		 if(!validateSave1())
			 NewRegistrationUTIL.saveNewPatientRegistration(this,objRequest,objResponse);
	 }
	 
	 public boolean validateSave1() {
			System.out.println("NewRegistrationAction :: validateSave()");
			
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
