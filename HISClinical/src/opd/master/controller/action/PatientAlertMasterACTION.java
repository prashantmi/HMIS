/**
 * 
 */
package opd.master.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.master.controller.fb.PatientAlertMasterFB;
import opd.master.controller.util.PatientAlertMasterUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author ashas
 *
 */
public class PatientAlertMasterACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		return this.ADD(mapping, form, request, response);
	}
	public ActionForward ADD(ActionMapping mapping,	ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		generateToken(request);
		PatientAlertMasterFB fb=(PatientAlertMasterFB)form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		PatientAlertMasterUTIL.getPatientAlertEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	public ActionForward SAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		PatientAlertMasterFB fb=(PatientAlertMasterFB)form;
		boolean flag=PatientAlertMasterUTIL.savePatientAlert(fb, request);
		if(flag)
			fb.reset(mapping, request);
		fb.setHmode("ADD");
		return mapping.findForward("NEW");
	}
	public ActionForward MODIFY(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		PatientAlertMasterFB fb=(PatientAlertMasterFB)form;
		WebUTIL.refreshTransState(request);
		PatientAlertMasterUTIL.fetchPatientAlertModify(fb, request);
		return mapping.findForward("NEW");
	}
	public ActionForward MODIFYSAVE(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		validateToken(request,response);
		PatientAlertMasterFB fb=(PatientAlertMasterFB)form;
		boolean flag=PatientAlertMasterUTIL.modifySave(fb, request);
		if(!flag){
			fb.setHmode("MODIFY");
			return mapping.findForward("NEW");
		}	
		else
			return mapping.findForward("LIST");
	}
	public ActionForward VIEW(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		PatientAlertMasterFB fb=(PatientAlertMasterFB)form;
		PatientAlertMasterUTIL.fetchPatientAlertModify(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception {
		
		return mapping.findForward("LIST");
	}
}
