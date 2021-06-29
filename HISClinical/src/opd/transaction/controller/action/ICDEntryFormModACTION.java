package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.ICDEntryFormFB;
import opd.transaction.controller.util.ICDEntryFormUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ICDEntryFormModACTION extends CSRFGardTokenAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return this.MOD(mapping, form, request, response);
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		ICDEntryFormFB fb = (ICDEntryFormFB) form;
		fb.reset(mapping, request);
		//ICDEntryFormUTIL.setEssentials(fb, request);
		ICDEntryFormUTIL.setOPDPatientListForMod(fb, request);
		return mapping.findForward("MOD");
	}
	
	public ActionForward MOD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		ICDEntryFormFB fb = (ICDEntryFormFB) form;
		//WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		//ICDEntryFormUTIL.setEssentials(fb, request);
		ICDEntryFormUTIL.setOPDPatientListForMod(fb, request);
		return mapping.findForward("MOD");
	}

	public ActionForward SEARCH(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ICDEntryFormFB fb = (ICDEntryFormFB) form;
		ICDEntryFormUTIL.getOPDPatientList(fb, request);
		return mapping.findForward("MOD");
	}
	
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ICDEntryFormFB fb = (ICDEntryFormFB) form;
		ICDEntryFormUTIL.setPaginationData(fb, request);
		fb.setHmode("");
		return mapping.findForward("MOD");
	}

	public ActionForward CRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ICDEntryFormFB fb = (ICDEntryFormFB) form;
		ICDEntryFormUTIL.getOrderBy(Config.OPD_PATIENT_OREDRE_BY_CR_NO, fb, request);
		return mapping.findForward("MOD");
	}

	public ActionForward PATNAME(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ICDEntryFormFB fb = (ICDEntryFormFB) form;
		ICDEntryFormUTIL.getOrderBy(Config.OPD_PATIENT_OREDRE_BY_NAME, fb, request);
		return mapping.findForward("MOD");
	}

	public ActionForward PRICAT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		ICDEntryFormFB fb = (ICDEntryFormFB) form;
		ICDEntryFormUTIL.getOrderBy(Config.OPD_PATIENT_OREDRE_BY_PATIENT_CATEGORY, fb, request);
		return mapping.findForward("MOD");
	}
	
	public ActionForward AJX_G_ICDCODES(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		ICDEntryFormFB objFB = (ICDEntryFormFB) objForm_p;
		StringBuffer strBuff = ICDEntryFormUTIL.getICDCodesList(objFB, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}

	public ActionForward AJX_G_ROOMS(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
			throws HisException, Exception, SQLException
	{
		ICDEntryFormFB objFB = (ICDEntryFormFB) objForm_p;
		StringBuffer strBuff = ICDEntryFormUTIL.getUnitRoomList(objFB, objRequest_p);
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		ICDEntryFormFB fb=(ICDEntryFormFB)form;		
		if(ICDEntryFormUTIL.savePatientICDCodesDtl(fb, request))
		{
			fb.reset(mapping, request);
			ICDEntryFormUTIL.setOPDPatientListForMod(fb, request);
			Status objStatus = new Status(); 
			objStatus.add(Status.NEW,"Record Saved successfully","Record Saved successfully");
			WebUTIL.setStatus(request, objStatus);
		}
		return mapping.findForward("MOD");
	}
}
