package medicalboard.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.transactions.controller.fb.CertificateVerificationFB;
import medicalboard.transactions.controller.utl.CertificateVerificationUTL;




import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CertificateVerificationACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}

	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		CertificateVerificationFB _fb = (CertificateVerificationFB) form;
		_fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		CertificateVerificationUTL.getMBCertVerificationEssential(_fb, request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETCANDIDATELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		CertificateVerificationFB fb = (CertificateVerificationFB) form;
		CertificateVerificationUTL.getCandidateList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCANDIDATEMEDICALDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		CertificateVerificationFB fb = (CertificateVerificationFB) form;
		CertificateVerificationUTL.getCandidateDetailForCertVerification(fb,request);
		return mapping.findForward("NEW");
	}
	
public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
	validateToken(request,response);
		CertificateVerificationFB fb = (CertificateVerificationFB) form;
		if(CertificateVerificationUTL.saveCertVerificationDetail(fb,request))
		{
			CertificateVerificationUTL.getCandidateList(fb,request);
			Status objStatus=new Status();
			objStatus.add(Status.LIST,"","Record Saved Successfully");
			request.setAttribute(Config.STATUS_OBJECT, objStatus);
		}
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}
	
	
}
