package registration.transactions.controller.action;
/***************************Start of program*****************************\
## Copyright Information				: C-DAC, Noida  
## Project Name							: HIMS G5
## Name of Developer		 			: Aadil Wasi
## Module Name							: Registration
## Process/Database Object Name			:
## Purpose								: Patient Detail Modification
## Date of Creation						: Dec-2013
## Modification Log						:				
##		Modify Date						: 
##		Reason	(CR/PRS)				: 
##		Modify By						: 
*/

import java.io.UnsupportedEncodingException;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.filetransfer.config.FileTransferConfig;

import javax.servlet.ServletContext;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.PatientDetailModificationSUP;
import registration.transactions.controller.util.NewRegistrationUTIL;
import registration.transactions.controller.util.PatientModificationUTIL;
import registration.transactions.controller.util.UnknownToKnownUTIL;

public class PatientDetailModificationAction extends PatientDetailModificationSUP
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 
	 public String execute() throws Exception
	 {
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
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
			WebUTIL.refreshTransState(super.getObjRequest(),"PatientDetailModificationAction");	
			WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_NAME, "");
			WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, null);
			WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC, null);
			PatientModificationUTIL.setSysdateAndDefaultCrNoFormat(super.getObjRequest());
			Status status = new Status();
			status.add(Status.NEW);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.LOCAL_LANGUAGE, PatientModificationUTIL.getHospitalVO(objRequest).getLocalLangCode());
			//WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.LOCAL_LANGUAGE, "marathi");
			WebUTIL.setStatus(super.getObjRequest(), status);
			this.setAfterGo("0");
			this.setisDuplicatePatient("0");
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
			PatientModificationUTIL.setPatientDtlForMod(this,super.getObjRequest());
			return "NEW";
		}

		 
		 public void populateformvalues()
		 {
			   
			 System.out.println("EmgRegistrationAction :: populateFormValues()");
			 message = "Inside populateFormValues_AJAX() method";
				
			 CharacterEncoding.setCharacterEncoding(objRequest);
			 PatientModificationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
			 	
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
			try {
				this.setPatFirstNameInMultiLang(new String(this.patFirstNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
				this.setPatMiddleNameInMultiLang(new String(this.patMiddleNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
				this.setPatLastNameInMultiLang(new String(this.patLastNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			 this.setAsNewPatient("0");
			int x=PatientModificationUTIL.updateRecord(this,objRequest);
			if(x==0){
				this.setErrorMessage("Patient Details modified successfully");
				this.isDuplicatePatient="0";
				this.clear();
				return NEW();
			}
			else{
				return "NEW";
			}
	
		}
		/*Modify Date			: 5thDec'14
		  Reason	(CR/PRS)	: Secondary UHID check incorporation
		  Modify By				: Sheeldarshi */
	 	public String saveAsNewPatient(){
			 
			 System.out.println("EmgRegistrationAction :: SaveAsNewPatient");
			 
			 this.setAsNewPatient("1");
			 try {
					this.setPatFirstNameInMultiLang(new String(this.patFirstNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
					this.setPatMiddleNameInMultiLang(new String(this.patMiddleNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
					this.setPatLastNameInMultiLang(new String(this.patLastNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			 	int x= PatientModificationUTIL.updateRecord(this,objRequest);
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
	 	
		/**
		 * sets Address Selected to Modify
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "NEW"
		 */
		public void MODIFYADDRESS()
		{
			PatientModificationUTIL.setAddressToModify(this, objRequest,objResponse);
			this.setHmode("MODIFY");
			
		}
		/**
		 * sets Address Selected to Add
		 * 
		 * @param mapping -object of ActionMapping
		 * @param form - object of ActionForm
		 * @param request - HttpServletRequest
		 * @param response - HttpServletResponse
		 * @return action forwards to the output view called "NEW"
		 */
		public void ADDADDRESS()
		{
			PatientModificationUTIL.setAddressToAdd(this, objRequest,objResponse);
			this.setHmode("ADD");
			
		}
		
		public void SAMEASCURRENTADDRESSADD()
		{
			PatientModificationUTIL.setSameAsCurrentAddressToAdd(this, objRequest,objResponse);
			this.setHmode("ADD");
			
		}
		
		public void SAMEASCURRENTADDRESSMODIFY()
		{
			PatientModificationUTIL.setSameAsCurrentAddressToModify(this, objRequest,objResponse);
			this.setHmode("MODIFY");
			
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
		 System.out.println("EmgRegistrationAction :: getState()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		 System.out.println("EmgRegistrationAction :: getDistrict()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 
	 public void setServletContext(ServletContext context) {
			this.objContext=context;
	 }
	 
	
}
