package registration.transactions.controller.action;



import javax.servlet.ServletContext;

import registration.config.CharacterEncoding;
import registration.transactions.controller.actionsupport.VerificationDocumnetSUP;
import registration.transactions.controller.util.verificationDocumentUTIL;

public class verificationDocumentACTION extends VerificationDocumnetSUP{
	
	
	 private String callerName;

	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	public String execute(){
		System.out.println("inside unspecified of ............................validation document");
		return this.NEW();		
	}
	/**
	 * action mainly called at the initial loading of a page or when a form is reset
	 * sets all  verification documents essentials
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public String NEW(){
		CharacterEncoding.setCharacterEncoding(objRequest);
		verificationDocumentUTIL.setAllVerificationDocEssentials(super.getObjRequest(),this.callerName);	
	 	return "NEW";
	}
	@Override
	public void setServletContext(ServletContext arg0) {
		// TODO Auto-generated method stub
		
	}
	public String getCallerName() {
		return callerName;
	}
	public void setCallerName(String callerName) {
		this.callerName = callerName;
	}
	
}//end of class
