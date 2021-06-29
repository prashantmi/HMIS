package inpatient.transaction.controller.action;

import inpatient.transaction.controller.fb.ValidateUserFB;
import inpatient.transaction.controller.utl.ValidateUserUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ValidateUserACT extends DispatchAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ValidateUserFB _fb=(ValidateUserFB)form;
		_fb.reset(mapping, request);
		ValidateUserUTL.getUserList(request, _fb);
		return mapping.findForward("NEW");
	}

	public ActionForward VALIDATEUSER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ValidateUserFB _fb=(ValidateUserFB)form;
		ValidateUserUTL.valideteUser(request, _fb);
		return mapping.findForward("NEW");
	}
}
