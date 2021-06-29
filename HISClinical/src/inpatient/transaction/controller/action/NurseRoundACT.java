package inpatient.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.NurseRoundFB;
import inpatient.transaction.controller.utl.NurseRoundUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class NurseRoundACT extends CSRFGardTokenAction {
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		NurseRoundFB fb=(NurseRoundFB)form;
		fb.reset(mapping,request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		NurseRoundFB fb=(NurseRoundFB)form;
		NurseRoundUTL.saveNurseProgNotes(fb,request);
		fb.setProgressNote("");
		return mapping.findForward("NEW");
	}
	
	public ActionForward PREVIOUS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		NurseRoundFB fb=(NurseRoundFB)form;
		NurseRoundUTL.getPreviousProgressNotes(fb,request);
		return mapping.findForward("PREVIOUS");
	}
	
	public ActionForward ADDNOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		NurseRoundFB fb=(NurseRoundFB)form;
		NurseRoundUTL.getProgressNotesToAdd(fb,request);
		return mapping.findForward("ADDNOTES");
	}
	
	public ActionForward SHOWNOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("ADDNOTES");
	}
	
	public ActionForward POPUPPAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		NurseRoundFB fb=(NurseRoundFB)form;
		//ConsentRequestUTIL.setData(fb, request);
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		fb.reset(mapping, request);
		return mapping.findForward("PREVIOUS");
	}
}
