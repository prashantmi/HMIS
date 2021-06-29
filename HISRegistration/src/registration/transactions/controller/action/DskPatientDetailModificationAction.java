package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.io.UnsupportedEncodingException;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.filetransfer.config.FileTransferConfig;

import javax.servlet.ServletContext;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.DskPatientDetailModificationSUP;
import registration.transactions.controller.util.DskPatientModificationUTIL;
import registration.transactions.controller.util.NewRegistrationUTIL;
import registration.transactions.controller.util.PatientModificationUTIL;

public class DskPatientDetailModificationAction extends DskPatientDetailModificationSUP
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 private String strPatRegCatCode="";
	 
	 public String execute() throws Exception
	 {
		message = "Inside execute method";
		if(strPatRegCatCode.isEmpty())
			strPatRegCatCode="11";
//		System.out.println("strPatRegCatCode :"+strPatRegCatCode);
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
			System.out.println("DskPatientDetailModificationAction :: NEW()");
			super.clear();
			WebUTIL.refreshTransState(super.getObjRequest(),"DskPatientDetailModificationAction");	
			WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_NAME, "");
			WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, null);
			DskPatientModificationUTIL.setSysdateAndDefaultCrNoFormat(super.getObjRequest());
			if(!strPatRegCatCode.equals("11"))
				super.getObjRequest().setAttribute(RegistrationConfig.REGISTRATION_DESK_TYPE, RegistrationConfig.REGISTRATION_DESK_TYPE_SPECIAL);
			DskPatientModificationUTIL.setAllCRNoForModification(super.getObjRequest());
			Status status = new Status();
			status.add(Status.NEW);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
			WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.LOCAL_LANGUAGE, PatientModificationUTIL.getHospitalVO(objRequest).getLocalLangCode());
			//WebUTIL.setAttributeInSession(objRequest, RegistrationConfig.LOCAL_LANGUAGE, "marathi");
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
			System.out.println("DskPatientDetailModificationAction :: GETPATDTL()");
			CharacterEncoding.setCharacterEncoding(objRequest);
			DskPatientModificationUTIL.setPatientDtlForMod(this,super.getObjRequest());
			//this.setAfterGo("1");
			return "NEW";
		}

		 
		 public void populateformvalues()
		 {
			   
			 System.out.println("DskPatientDetailModificationAction :: populateFormValues()");
			 message = "Inside populateFormValues_AJAX() method";
				
			 CharacterEncoding.setCharacterEncoding(objRequest);
			 DskPatientModificationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
			 	
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
			System.out.println("DskPatientDetailModificationAction :: SAVE()");
			//CharacterEncoding.setCharacterEncoding(objRequest);
			
			try {
				this.setPatFirstNameInMultiLang(new String(this.patFirstNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
				this.setPatMiddleNameInMultiLang(new String(this.patMiddleNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
				this.setPatLastNameInMultiLang(new String(this.patLastNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int x=DskPatientModificationUTIL.updateRecord(this,objRequest);
			if(x==0){
				this.setErrorMessage("Patient Details modified successfully");
				this.clear();
				return NEW();
			}
			else{
				return "NEW";
			}
	
		}


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
			System.out.println("DskPatientDetailModificationAction :: MODIFYADDRESS()");
			DskPatientModificationUTIL.setAddressToModify(this, objRequest,objResponse);
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
			System.out.println("DskPatientDetailModificationAction :: ADDADDRESS()");
			DskPatientModificationUTIL.setAddressToAdd(this, objRequest,objResponse);
			this.setHmode("ADD");
			
		}
	
	 public String cancel() throws Exception
	 {
		 return execute();
	 }
	 public String Clear() throws Exception
	 {
		 CharacterEncoding.setCharacterEncoding(objRequest);
		 return GETPATDTL();
	 }
	 public void getState()
	 {
		   
		 System.out.println("DskPatientDetailModificationAction :: getState()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("DskPatientDetailModificationAction :: getDistrict()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 
	 public void setServletContext(ServletContext context) {
		this.objContext=context;
	 }
	public String getStrPatRegCatCode() {
		return strPatRegCatCode;
	}
	public void setStrPatRegCatCode(String strPatRegCatCode) {
		this.strPatRegCatCode = strPatRegCatCode;
	}
	 
	
}
