package inpatient.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.DoctorCallBookFB;
import inpatient.transaction.controller.utl.DoctorCallBookUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DoctorCallBookACT extends CSRFGardTokenAction 

{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		DoctorCallBookFB fb=(DoctorCallBookFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		
		DoctorCallBookUTL.getCallBookDetails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorCallBookFB fb=(DoctorCallBookFB)form;
		DoctorCallBookUTL.getDeptUnitList(fb, request);
		DoctorCallBookUTL.getConsultantDetails(fb, request);
		DoctorCallBookUTL.getPhoneDetails(fb, request);
		DoctorCallBookUTL.getPeonDetails(fb, request); 
		return mapping.findForward("ADD");
	}
	

	public ActionForward GETCONSULTANT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorCallBookFB fb=(DoctorCallBookFB)form;
		DoctorCallBookUTL.getConsultantDetails(fb, request);
		DoctorCallBookUTL.getPhoneDetails(fb, request);
		DoctorCallBookUTL.getPeonDetails(fb, request); 
		return mapping.findForward("ADD");
	}
	
	public ActionForward GETPHONE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorCallBookFB fb=(DoctorCallBookFB)form;
		DoctorCallBookUTL.getPhoneDetails(fb, request); 
		 
		return mapping.findForward("ADD");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorCallBookFB fb=(DoctorCallBookFB)form;
		DoctorCallBookUTL.getCallDetails(fb, request); 
		DoctorCallBookUTL.getPeonDetails(fb, request);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		validateToken(request,response);
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallBookUTL.saveDoctorCallDetails(fb, request);
		fb.reset(mapping,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		validateToken(request,response);
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallBookUTL.getMODPhoneDetails(fb, request);
		DoctorCallBookUTL.getPeonDetails(fb, request);
		DoctorCallBookUTL.ModifyDoctorCallDetails(fb, request);
		fb.reset(mapping,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward GETCALLREMARKS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallBookUTL.getCallRemarks(fb, request);
	//	fb.reset(mapping,request);
		return mapping.findForward("NEW");
	}

	public ActionForward GETALLCALLS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallBookUTL.getAllCalls(fb, request);
	//	fb.reset(mapping,request);
		return mapping.findForward("NEW");
	}

	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorCallBookFB fb = (DoctorCallBookFB) form;
		Status objStatus= new Status();
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("NEW");
		return mapping.findForward("NEW");
	}
	
	public ActionForward DOCTORCALLBOOKLOGREPORT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
	//	DoctorCallBookFB fb= (DoctorCallBookFB)form;
		
		return mapping.findForward("DOCTORCALLBOOKLOGREPORT");
	}
	
	public ActionForward ADDNOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DoctorCallBookFB fb=(DoctorCallBookFB)form;
		DoctorCallBookUTL.getCallRemarksNNotes(fb,request);
		return mapping.findForward("ADDNOTES");
	}
	
	public ActionForward SHOWNOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("ADDNOTES");
	}

}
