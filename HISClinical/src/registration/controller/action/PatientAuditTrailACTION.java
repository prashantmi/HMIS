package registration.controller.action;





import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import registration.RegistrationConfig;
import registration.controller.fb.PatientAuditTrailFB;
import registration.controller.util.PatientAuditTrailUTIL;

 

public class PatientAuditTrailACTION extends DispatchAction
{
	

	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		System.out.println("inside unspecified");
		return this.NEW(mapping,form,request,response);}	
	/**
	 * action mainly called at the initial loading of a page or when a form is reset
	 * refreshes Transaction State
	 * sets all Cr No for modification
	 * sets sys date
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse response){
		System.out.println("inside new  of PatientDetailModificationACTION");
		PatientAuditTrailFB fb = (PatientAuditTrailFB) form;
		fb.reset(mapping,_request);
		WebUTIL.refreshTransState(_request);		
		ControllerUTIL.setSysdate(_request);
		return mapping.findForward("NEW");
	}	
	/**
	 * gets Patient Details based on Cr no
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "SAME"
	 */
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest _request,HttpServletResponse response){
		
		
		ControllerUTIL.setSysdate(_request);
		
		PatientAuditTrailFB _fb = (PatientAuditTrailFB) form;
		
		Status objStatus = new Status();
	    objStatus.add(Status.TRANSINPROCESS);
	    WebUTIL.setStatus(_request, objStatus);
	    PatientAuditTrailUTIL.getPatientAuditTrailEssentials(_request, _fb);
		
	 	return mapping.findForward("NEW");
	
	}
	
//Going to the Ame Page
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		 return this.NEW(mapping, form, request, response);
		
	//	return mapping.findForward("NEW");
	}

//Going to the initpage 
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){		 	
		  return mapping.findForward("CANCEL");		 	   	
	}

	
}
