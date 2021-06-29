package registration.controller.action;
import org.apache.struts.actions.DispatchAction;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import registration.RegistrationConfig;
import registration.controller.fb.SearchByNameFB;
import registration.controller.util.SearchByNameUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

public class SearchByNameACTION extends DispatchAction{
	
	/**
	 * the default action called when a page is loaded for the first time
	* @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 * */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("inside unspecified of ............................validation document");
		return this.NEW(mapping,form,request,response);		
	}
	
	
	/**
	 * action mainly called at the initial loading of a page or when a form is reset
	 * refreshes Transaction State
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
		public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		//System.out.println("inside new 11111111111111111111");
		SearchByNameFB fb = (SearchByNameFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		SearchByNameUTIL.getEssentialForSearch(fb,request);
	 	return mapping.findForward("NEW");
	}
	
	/**
	 * sets Patient details based on  name 
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "SAME"
	 */
	 
	public ActionForward SEARCHBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		//System.out.println("inside getpatient detail in old patient///////"); 
		SearchByNameFB fb = (SearchByNameFB) form;
		SearchByNameUTIL.setPatientDtlByName(fb,request);
		//System.out.println("search by name action complete.............");
		return mapping.findForward("SAME");
	}
}
