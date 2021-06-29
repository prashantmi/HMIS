/**
 * 
 */
package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.HospitalDiseaseMasterFB;
import opd.master.controller.util.HospitalDiseaseMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author ashas
 *
 */
public class HospitalDiseaseMasterNewACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{

		return this.ADD(mapping, form, request, response);
	}
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		HospitalDiseaseMasterUTIL.getHospitalDiseaseEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);

		System.out.println("Inside Hospital Disease Save");
		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		boolean flag=HospitalDiseaseMasterUTIL.saveHospitalDisease(fb, request);
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		WebUTIL.refreshTransState(request);
		HospitalDiseaseMasterUTIL.fetchHospitalDiseaseModify(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);

		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		boolean flag=HospitalDiseaseMasterUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}	
		else
			return mapping.findForward("LIST");
	}
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		HospitalDiseaseMasterFB fb=(HospitalDiseaseMasterFB)form;
		HospitalDiseaseMasterUTIL.fetchHospitalDiseaseModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("LIST");
	}
	

}
