/*Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:22-Nov-2014 

*/




package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.EstimateCertificateIssuetFB;
import mrd.transaction.controller.utl.EstimateCertificateIssueUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EstimateCertificateIssueACTION extends CSRFGardTokenAction{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		EstimateCertificateIssuetFB fb=(EstimateCertificateIssuetFB)form;
		WebUTIL.refreshTransState(request);	
		//fb.reset(mapping, request);
		EstimateCertificateIssueUTL.getEstimateCertificateReqDtl(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward GETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		EstimateCertificateIssuetFB fb=(EstimateCertificateIssuetFB)form;
	//	WebUTIL.refreshTransState(request);	
	//	fb.reset(mapping, request);
		EstimateCertificateIssueUTL.getEstimateCertificateIssuePatDtl(fb,request);
		return mapping.findForward("GETDTL");
	}
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		EstimateCertificateIssuetFB fb=(EstimateCertificateIssuetFB)form;
		if(EstimateCertificateIssueUTL.saveEstimateCertificateIssueDtl(fb,request))
		{
			
				//return this.GETDTL(mapping, form, request, response);
				return this.NEW(mapping, form, request, response);
			
		}
		else
			//return mapping.findForward("GETDTL");
		    return mapping.findForward("NEW");
	}
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EstimateCertificateIssuetFB fb = (EstimateCertificateIssuetFB) form;
		Status objStatus= new Status();
		
		objStatus.add(Status.RECORDFOUND);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EstimateCertificateIssuetFB fb = (EstimateCertificateIssuetFB) form;
		EstimateCertificateIssueUTL.generateEstimateCertificate(fb,request);
		return mapping.findForward("POPUP");
	}
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		
			return mapping.findForward("CANCEL");
		
	}
	
}
