
/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	MRD
## Process/Database Object Name	:	IPD Nursing Desk
## Purpose						:	Request to get IPD Patient List, Dept List, Unit List, Room List, Ward List
## Date of Creation				: 	18-December-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package inpatient.transaction.controller.action;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import inpatient.InpatientConfig;
import inpatient.transaction.controller.fb.IpdDoctorDeskLoginFB;
import inpatient.transaction.controller.fb.IpdPatDocDeskFB;
import inpatient.transaction.controller.utl.IpdDoctorDeskLoginUTL;
import inpatient.transaction.controller.utl.IpdPatDocDeskUTL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.OpdPatientDeskFB;
import opd.transaction.controller.util.OpdPatientDeskUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class IpdNursingDeskPatientListACT extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	
	/** Getting the List of Admitted patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		IpdPatDocDeskFB fb=(IpdPatDocDeskFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		//IpdPatDocDeskUTL.getAdmittedPatientList(fb,request); //commented by Dheeraj on 28-Dec-2018 for Desk Optimization
		//boolean flag = IpdPatDocDeskUTL.getIpdDeskEssentials(fb, request);
		boolean flag = IpdPatDocDeskUTL.getIpdNursingDeskEssentials(fb, request); //Modified by Vasu on 15.Sept.2018
		return mapping.findForward("NEW");
	}
	
	public ActionForward AJX_G_WARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		System.out.println("IpdNursingDeskPatientListACT.AJX_G_WARD()");
		IpdPatDocDeskFB fb=(IpdPatDocDeskFB)form;
		System.out.println("deptUnitCode is :-"+fb.getDepartmentUnitCode());
		//StringBuffer strBuff = IpdPatDocDeskUTL.getWardOnBasisOfUnitCode(fb,request);
		StringBuffer strBuff = IpdPatDocDeskUTL.getWardOnBasisOfUnitCodeForIPDNursing(fb,request); //Modified by Vasu on 15.Sept.2018
		
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(strBuff.toString());
		return null;	
	}
	
	public ActionForward AJX_G_ROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		IpdPatDocDeskFB fb=(IpdPatDocDeskFB)form;		
		StringBuffer strBuff = IpdPatDocDeskUTL.getRoomOnBasisOfWardCode(fb,request);
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().print(strBuff.toString());
		return null;
	}

	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		Status objStatus = new Status();
		fb.setHmode("");
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADMNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByAdmissionNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward PATNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByPatName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ADMDATE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		IpdPatDocDeskFB fb = (IpdPatDocDeskFB) form;
		IpdPatDocDeskUTL.getOrderByAdmissionDate(fb, request);
		return mapping.findForward("NEW");
	}

	
	public ActionForward AJX_G_PATIENTLIST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		IpdPatDocDeskFB ipdPatDocDeskFB = (IpdPatDocDeskFB) objForm_p;
		System.out.println("IpdNursingDeskPatientListACT.AJX_G_PATIENTLIST()");
		System.out.println("departmentUnitCode  :"+ipdPatDocDeskFB.getDepartmentUnitCode());
		System.out.println("wardCode  :"+ipdPatDocDeskFB.getWardCode());
		System.out.println("roomCode  :"+ipdPatDocDeskFB.getRoomCode());
		System.out.println("searchCriteria  :"+ipdPatDocDeskFB.getSrchCriteria());
		System.out.println("searchValue  :"+ipdPatDocDeskFB.getSrchValue());		
		IpdPatDocDeskUTL.AJX_G_PATIENTLIST(ipdPatDocDeskFB, objRequest_p, objResponse_p, objMapping_p);		
		return null;	
	}
}
