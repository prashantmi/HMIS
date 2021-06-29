package mortuary.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mortuary.transaction.controller.fb.DeceasedInjuriesFB;
import mortuary.transaction.controller.utl.DeceasedInjuriesUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DeceasedInjuriesACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		DeceasedInjuriesFB fb=(DeceasedInjuriesFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		DeceasedInjuriesUTL.getEssentialForInjuries(fb,request); 
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		DeceasedInjuriesFB fb=(DeceasedInjuriesFB)form;
		DeceasedInjuriesUTL.saveInjuriesDetail(fb,request); 
		return mapping.findForward("NEW");
	}
}
