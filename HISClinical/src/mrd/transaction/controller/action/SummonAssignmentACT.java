package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.SummonAssigmentFB;
import mrd.transaction.controller.utl.SummonAssignmentDetailUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class SummonAssignmentACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		SummonAssigmentFB fb=(SummonAssigmentFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		SummonAssignmentDetailUTL.getEssenForSummonAssignDtl(fb, request);
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
		SummonAssigmentFB fb=(SummonAssigmentFB)form;
		SummonAssignmentDetailUTL.getSummonDetail(fb,request);
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.ALL_SUMMON_RECEIVED_LIST);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SummonAssigmentFB fb=(SummonAssigmentFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.SELECTED_SUMMON_RECEIVED_VO);
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCHEMP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//SummonAssigmentFB fb=(SummonAssigmentFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(MrdConfig.SEARCH_EMPLOYEE_lIST);
		return mapping.findForward("SEARCHEMP");
	}
	
	public ActionForward GETEMPLOYEE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		SummonAssigmentFB fb=(SummonAssigmentFB)form;
		SummonAssignmentDetailUTL.getEmployeeDetail(fb,request);
		return mapping.findForward("SEARCHEMP");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		
		SummonAssigmentFB fb = (SummonAssigmentFB) form;
		SummonAssignmentDetailUTL.searchEmpDetail(fb,request);
		return mapping.findForward("SEARCHEMP");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		SummonAssigmentFB fb = (SummonAssigmentFB) form;
		SummonAssignmentDetailUTL.saveAssigmentDetail(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping, form, request, response);
	}
	
	
}
