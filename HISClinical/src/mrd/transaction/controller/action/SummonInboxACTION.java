package mrd.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.SummonInboxFB;
import mrd.transaction.controller.utl.SummonInboxUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SummonInboxACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		SummonInboxFB fb=(SummonInboxFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		SummonInboxUTL.getEssentialForSummonDtl(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward GETSUMMONDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		SummonInboxFB fb=(SummonInboxFB)form;
		SummonInboxUTL.getSummonDetail(fb, request);
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.ALL_ASSIGNED_SUMMON_DETAIL_LIST);
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
}
