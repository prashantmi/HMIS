package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.BodyIdentificationDetailFB;
import mortuary.transaction.controller.utl.BodyIdentificationDetailUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class BodyIdentificationDetailACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		BodyIdentificationDetailFB fb=(BodyIdentificationDetailFB)form;
		fb.reset(mapping, request);
		BodyIdentificationDetailUTL.getEssentialForIdentification(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		BodyIdentificationDetailFB fb=(BodyIdentificationDetailFB)form;
		BodyIdentificationDetailUTL.saveBodyIdentificationDetail(fb,request);
		return mapping.findForward("NEW");
	}
}
