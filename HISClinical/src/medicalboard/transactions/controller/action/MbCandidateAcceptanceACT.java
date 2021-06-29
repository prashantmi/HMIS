package medicalboard.transactions.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.transactions.controller.fb.MbCandidateAcceptanceFB;
import medicalboard.transactions.controller.utl.MbCandidateAcceptanceUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class MbCandidateAcceptanceACT extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		return this.NEW(mapping,form,request,response);		
	}

	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		generateToken(request);
		MbCandidateAcceptanceFB fb = (MbCandidateAcceptanceFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
    	MbCandidateAcceptanceUTL.getCertificateTypeList(fb,request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETCANDIDATELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		MbCandidateAcceptanceFB fb = (MbCandidateAcceptanceFB) form;
		MbCandidateAcceptanceUTL.getCandidateList(fb,request,fb.getStrPreviousDate());
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCHECKLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MbCandidateAcceptanceFB fb = (MbCandidateAcceptanceFB) form;
		MbCandidateAcceptanceUTL.getChecklistDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		MbCandidateAcceptanceFB fb = (MbCandidateAcceptanceFB) form;
		MbCandidateAcceptanceUTL.saveCandidateAcceptance(fb, request);
		return this.NEW(mapping, form, request, response);
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward GETPREVCANDIDATELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MbCandidateAcceptanceFB fb = (MbCandidateAcceptanceFB) form;
		MbCandidateAcceptanceUTL.getPrevCandidateList(fb,request);
		return mapping.findForward("NEW");
	}

 }
