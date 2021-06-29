package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.io.UnsupportedEncodingException;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.SplPatientRegistrationSUP;
import registration.transactions.controller.util.NewRegistrationUTIL;
import registration.transactions.controller.util.SplRegistrationUTIL;

public class SplRegistrationAction extends SplPatientRegistrationSUP 
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 
	public String execute() throws Exception
	 {
		System.out.println("SplRegistrationAction :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return init();
	 }
	 
	 public String init()
	 {
		   
		 System.out.println("SplRegistrationAction :: init()");
		 message = "Inside list method";
			
			this.clear();	
			HttpSession ses=objRequest.getSession();
			Status objStatus=new Status();
		 
			CharacterEncoding.setCharacterEncoding(objRequest);
		if(((String)ses.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null)
		{
			WebUTIL.refreshTransState(objRequest,"SplRegistrationAction");
		}
		
		SplRegistrationUTIL.setAllSplRegistrationEssentials(this, objRequest,objResponse,mapSesion);
		 	
	 	WebUTIL.setStatus(objRequest,objStatus);		 	
		 	
		 return "NEW";
			 
		
	 }
	 
	 public void populateformvalues()
	 {
		   
		 System.out.println("SplRegistrationAction :: populateFormValues()");
		 message = "Inside populateFormValues_AJAX() method";
		
		 CharacterEncoding.setCharacterEncoding(objRequest);
		 SplRegistrationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
		 	
	 }
	 
	 public void getState()
	 {
		   
		 System.out.println("SplRegistrationAction :: getState()");
		 message = "Inside getState() method";
			
		SplRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("SplRegistrationAction :: getDistrict()");
		 message = "Inside getDistrict() method";
			
		SplRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 public void getRefDept()
	 {
		   
		 System.out.println("SplRegistrationAction :: getRefDept()");
		 message = "Inside getRefDept() method";
			
		SplRegistrationUTIL.getRefDept_AJAX(objRequest, objResponse);
	 }
	 
	 public String openPatPopup()
	 {
		 System.out.println("SplRegistrationAction :: openPatPopup()");
		 
		 CharacterEncoding.setCharacterEncoding(objRequest);
		 String patPrimarCatId= (String)objRequest.getParameter("patPrimarCatId");
		 String alreadyRegisteredFlag= (String)objRequest.getParameter("alreadyRegisteredFlag");
		 this.patPrimaryCatCode=patPrimarCatId==null?"":patPrimarCatId;
		 this.alreadyRegisteredFlag=(alreadyRegisteredFlag==null && alreadyRegisteredFlag.equals(""))?"0":alreadyRegisteredFlag;
		 System.out.println("patPrimarCatId :" + patPrimarCatId);
		 System.out.println("this.alreadyRegisteredFlag :" + this.alreadyRegisteredFlag);
		 
		return "popup";
	 }
	 public void getPatDtlOnPatCatId()
	 {
		 System.out.println("SplRegistrationAction :: getPatDtlOnCatChange()");
		 message = "Inside getPatDtlOnCatChange() method";
		
		 CharacterEncoding.setCharacterEncoding(objRequest);
		 SplRegistrationUTIL.getPatDtlOnPatCatId(objRequest,objResponse);
	 }
	 
	 public void save(){
		 
		 System.out.println("SplRegistrationAction :: Save");
		 CharacterEncoding.setCharacterEncoding(objRequest);
		 if(!validateSave1())
			 SplRegistrationUTIL.saveSplPatientRegistration(this,objRequest,objResponse);
	 }
	 
	 public boolean validateSave1() {
			System.out.println("SpecialRegistrationAction :: validateSave()");
			
			return SplRegistrationUTIL.validateSavePatDtl(this, objResponse);
			
		}
	 
	 public void setServletContext(ServletContext context) {
			this.objContext=context;
			
		}

	}
