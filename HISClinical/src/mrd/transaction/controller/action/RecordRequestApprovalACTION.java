package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.RecordRequestApprovalFB;
import mrd.transaction.controller.utl.RecordRequestApprovalUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class RecordRequestApprovalACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		RecordRequestApprovalFB fb=(RecordRequestApprovalFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		RecordRequestApprovalUTL.getRequestListForApproval(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREQDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		RecordRequestApprovalFB fb=(RecordRequestApprovalFB)form;
		RecordRequestApprovalUTL.getRequestDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETRECORDSTAUS(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		RecordRequestApprovalFB fb=(RecordRequestApprovalFB)form;
		RecordRequestApprovalUTL.getMrdRecordStatus(fb,request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		RecordRequestApprovalFB fb=(RecordRequestApprovalFB)form;
		//RecordRequestApprovalUTL.saveApprovalDetail(fb,request);
		if(RecordRequestApprovalUTL.saveApprovalDetail(fb,request))
		{
			WebUTIL.refreshTransState(request);	
			fb.reset(mapping, request);
			RecordRequestApprovalUTL.getRequestListForApproval(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
}
