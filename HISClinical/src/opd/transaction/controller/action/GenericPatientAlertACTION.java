package opd.transaction.controller.action;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.utl.InpatientDetailUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.GenericPatientAlertFB;
import opd.transaction.controller.util.GenericPatientAlertUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;



public class GenericPatientAlertACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return this.NEW(mapping,form,request,response);		
	}
	
	/** Action is called at the initial loading of a Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		generateToken(request);
		GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		fb.reset(mapping,request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		InpatientDetailUTL.getInpatientDetailByCrNo(fb, request);
		GenericPatientAlertUTIL.getEssentialForPatAlerts(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Adding the Value of first Row in the VO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		GenericPatientAlertUTIL.addRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}	
	
	/** Deleting the Row of VO
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		GenericPatientAlertUTIL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}	
	
	/** Saving the Patient Alerts
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		GenericPatientAlertUTIL.savePatientAlerts(fb,request);
		return this.NEW(mapping,form,request,response);	
		//return mapping.findForward("NEW");
	}
	
	/** Revoking the Alerts of the Patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward REVOKE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		GenericPatientAlertUTIL.revokeAlerts(fb,request);
		//return mapping.findForward("NEW");
		return this.NEW(mapping,form,request,response);	
	}
	
	/** Showing All Alerts of the Patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ALLALERT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		GenericPatientAlertUTIL.getAllPatientAlert(fb,request);
		return mapping.findForward("ALLALERT");
	}
	
	public ActionForward DAYCAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		GenericPatientAlertUTIL.calculateDay(fb,request);
		return mapping.findForward("DAYCAL");
	}
	
	public ActionForward SHOWDAYS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//GenericPatientAlertFB fb=(GenericPatientAlertFB)form;
		Status objStatus=new Status();
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("DAYCAL");
	}
	
	
	
}
