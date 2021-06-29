package medicalboard.transactions.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class WebCamImageCaptureACT  extends DispatchAction
{
	/**
	 * the default action called 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("INDIDE UNSPECIFIED WebCamImageCaptureACT");
		return this.NEW(mapping,form,request,response);		
	}	
	
    public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
    {		
	    System.out.println("inside new of upload WebCamImageCaptureACT");
	    Status objStatus=new Status();
	    objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);	    
	 	return mapping.findForward("NEW");
	}
}
