package medicalboard.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.transactions.controller.fb.MedicalExamInitiationFB;
import medicalboard.transactions.controller.utl.MedicalExamInitiationUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class MedicalExamInitiationACT extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		generateToken(request);
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
	//	Status status=new Status();
    	MedicalExamInitiationUTL.getCertificateTypeList(fb,request);
 //   	status.add(Status.NEW);
  //  	WebUTIL.setStatus(request, status);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETCANDIDATELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.getCandidateList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREFERMAPPINGIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.getReferMappingList(fb,request);
		//return mapping.findForward("POPUP");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETINVESTIGATIONMAPPINGIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.getMBInvestigationEssential(fb,request);
		//return mapping.findForward("POPUP");
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCANDIDATEREFERDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.getMBCandidateRefDetail(fb,request);
		//return mapping.findForward("POPUP");
		return mapping.findForward("REFERDETAIL");
	}
	
	public ActionForward GETCANDIDATEINVDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.getMBCandidateInvDetail(fb,request);
		//return mapping.findForward("POPUP");
		return mapping.findForward("INVDETAIL");
	}
	
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		//MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		Status status=new Status();
		status.add(Status.LIST);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("POPUP");
		
	}
	
	
	public ActionForward INVMAPPING(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		//MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		Status status=new Status();
		status.add(Status.LIST);
		WebUTIL.setStatus(request, status);
		return mapping.findForward("INVMAPPING");
		
	}
	
	
	public ActionForward SAVEREFERDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		validateToken(request,response);
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.saveCandidateReferDetail(fb, request);
		return this.GETCANDIDATELIST(mapping, form, request, response);
	}
	
	public ActionForward SAVEINVESTIGATIONDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		validateToken(request,response);
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.raiseCandidateInvestigation(fb, request);
		return this.GETCANDIDATELIST(mapping, form, request, response);
	}
	
public ActionForward SHOWEXTERNAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
//		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		
		//return mapping.findForward("POPUP");
		return mapping.findForward("EXTREFER");
	}
	
	
	public ActionForward SAVEEXTREFER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		validateToken(request,response);
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.saveExternalRefer(fb, request);
		return this.GETCANDIDATELIST(mapping, form, request, response);
	}
	
	public ActionForward GETEXTREFERDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		MedicalExamInitiationUTL.getMBCandidateExtRefDetail(fb,request);
		//return mapping.findForward("POPUP");
		return mapping.findForward("EXTREFERDETAIL");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalExamInitiationFB fb = (MedicalExamInitiationFB) form;
		Status objStatus= new Status();
		objStatus.add(Status.LIST);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
	

 }
