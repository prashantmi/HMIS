package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.io.UnsupportedEncodingException;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.ServletContext;

import registration.config.CharacterEncoding;
import registration.transactions.controller.actionsupport.EmgPatDetailModificationSUP;
import registration.transactions.controller.util.DskPatientModificationUTIL;
import registration.transactions.controller.util.EmgPatDetailModificationUTIL;
import registration.transactions.controller.util.NewRegistrationUTIL;

public class EmgPatDetailModificationAction extends EmgPatDetailModificationSUP
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
			CharacterEncoding.setCharacterEncoding(objRequest);
			super.clear();
			WebUTIL.refreshTransState(super.getObjRequest(),"EmgPatDetailModificationAction");		
			EmgPatDetailModificationUTIL.setSysdate(super.getObjRequest());
			EmgPatDetailModificationUTIL.setAllCRNoForModification(super.getObjRequest());
			Status status = new Status();
			status.add(Status.NEW);
			this.setPatCrNo((String)super.getObjRequest().getSession().getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT));
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
			CharacterEncoding.setCharacterEncoding(objRequest);
			EmgPatDetailModificationUTIL.setPatientDtlForMod(this,super.getObjRequest());
			return "NEW";
		}

		 
		 public void populateformvalues()
		 {
			   
			 System.out.println("EmgPatDetailModificationAction :: populateFormValues()");
			 message = "Inside populateFormValues_AJAX() method";
			 CharacterEncoding.setCharacterEncoding(objRequest);	
			 EmgPatDetailModificationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
			 	
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
			//CharacterEncoding.setCharacterEncoding(objRequest);
			
			try {
				this.setPatFirstNameInMultiLang(new String(this.patFirstNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
				this.setPatMiddleNameInMultiLang(new String(this.patMiddleNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
				this.setPatLastNameInMultiLang(new String(this.patLastNameInMultiLang.getBytes("ISO-8859-1"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int x=EmgPatDetailModificationUTIL.updateRecord(this,objRequest);
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
			EmgPatDetailModificationUTIL.setAddressToModify(this, objRequest,objResponse);
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
			EmgPatDetailModificationUTIL.setAddressToAdd(this, objRequest,objResponse);
			this.setHmode("ADD");
			
		}
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
		   
		 System.out.println("EmgPatDetailModificationAction :: getState()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("EmgPatDetailModificationAction :: getDistrict()");
		 message = "Inside populateFormValues_AJAX() method";
			
		NewRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 
}
