package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.OfflineMrdRecordReqDtlFB;
import mrd.transaction.controller.utl.OfflineMrdRecordReqDtlUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class OfflineMrdRecordReqDtlACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		OfflineMrdRecordReqDtlUTL.getEssentialForOfflineReq(fb, request);
		//fb.setRecordType(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
		fb.setIsRecordRequested(MrdConfig.NO);
		fb.setIsReqOnlineOffline(MrdConfig.RECORD_REQUEST_OFFLINE);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETMRD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		OfflineMrdRecordReqDtlUTL.getMrdBasedOnRecordType(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		OfflineMrdRecordReqDtlUTL.openPopupForSearchRecord(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		OfflineMrdRecordReqDtlUTL.searchRecord(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward POPULATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		OfflineMrdRecordReqDtlUTL.populateSelectedRecord(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		OfflineMrdRecordReqDtlUTL.removeSelectedRecord(fb, request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		OfflineMrdRecordReqDtlUTL.saveForOfflineRequestDetail(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CLEAR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		OfflineMrdRecordReqDtlFB fb=(OfflineMrdRecordReqDtlFB)form;
		OfflineMrdRecordReqDtlUTL.getEssentialForOfflineReq(fb, request); //Added by Vasu on 11.10.2018
		request.getSession().removeAttribute(MrdConfig.ARR_TO_BE_REQUESTED_RECORD_VO);
		fb.setIsRecordRequested(MrdConfig.NO);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
