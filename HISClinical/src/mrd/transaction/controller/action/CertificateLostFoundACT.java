package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CertificateLostFoundFB;
import mrd.transaction.controller.utl.CertificateLostFoundUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateLostFoundACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		CertificateLostFoundFB fb=(CertificateLostFoundFB)form;
		fb.reset(mapping, request);
		CertificateLostFoundUTL.getEssentialForLostFoundDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CertificateLostFoundFB fb=(CertificateLostFoundFB)form; 
		CertificateLostFoundUTL.getShelfBasedOnRack(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CertificateLostFoundFB fb=(CertificateLostFoundFB)form;
		CertificateLostFoundUTL.saveFoundDetailNArchivedInMrd(fb,request);
		return this.NEW(mapping, form, request, response);
	}
}
