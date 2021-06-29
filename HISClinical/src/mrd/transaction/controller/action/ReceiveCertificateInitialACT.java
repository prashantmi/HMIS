package mrd.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.ReceiveCertificateInitialFB;
import mrd.transaction.controller.utl.ReceiveCertificateInitialUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ReceiveCertificateInitialACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ReceiveCertificateInitialFB fb=(ReceiveCertificateInitialFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		fb.setMode("RECORDTYPE");
		boolean flag = ReceiveCertificateInitialUTL.getMrdList(fb,request);
		if(flag)
			return this.RECORDTYPE(mapping, form, request, response);
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward RECORDTYPE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ReceiveCertificateInitialFB fb=(ReceiveCertificateInitialFB)form;
		boolean flag = ReceiveCertificateInitialUTL.getRecordType(fb,request);
		if(flag)
			return this.DESK(mapping, form, request, response);
		else
			return mapping.findForward("NEW");
	}
	
	//ADDED  BY SWATI SAGAR ON DATE:08-MAY-2019
	public ActionForward RECORDTYPEADMNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ReceiveCertificateInitialFB fb=(ReceiveCertificateInitialFB)form;
		// ReceiveCertificateInitialUTL.getRecordType(fb,request);
		
			return this.DESK(mapping, form, request, response);
	
	}
	
	//ADDED  BY SWATI SAGAR ON DATE:13-MAY-2019
		public ActionForward RECORDTYPECRNOWISE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			ReceiveCertificateInitialFB fb=(ReceiveCertificateInitialFB)form;
			// ReceiveCertificateInitialUTL.getRecordType(fb,request);
			
				return this.DESK(mapping, form, request, response);
		
		}
	
	
	public ActionForward DESK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		ReceiveCertificateInitialFB fb=(ReceiveCertificateInitialFB)form;
		ReceiveCertificateInitialUTL.setRecordType(fb,request);
		return mapping.findForward("DESK");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
