package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.MrdRecordIssueFB;
import mrd.transaction.controller.utl.MrdRecordIssueUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MrdRecordIssueACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);

		MrdRecordIssueFB fb=(MrdRecordIssueFB)form;
	//	WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		MrdRecordIssueUTL.getRequestListForIssue(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREQDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MrdRecordIssueFB fb=(MrdRecordIssueFB)form;
		MrdRecordIssueUTL.getRequestDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MrdRecordIssueFB fb=(MrdRecordIssueFB)form;
		if(MrdRecordIssueUTL.saveIssueDetail(fb,request))
		{
			WebUTIL.refreshTransState(request);	
			fb.reset(mapping, request);
			MrdRecordIssueUTL.getRequestListForIssue(fb,request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW,"","Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus= new Status();
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);		
		return mapping.findForward("NEW");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
		return mapping.findForward("CANCEL");
		
	}
	
}
