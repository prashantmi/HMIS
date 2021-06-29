package registration.transactions.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import registration.transactions.controller.actionsupport.CRNoSUP;
import registration.transactions.controller.fb.PatientDetailFB;
import registration.transactions.controller.util.PatDetailUTIL;

import org.apache.struts.action.*;


public class PatDetailACTION extends Action {
	/**
	 * sets Patient Details
	 * @param mapping -object of ActionMapping
	 * @param form - object of  ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "SAME"
	 */
	public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		{
			//System.out.println("GETPATDTL");		
			PatientDetailFB fb = (PatientDetailFB) form;
			
			PatDetailUTIL.getPatientDtlByCrno(fb,request);				
		 	//System.out.println("before forwarding"); 
			return mapping.findForward("SAME");			
		}
	}	
}
