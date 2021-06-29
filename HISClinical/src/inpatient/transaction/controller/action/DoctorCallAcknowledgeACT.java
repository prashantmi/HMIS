package inpatient.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.DoctorCallBookFB;
import inpatient.transaction.controller.utl.DoctorCallAcknowledgeUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class DoctorCallAcknowledgeACT extends CSRFGardTokenAction 

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
		
		DoctorCallAcknowledgeUTL.getCallAckowledgeDetails(fb, request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDETAILS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallAcknowledgeUTL.getSpecificCallDetails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ACKNOWLEDGE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		validateToken(request,response);
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallAcknowledgeUTL.saveDoctorCallAcknowledgeDetails(fb, request);
		fb.reset(mapping,request);
		return this.NEW(mapping, form, request, response);
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
	
	public ActionForward GETALLCALLS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallAcknowledgeUTL.getAllCalls(fb, request);
	//	fb.reset(mapping,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETCALLREMARKS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		DoctorCallBookFB fb= (DoctorCallBookFB)form;
		DoctorCallAcknowledgeUTL.getCallRemarks(fb, request);
	//	fb.reset(mapping,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDNOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		DoctorCallBookFB fb=(DoctorCallBookFB)form;
		DoctorCallAcknowledgeUTL.getCallRemarksNNotes(fb,request);
		return mapping.findForward("ADDNOTES");
	}
	
	public ActionForward SHOWNOTES(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("ADDNOTES");
	}
}
