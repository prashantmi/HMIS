package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.CrNoMergeDtlFB;
import mrd.transaction.controller.utl.CrNoMergeDtlUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CrNoMergeDtlACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		
		WebUTIL.refreshTransState(request);
		fb.setPatCrNo("");
		CrNoMergeDtlUTL.setSysdate(request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		CrNoMergeDtlUTL.getMergedCrNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		CrNoMergeDtlUTL.getNotUsedCrNo(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		CrNoMergeDtlUTL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		CrNoMergeDtlUTL.saveNotUsedCrNo(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		fb.reset(mapping, request);
		request.getSession().removeAttribute(MrdConfig.ARR_SEARCH_PATIENT);
		Status objStatus=new Status();
	    objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);	   
		return mapping.findForward("POPUP");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		CrNoMergeDtlUTL.searchPatient(fb,request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward REVOKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		
		CrNoMergeDtlFB fb=(CrNoMergeDtlFB) form;
		CrNoMergeDtlUTL.revokeMergedCRNo(fb,request);
		return this.GETPATDTL(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
