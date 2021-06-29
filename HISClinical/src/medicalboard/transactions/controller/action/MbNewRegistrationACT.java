package medicalboard.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.transactions.controller.fb.MbNewRegistrationFB;
import medicalboard.transactions.controller.utl.MbNewRegistrationUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class MbNewRegistrationACT extends CSRFGardTokenAction {

	/**
	 * the default action called when a page is loaded for the first time
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action  "NEW"
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	
	/**
	 * action mainly called at the initial loading of a page or when a form is reset
	 * refreshes Transaction State
	 * sets all  new registration essentials
	 * sets department options
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse 
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		generateToken(request);
		MbNewRegistrationFB mRegistrationFB = (MbNewRegistrationFB) form;
		mRegistrationFB.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
    	MbNewRegistrationUTL.setMsNewRegistrationEssentials(mRegistrationFB,request);
	 	return mapping.findForward("NEW");
	}
	
	
    public ActionForward GETCHECKLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MbNewRegistrationFB mRegistrationFB = (MbNewRegistrationFB) form;
    	MbNewRegistrationUTL.getCheckList(mRegistrationFB,request);
    	return mapping.findForward("NEW");	
	}
	
    public ActionForward GETORGDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MbNewRegistrationFB _fb = (MbNewRegistrationFB) form;
    	MbNewRegistrationUTL.getOrganizationDetail(_fb,request);
    	return mapping.findForward("NEW");	
	}
    
	/**
	 * saves New Registration Details
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls the action "NEW"
	 * @throws Exception 
	 */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		MbNewRegistrationFB fb = (MbNewRegistrationFB) form;
	   // System.out.println("is refered in action..............................."+fb.getIsReferred());
		MbNewRegistrationUTL.saveNewPatientRegistration(request, fb);		
		fb.reset(mapping, request);
	//	RegDskNewRegistrationUTIL.setDeptOptions(request, fb);	
		return this.NEW(mapping,form,request,response);		
	}	

 }
