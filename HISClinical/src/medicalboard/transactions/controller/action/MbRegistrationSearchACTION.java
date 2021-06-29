package medicalboard.transactions.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import medicalboard.transactions.controller.fb.MbNewRegistrationFB;
import medicalboard.transactions.controller.utl.MbRegistrationSearchUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MbRegistrationSearchACTION extends DispatchAction 

{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MbNewRegistrationFB fb=(MbNewRegistrationFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		
		//MbRegistrationSearchUTL.getPatientCatAndCertificateType(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		MbNewRegistrationFB fb=(MbNewRegistrationFB)form;
		MbRegistrationSearchUTL.getPatientCatAndCertificateType(fb, request);
		MbRegistrationSearchUTL.getChecklistDetails(fb, request);		
		//MbRegistrationSearchUTL.getConsultantDetails(fb, request); 
		return mapping.findForward("NEW");
	}
	/*

	public ActionForward SHOWENCLOSURE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MbNewRegistrationFB fb=(MbNewRegistrationFB)form;
		 
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MbNewRegistrationFB fb=(MbNewRegistrationFB)form;
		mbRegistrationSearchUTL.getCallDetails(fb, request); 
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		MbNewRegistrationFB fb= (MbNewRegistrationFB)form;
		mbRegistrationSearchUTL.saveDoctorCallDetails(fb, request);
		fb.reset(mapping,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{		
		MbNewRegistrationFB fb= (MbNewRegistrationFB)form;
		mbRegistrationSearchUTL.ModifyDoctorCallDetails(fb, request);
		fb.reset(mapping,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MbNewRegistrationFB fb = (MbNewRegistrationFB) form;
		Status objStatus= new Status();
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}*/
}
