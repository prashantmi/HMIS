package medicalboard.transactions.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.MedicalBoardConfig;
import medicalboard.transactions.controller.fb.PostMedicalEntryFB;
import medicalboard.transactions.controller.utl.PostMedicalEntryUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class PostMedicalEntryACT extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}

	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		PostMedicalEntryFB fb = (PostMedicalEntryFB) form;
		fb.reset(mapping,request);	
		WebUTIL.refreshTransState(request);
		fb.setOnlineOfflineFlag(MedicalBoardConfig.POST_ENTRY_FLAG);
    	PostMedicalEntryUTL.getMBPostEntryEssential(fb,request);
	 	return mapping.findForward("NEW");
	}
	
	public ActionForward GETBOARDLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PostMedicalEntryFB fb = (PostMedicalEntryFB) form;
		PostMedicalEntryUTL.getBoardList(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward GETCANDIDATELIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PostMedicalEntryFB fb = (PostMedicalEntryFB) form;
		PostMedicalEntryUTL.getCandidateList(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCANDIDATEMEDICALDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		PostMedicalEntryFB fb = (PostMedicalEntryFB) form;
		PostMedicalEntryUTL.getCandidateDetailForPostEntry(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		validateToken(request,response);
		PostMedicalEntryFB fb = (PostMedicalEntryFB) form;
		PostMedicalEntryUTL.savePostEntryDetail(fb, request);
		return this.GETCANDIDATELIST(mapping, form, request, response);
	}
	
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PostMedicalEntryFB fb = (PostMedicalEntryFB) form;
		Status objStatus= new Status();
		objStatus.add(Status.LIST);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
	

 }
