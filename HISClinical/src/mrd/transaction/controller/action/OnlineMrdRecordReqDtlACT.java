package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.OnlineMrdRecordReqDtlFB;
import mrd.transaction.controller.utl.OnlineMrdRecordReqDtlUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OnlineMrdRecordReqDtlACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		WebUTIL.refreshTransState(request);
		OnlineMrdRecordReqDtlUTL.getEssentialForOnlineReq(fb, request);
		fb.reset(mapping, request);
		fb.setIsRecordRequested(MrdConfig.NO);
		fb.setIsReqOnlineOffline(MrdConfig.RECORD_REQUEST_ONLINE);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETMRD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.getMrdBasedOnRecordType(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward POPULATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.populateSelectedRecord(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.removeSelectedRecord(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.saveOnlineRequestDetail(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		request.getSession().removeAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
		fb.setIsRecordRequested(MrdConfig.NO);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREQUESTSTATUS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.getPendingRecordRequestStatus(fb,request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward DELREJREC(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.deleteRejectedRecordDetail(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward EXTEND(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.getPendingRecordRequestStatus(fb,request);
		return mapping.findForward("EXTEND");
	}
	public ActionForward SAVE_EXTEND_DAYS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OnlineMrdRecordReqDtlFB fb=(OnlineMrdRecordReqDtlFB)form;
		OnlineMrdRecordReqDtlUTL.saveExtendDays(fb,request);
		return this.NEW(mapping, form, request, response);
	}
}
