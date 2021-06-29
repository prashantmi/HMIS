package mrd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.PatientFamilyDoctorDtlFB;
import mrd.transaction.controller.utl.PatientFamilyDoctorDtlUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class PatientFamilyDoctorDtlACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		fb.setPatCrNo("");
		fb.setSlNo("");
		PatientFamilyDoctorDtlUTL.setSysdate(request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		PatientFamilyDoctorDtlUTL.getExistingFamilyDoctorRecord(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADD(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		PatientFamilyDoctorDtlUTL.getPhysicianType(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		PatientFamilyDoctorDtlUTL.savePatientFamilyDoctorDetail(fb,request);
	
		return this.GETPATDTL(mapping, form, request, response);
	}	
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		PatientFamilyDoctorDtlUTL.getPatientFamilyDocDetail(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward VIEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		PatientFamilyDoctorDtlUTL.getPatientFamilyDocDetail(fb,request);
	
		return mapping.findForward("NEW");
	}
	
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		PatientFamilyDoctorDtlUTL.modifyPatientFamilyDocDetail(fb,request);
		
		return this.GETPATDTL(mapping, form, request, response);
	}
	
	public ActionForward DELETE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientFamilyDoctorDtlFB fb=(PatientFamilyDoctorDtlFB)form; 
		PatientFamilyDoctorDtlUTL.revokePatientFamilyDocDetail(fb,request);
		
		return this.GETPATDTL(mapping, form, request, response);
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
