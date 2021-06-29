package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.PatientAttendantFB;
import opd.transaction.controller.util.PatientAttendantUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PatientAttendantACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.GETCRNO(mapping, form, request, response);
	}
	
	// Call Directly
	public ActionForward GETCRNO(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		PatientAttendantFB fb=(PatientAttendantFB)form;
		fb.reset(mapping, request);
		fb.setIsDirectCall("DIRECT");
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
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientAttendantFB fb=(PatientAttendantFB)form;
		PatientAttendantUTL.getAllEpisodeOfThePatient(fb,request);
//		PatientAttendantUTL.getPatientAddedAttendant(fb,request);
		fb.setHmode("GETPATDTL");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	/**
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientAttendantFB fb=(PatientAttendantFB)form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.reset(mapping, request);
		PatientAttendantUTL.getPatientAddedAttendant(fb,request);
		fb.setHmode("NEW");
		fb.setTempMode(fb.getHmode());
		fb.setIsDirectCall("DESK");
		return mapping.findForward("NEW");
	}
	

	public ActionForward GETATTDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		PatientAttendantFB fb=(PatientAttendantFB)form;
		PatientAttendantUTL.getPatientAddedAttendant(fb,request);
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PatientAttendantFB fb=(PatientAttendantFB)form;
		if(PatientAttendantUTL.savePatientAttendantDtl(fb,request))
		{
			if(fb.getIsDirectCall().equals("DESK"))
				return this.NEW(mapping, form, request, response);
			else
				return this.GETCRNO(mapping, form, request, response);
		}
		else
			return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		PatientAttendantFB fb=(PatientAttendantFB)form;
		if(fb.getTempMode().equals("GETPATDTL"))
			return this.GETCRNO(mapping, form, request, response);
		if(fb.getTempMode().equals("GETATTDTL"))
			return this.GETPATDTL(mapping, form, request, response);
		else
			return this.FINALCANCEL(mapping, form, request, response);
	}
	
	public ActionForward FINALCANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		return mapping.findForward("CANCEL");
	}

	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		StringBuffer strBuff= new StringBuffer();
		strBuff.append("[{summary:\'Patient Attendant Detail\'}]");
		System.out.println("PatientAttendantACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
}
