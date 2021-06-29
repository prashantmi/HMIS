package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.RecordLostInMrdFB;
import mrd.transaction.controller.utl.RecordLostInMrdUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RecordLostInMrdACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		RecordLostInMrdFB fb=(RecordLostInMrdFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setLostType(MrdConfig.RECORD_LOST_TYPE_COMPLETE);
		RecordLostInMrdUTL.getLostRecordReportedByList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		RecordLostInMrdFB fb=(RecordLostInMrdFB)form;
		RecordLostInMrdUTL.openPopupForSearchRecord(fb, request);
		return mapping.findForward("POPUP");
	}	
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		RecordLostInMrdFB fb=(RecordLostInMrdFB)form;
		RecordLostInMrdUTL.searchRecord(fb, request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		RecordLostInMrdFB fb=(RecordLostInMrdFB)form;
		RecordLostInMrdUTL.saveMrdRecordLostDetail(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
