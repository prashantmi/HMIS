package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.CertificateAcceptFB;
import mrd.transaction.controller.utl.CertificateAcceptUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateAcceptACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		fb.reset(mapping, request);
		CertificateAcceptUTL.getRecordListToAccept(fb,request);
		fb.setAcceptLostFlag(MrdConfig.CERTIFICATE_ACCEPT_IN_MRD);
		fb.setLostType(MrdConfig.RECORD_LOST_TYPE_COMPLETE);
		return mapping.findForward("NEW");
	}

	public ActionForward GETRACK(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.getRackBasedOnMrd(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETSHELF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.getShelfBasedOnRack(fb,request);
		return mapping.findForward("NEW");
	}
	
	//Added by Dheeraj on 05-Nov-2018
	public ActionForward GETSTAFF(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.getShelfBasedOnRack(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ACCEPT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.saveRecordAccepted(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward ARCHIVAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.saveRecordArchived(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward LOST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.saveRecordLost(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward GETENCLOSURE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.getEnclosureDetail(fb,request);
		return mapping.findForward("ENCLOSURE");
	}
	
	public ActionForward ADDENCLOSURE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CertificateAcceptFB fb=(CertificateAcceptFB)form; 
		CertificateAcceptUTL.addEnclosureDetail(fb,request);
		return mapping.findForward("ENCLOSURE");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	
}
