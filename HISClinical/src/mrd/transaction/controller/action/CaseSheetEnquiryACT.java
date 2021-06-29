package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HisFileControlUtil;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mrd.transaction.controller.fb.CaseSheetEnquiryFB;
import mrd.transaction.controller.utl.CaseSheetEnquiryUTL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CaseSheetEnquiryACT extends DispatchAction 
{
	
	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		return this.NEW(mapping,form,request,response);		
	}
	
	public ActionForward NEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		CaseSheetEnquiryFB fb = (CaseSheetEnquiryFB) form;
		WebUTIL.refreshTransState(request);
		fb.reset(mapping, request);
		CaseSheetEnquiryUTL.getEssentials(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		CaseSheetEnquiryFB fb = (CaseSheetEnquiryFB) form;
		CaseSheetEnquiryUTL.searchPatientCaseSheet(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward GETDISCHARGEDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		CaseSheetEnquiryFB fb=(CaseSheetEnquiryFB)form;
		CaseSheetEnquiryUTL.getDischargeDetail(fb,request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward VIEWPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CaseSheetEnquiryFB fb = (CaseSheetEnquiryFB) form;

		String winPath = Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS;
		String linuxPath = Config.PATIENT_PROFILE_STORAGE_PATH_LINUX;
		String fileName = fb.getProfileId() + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;
		
		HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, winPath, linuxPath);
		fileUtil.readFile();
		fb.setProfileHTML(fileUtil.getFileContentInString());
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getProfileHTML());
		// Water Marking
		//WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "Profile ID:" + fb.getProfileId());
		return mapping.findForward("VIEW");
	}
	
	public ActionForward ORDERBYCRNO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		CaseSheetEnquiryFB fb = (CaseSheetEnquiryFB) form;
		CaseSheetEnquiryUTL.getOrderByCrNo(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward ORDERBYNAME(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		CaseSheetEnquiryFB fb = (CaseSheetEnquiryFB) form;
		CaseSheetEnquiryUTL.getOrderByName(fb, request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward SELECTRADIO(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		CaseSheetEnquiryFB fb = (CaseSheetEnquiryFB) form;
		CaseSheetEnquiryUTL.getCaseSheetEnquiry(fb, request);
		return mapping.findForward("NEW");
	}
	//DIAGNOSIS
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		CaseSheetEnquiryFB fb=(CaseSheetEnquiryFB)form;
		CaseSheetEnquiryUTL.popUpDiagnosis(fb,request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward SEARCHICDCODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{	
		CaseSheetEnquiryFB fb = (CaseSheetEnquiryFB) form;
		CaseSheetEnquiryUTL.searchIcdCode(fb,request);
		return mapping.findForward("POPUP");
	}
	
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
    }

}
