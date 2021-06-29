package registration.transactions.controller.action;
/**
 * Created By 	: Aadil Wasi
 * Date			: Dec 2013
 */
import java.io.UnsupportedEncodingException;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.filetransfer.config.FileTransferConfig;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import registration.config.CharacterEncoding;
import registration.config.RegistrationConfig;
import registration.transactions.controller.actionsupport.EmgPatientRegistrationSUP;
import registration.transactions.controller.util.EmgRegistrationUTIL;
import registration.transactions.controller.util.NewRegistrationUTIL;

public class EmgRegistrationAction extends EmgPatientRegistrationSUP 
{
	
	 private String message;
	 private String strMsgString;
	 private String strMsgType;
	 private String strNormalMessage;
	 private String strWarning;
	 private String strErrorMsg;
	 
	public String execute() throws Exception
	 {
		System.out.println("EmgRegistrationAction :: execute()");
		message = "Inside execute method";
		boolean ff=objRequest.getSession().isNew();
		return init();
	 }
	 
	 public String init()
	 {
		   
		 System.out.println("EmgRegistrationAction :: init()");
		 message = "Inside list method";
			
			this.clear();	
			HttpSession ses=objRequest.getSession();
			Status objStatus=new Status();
		 
			CharacterEncoding.setCharacterEncoding(objRequest);
			if(((String)ses.getAttribute(RegistrationConfig.SESSION_FLAG_NEW_REGISTRATION_DUPLICATE))==null)
			{
				WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_NAME, "");
				WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, null);
				WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT_DOC, null);
				WebUTIL.refreshTransState(objRequest,"EmgRegistrationAction");
			}
		
			EmgRegistrationUTIL.setAllEmgRegistrationEssentials(this, objRequest,objResponse,mapSesion);
		 	
			WebUTIL.setStatus(objRequest,objStatus);		 	
		 	
			return "NEW";
			 
	 }
	 
	 public void populateformvalues()
	 {
		   
		 System.out.println("EmgRegistrationAction :: populateFormValues()");
		 message = "Inside populateFormValues_AJAX() method";
		 CharacterEncoding.setCharacterEncoding(objRequest);	
		 WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_NAME, "");
		 WebUTIL.setAttributeInSession(objRequest,FileTransferConfig.KEY_UPLOADED_FILE_CONTENT, null);
		 EmgRegistrationUTIL.populateRegistrationFormEssentials_AJAX(this, objRequest,objResponse,mapSesion);
		 	
	 }
	 
	 public void getState()
	 {
		   
		 System.out.println("EmgRegistrationAction :: getState()");
		 message = "Inside getState() method";
			
		 EmgRegistrationUTIL.getState_AJAX(objRequest,objResponse);
	 }
	 public void getDistrict()
	 {
		   
		 System.out.println("EmgRegistrationAction :: getDistrict()");
		 message = "Inside getDistrict() method";
			
		 EmgRegistrationUTIL.getDistrict_AJAX(objRequest,objResponse);
	 }
	 public void getRefDept()
	 {
		   
		 System.out.println("EmgRegistrationAction :: getRefDept()");
		 message = "Inside getRefDept() method";
			
		 EmgRegistrationUTIL.getRefDept_AJAX(objRequest, objResponse);
	 }
	 
	 public String openPatPopup()
	 {
		 System.out.println("EmgRegistrationAction :: openPatPopup()");
		
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
		 System.out.println("EmgRegistrationAction :: getPatDtlOnCatChange()");
		 message = "Inside getPatDtlOnCatChange() method";
			
		 CharacterEncoding.setCharacterEncoding(objRequest);
		 EmgRegistrationUTIL.getPatDtlOnPatCatId(objRequest,objResponse);
	 }
	 
	 public void save(){
		 
		 System.out.println("EmgRegistrationAction :: Save");
		 CharacterEncoding.setCharacterEncoding(objRequest);
		 this.setAsNewPatient("0");
		 //if(!validateSave1())
			 EmgRegistrationUTIL.saveEmgPatientRegistration(this,objRequest,objResponse);
	}
	 //Start:Sheeldarshi:17thOct'14:Duplicacy check
 	public void saveAsNewPatient(){
		 
		 System.out.println("EmgRegistrationAction :: SaveAsNewPatient");
		 //CharacterEncoding.setCharacterEncoding(objRequest); 
		 this.setAsNewPatient("1");
		 //if(!validateSave1())
			 EmgRegistrationUTIL.saveEmgPatientRegistration(this,objRequest,objResponse);
	 }
 		//End:Sheeldarshi:17thOct'14:Duplicacy check
	 public boolean validateSave1() {
			System.out.println("EmgRegistrationAction :: validateSave()");
			
			return EmgRegistrationUTIL.validateSavePatDtl(this, objResponse);
			
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
			EmgRegistrationUTIL.getCashCollectionDetail(objRequest,objResponse, this);
			return "cashCollectionPopup";
			
		}
	 //End
	 
	 public String openWhiteCardPopup()
	 {
		return "WhiteCardSearchPopup";
	 }
 	 //showStaffImageEmgRegistration

	 public void showSaveStaffImage()
	 {
		 EmgRegistrationUTIL.showSaveStaffImage(this,objRequest);
	 }
	}
