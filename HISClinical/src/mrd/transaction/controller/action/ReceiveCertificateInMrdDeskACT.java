package mrd.transaction.controller.action;

import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.ReceiveCertificateInMrdDeskFB;
import mrd.transaction.controller.utl.ReceiveCertificateInMrdDeskUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ReceiveCertificateInMrdDeskACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.ACCEPT(mapping,form,request,response);
	}
	
	public ActionForward ACCEPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ReceiveCertificateInMrdDeskFB fb=(ReceiveCertificateInMrdDeskFB)form;
		Status objStatus =new Status();
		ReceiveCertificateInMrdDeskUTL.setTabSequence(MrdConfig.CERTIFICATE_RECEIVED_IN_MRD_MODE_ACCEPT, request);
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		fb.setDeskmode("ACCEPT");
		fb.setHmode("NEW");
		return mapping.findForward("ACCEPT");
	}
	
	public ActionForward ARCHIVAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ReceiveCertificateInMrdDeskUTL.setTabSequence(MrdConfig.CERTIFICATE_RECEIVED_IN_MRD_MODE_ARCHIVAL, request);
		return mapping.findForward("ARCHIVAL");
	}
	
	public ActionForward LOSTFOUND(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		ReceiveCertificateInMrdDeskUTL.setTabSequence(MrdConfig.CERTIFICATE_RECEIVED_IN_MRD_MODE_LOSTFOUND, request);
		return mapping.findForward("LOSTFOUND");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
}
