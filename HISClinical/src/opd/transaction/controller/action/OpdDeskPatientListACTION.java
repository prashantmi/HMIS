package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.OpdConfig;
import opd.transaction.controller.fb.OpdPatientDeskFB;
import opd.transaction.controller.util.OpdPatientDeskUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class OpdDeskPatientListACTION extends CSRFGardTokenAction
{

	/**
	 * the default action called when a page is loaded for the first time
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return calls action "NEW"
	 */
	// *
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	/**
	 * action mainly called at the initial loading of a page or when a form is reset refreshes Transaction State sets all
	 * OpdPatient essentials sets PatientList
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		// WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		System.out.println("Doctor Desk-> OPD Patient List : OpdDeskPatientListACTION");
		opdPatientDeskFB.setIsCasualty(OpdConfig.NO);
		OpdPatientDeskUTIL.getOpdDeskEssentials(opdPatientDeskFB, request);
		//System.out.println("All Patient List Today : OpdDeskPatientListACTION");
		//OpdPatientDeskUTIL.getTodayAllPatientList(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}
		
	public ActionForward GETROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("OpdDeskPatientListACTION.GETROOM()");
		OpdPatientDeskFB fb = (OpdPatientDeskFB) form;
		String departmentUnitCode = fb.getDepartmentUnitCode();
		OpdPatientDeskUTIL.getRoomByUnitCode(fb, request, departmentUnitCode);
		return mapping.findForward("NEW");
	
	}

	/**
	 * Make Patient List Order By QUEUENO
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward QUEUENO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientDeskUTIL.getOrderByQueueNo(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * Make Patient List Order By CRNO
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward CRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientDeskUTIL.getOrderByCrNo(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * Make Patient List Order By NAME
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward NAME(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientDeskUTIL.getOrderByName(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * Make Patient List Order By PATIENTCATEGORY
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	// *
	public ActionForward PATIENTCATEGORY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientDeskUTIL.getOrderByPatientCategory(opdPatientDeskFB, request);
		return mapping.findForward("NEW");
	}

	/**
	 * close the episode OF the patient.
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */

	public ActionForward SAVEEPISODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request,response);
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) form;
		OpdPatientDeskUTIL.saveOpdPatientEpisode(opdPatientDeskFB, request);
		return this.NEW(mapping, form, request, response);
	}

	
	public ActionForward AJX_G_PATIENTLIST(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		OpdPatientDeskFB opdPatientDeskFB = (OpdPatientDeskFB) objForm_p;
		System.out.println("OpdDeskPatientListACTION.AJX_G_PATIENTLIST()");
		System.out.println("departmentUnitCode  :"+opdPatientDeskFB.getDepartmentUnitCode());
		System.out.println("visitType  :"+opdPatientDeskFB.getVisitType());
		System.out.println("roomcode  :"+opdPatientDeskFB.getRoomCode());
		System.out.println("CRNo  :"+opdPatientDeskFB.getPatCrNo());
		
		OpdPatientDeskUTIL.AJX_G_PATIENTLIST(opdPatientDeskFB, objRequest_p, objResponse_p, objMapping_p);
		return null;	
	}

	public ActionForward AJX_G_ROOM(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		System.out.println("DeskHeaderACTION.AJX_G_ROOM()");
		String departmentUnitCode = objRequest_p.getParameter("unit");
		System.out.println("unitCodeWithDiagCodeType :"+departmentUnitCode);
		OpdPatientDeskFB objFB = (OpdPatientDeskFB) objForm_p;
		StringBuffer strBuff = OpdPatientDeskUTIL.getRoomByUnitCode(objFB, objRequest_p, departmentUnitCode);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}

}
