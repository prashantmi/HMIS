package inpatient.transaction.controller.action;

import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.AncTeamDetailFB;
import inpatient.transaction.controller.utl.ANCTeamDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class AncTeamDetailACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		AncTeamDetailFB fb = (AncTeamDetailFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		ControllerUTIL.setSysdate(request);
		ANCTeamDetailUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDDROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//AncTeamDetailFB fb = (AncTeamDetailFB) form;
		
		return mapping.findForward("NEW");
	}
	
	
}
