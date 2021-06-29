package dutyroster.transaction.controller.action;

import hisglobal.presentation.WebUTIL;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import dutyroster.transaction.controller.fb.NurseRoleDetailFB;
import dutyroster.transaction.controller.utl.NurseRoleDetailUTL;

public class NurseRoleDetailACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		NurseRoleDetailFB fb=(NurseRoleDetailFB)form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		NurseRoleDetailUTL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		NurseRoleDetailFB fb=(NurseRoleDetailFB)form;
		NurseRoleDetailUTL.getRosterDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	
}
