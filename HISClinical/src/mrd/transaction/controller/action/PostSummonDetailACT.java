package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.PostSummonDetailFB;
import mrd.transaction.controller.utl.PostSummonDtlUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PostSummonDetailACT extends CSRFGardTokenAction 
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		PostSummonDetailFB fb=(PostSummonDetailFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		PostSummonDtlUTL.getEssenForPostSummonDtl(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		//return mapping.findForward("CANCEL");
		return this.NEW(mapping, form, request, response);
	}
	public ActionForward INITCANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward GETSUMMONDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PostSummonDetailFB fb=(PostSummonDetailFB)form;
		PostSummonDtlUTL.getSummonDetail(fb,request);
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.POST_SUMMON_LIST);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PostSummonDetailFB fb=(PostSummonDetailFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.SELECTED_POST_SUMMON_VO);
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PostSummonDetailFB fb = (PostSummonDetailFB) form;
		PostSummonDtlUTL.savePostSummonDetail(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
}
