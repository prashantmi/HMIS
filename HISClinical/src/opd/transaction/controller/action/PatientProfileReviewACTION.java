package opd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.PatientProfileReviewFB;
import opd.transaction.controller.util.PatientProfileReviewUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class PatientProfileReviewACTION extends CSRFGardTokenAction
{

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		PatientProfileReviewFB fb = (PatientProfileReviewFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		PatientProfileReviewUTIL.getAllAdmittedPatientList(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PatientProfileReviewFB fb=(PatientProfileReviewFB)form;		
		PatientProfileReviewUTIL.getPreviousProfileDetails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETREVIEWDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PatientProfileReviewFB fb=(PatientProfileReviewFB)form;		
		PatientProfileReviewUTIL.getReviewDetails(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward FETCHPROFILEDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PatientProfileReviewFB fb=(PatientProfileReviewFB)form;		
		PatientProfileReviewUTIL.fetchProfileReviewDetails(fb, request);
		return mapping.findForward("PROFILEDETAIL");
	}
	
	//View Profile
	public ActionForward VIEWPRINTPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		PatientProfileReviewFB fb = (PatientProfileReviewFB) form;

		String winPath = Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS;
		String linuxPath = Config.PATIENT_PROFILE_STORAGE_PATH_LINUX;
		String fileName = fb.getProfileId() + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
		
		HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, winPath, linuxPath);
		fileUtil.readFile();
		fb.setProfileHTML(fileUtil.getFileContentInString());
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getProfileHTML());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());

		return mapping.findForward("VIEWPRINT");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		PatientProfileReviewFB fb=(PatientProfileReviewFB)form;		
		PatientProfileReviewUTIL.savePatientProfileReviewDetail(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCEL");
	}
}
