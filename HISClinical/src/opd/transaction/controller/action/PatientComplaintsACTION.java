package opd.transaction.controller.action;


/*
 * @author: CDAC Noida
 * @Developer: Pawan Kumar B N
 * created on: 20-Mar-2013
 */
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.PatientComplaintsFB;
import opd.transaction.controller.util.PatientComplaintsUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PatientComplaintsACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.GETCRNO(mapping, form, request, response);
	}
	
	// Call Directly
	public ActionForward GETCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		PatientComplaintsFB fb=(PatientComplaintsFB)form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
		fb.setTestConductedFrom(OpdConfig.TEST_CONDUCTED_IN_HOUSE);
		Status objStatus = new Status();
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.setPatCrNo("");
		ControllerUTIL.setSysdate(request);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		
		fb.setHmode("GETCRNO");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/** Getting parameter list
	 * This mode is used when the process is run by entering the CR No. 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientComplaintsFB fb=(PatientComplaintsFB)form;
		PatientComplaintsUTIL.getParameterForExtInv(fb,request);
		fb.setHmode("GETPATDTL");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/** Getting parameter list
	 * This mode is used when the process is run from DESK 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientComplaintsFB fb=(PatientComplaintsFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		
		fb.setTestConductedFrom(OpdConfig.TEST_CONDUCTED_IN_HOUSE);
		PatientComplaintsUTIL.setSysdate(request);
		PatientComplaintsUTIL.getParameterForExtInv(fb,request);
		fb.setHmode("NEW");
		fb.setTempMode(fb.getHmode());
		fb.setIsDirectCall("DESK");
		return mapping.findForward("NEW");
		
	}
	
	/** Adding new row 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward ADDROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientComplaintsFB fb=(PatientComplaintsFB)form;
		PatientComplaintsUTIL.addRow(fb,request);
		fb.reset(mapping, request);
		return mapping.findForward("NEW");
	}
	
	/** Deleting the row from multirow
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward DELETEROW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		PatientComplaintsFB fb=(PatientComplaintsFB)form;
		PatientComplaintsUTIL.deleteRow(fb,request);
		return mapping.findForward("NEW");
	}
	
	/** Saving the patient External Examination
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward SAVE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		PatientComplaintsFB fb=(PatientComplaintsFB)form;
		PatientComplaintsUTIL.saveExtInvestigationDtl(fb,request);
		//return mapping.findForward("NEW");
		if(fb.getIsDirectCall().equals("DESK"))
			return this.NEW(mapping, form, request, response);
		else
			return this.GETCRNO(mapping, form, request, response);
	}

	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientComplaintsFB fb=(PatientComplaintsFB)form;
		if(fb.getTempMode().equals("GETPATDTL"))
			return this.GETCRNO(mapping, form, request, response);
		else
			return this.FINALCANCEL(mapping, form, request, response);
	}
	
	public ActionForward FINALCANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
