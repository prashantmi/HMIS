package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdPatientBelongingFB;
import opd.transaction.controller.util.OpdPatientBelongingUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;


public class OpdPatientBelongingACTION extends CSRFGardTokenAction {

	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		generateToken(request);
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		fb.reset(mapping,request);
		WebUTIL.refreshTransState(request);
		OpdPatientBelongingUTIL.setSysdate(request);		
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		 
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		OpdPatientBelongingUTIL.setPatientDtlByCrno(fb,request);
		OpdPatientBelongingUTIL.getPatientBelongingEssentials(fb,request);
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADDDETAIL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		OpdPatientBelongingUTIL.addDetailRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward REMOVEDETAIL (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		OpdPatientBelongingUTIL.removeDetailRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		OpdPatientBelongingUTIL.saveBelongingDetails(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		Status objStatus=new Status();
		fb.setSelectedMode(fb.getHmode());
		objStatus.add(Status.NEW);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward MODIFYBELONGING(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		OpdPatientBelongingUTIL.modifyBelonging(fb,request);
		fb.setSelectedMode("MODIFY");
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward HANDOVER(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		fb.setSelectedMode(fb.getHmode());
		return mapping.findForward("MODIFY");
	}
	
	public ActionForward HANDOVERSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		OpdPatientBelongingUTIL.saveHandOverDetails(fb,request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward HANDOVERLIST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		fb.setSelectedMode(fb.getHmode());
		return mapping.findForward("MODIFY");
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception{
		validateToken(request,response);
		OpdPatientBelongingFB fb = (OpdPatientBelongingFB) form;
		OpdPatientBelongingUTIL.saveModifyDetails(fb,request);
		fb.reset(mapping, request);
		fb.setSelectedMode("MODIFY");
		return mapping.findForward("MODIFY");
	}
	
}
