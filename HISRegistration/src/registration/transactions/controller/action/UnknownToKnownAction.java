package registration.transactions.controller.action;
/**
 * Copyright Information	: C-DAC, Noida  
 * Project Name 			: HMIS G5
 * Name of Developer		: Aadil Wasi
 * Module Name				: Unknown to Known conversion
 * Date of Creation			: Dec 2013
 */
import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.filetransfer.config.FileTransferConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.transactions.controller.actionsupport.UnknownToKnownSUP;
import registration.transactions.controller.util.DskPatientModificationUTIL;
import registration.transactions.controller.util.EmgRegistrationUTIL;
import registration.transactions.controller.util.UnknownToKnownUTIL;
import registration.transactions.controller.util.NewRegistrationUTIL;

public class UnknownToKnownAction extends UnknownToKnownSUP
{
	
	 private String message;
	 public String execute() throws Exception
	 {
		message = "Inside execute method";
		return NEW();
	 }
	 /**
		 * action mainly called at the initial loading of a page or when a form is reset
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "NEW"
		 */
		public String NEW()
		{
			super.clear();
			WebUTIL.refreshTransState(super.getObjRequest(),"UnknownToKnownAction");		
			UnknownToKnownUTIL.setSysdate(super.getObjRequest());
			//DskPatientModificationUTIL.setAllCRNoForModification(super.getObjRequest());
			Status status = new Status();
			status.add(Status.NEW);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_NAME, "");
			WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, null);
			WebUTIL.setStatus(super.getObjRequest(), status);
			this.setAfterGo("0");
			return "NEW";
		}

	 /**
		 * gets Patient Detail
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "NEW"
		 */
		public String GETPATDTL()
		{
			UnknownToKnownUTIL.setPatientDtlForMod(this,super.getObjRequest());
			return "NEW";
		}

		 
		 public void populateformvalues()
		 {
			   
			 System.out.println("NewRegistrationAction :: populateFormValues()");
			 message = "Inside populateFormValues_AJAX() method";
			 CharacterEncoding.setCharacterEncoding(objRequest);	
			 UnknownToKnownUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
			 	
		 }
		 
		 /**
			 * updates Address Details
			 * 
			 * @param mapping -object of ActionMapping
			 * @param form - object of ActionForm
			 * @param request - HttpServletRequest
			 * @param response - HttpServletResponse
			 * @return calls the action "NEW"
			 */
		
		public String SAVE()
		{
			 this.setAsNewPatient("0");
			 CharacterEncoding.setCharacterEncoding(objRequest);
			int x=UnknownToKnownUTIL.updateRecord(this,objRequest,objResponse);
			if(x==0){
				this.setErrorMessage("Patient details are saved successfully");
				
				this.clear();
				return NEW();
			}
			else{
				return "NEW";
			}
	
		}

		/*Modify Date			: 24thNov'14
		  Reason	(CR/PRS)	: Secondary UHID check incorporation
		  Modify By				: Sheeldarshi */
	 	public String saveAsNewPatient(){
			 
			 System.out.println("EmgRegistrationAction :: SaveAsNewPatient");
			 
			 this.setAsNewPatient("1");
			
			 	int x= UnknownToKnownUTIL.updateRecord(this,objRequest,objResponse);
			 	if(x==0){
					this.setErrorMessage("Patient details are saved successfully");
				this.isDuplicatePatient="0";
					this.clear();
					return NEW();
				}
				else{
					return "NEW";
				}
		 }
	 	//End
	 	
	 public void setServletContext(ServletContext context) {
			this.objContext=context;
			
		}
	 public String cancel() throws Exception
	 {
		 return execute();
	 }
	 public String Clear() throws Exception
	 {
		 return GETPATDTL();
	 }
	 public void getState()
	 {
		   
		 System.out.println("NewRegistrationAction :: getState()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("NewRegistrationAction :: getDistrict()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 
	
	}
