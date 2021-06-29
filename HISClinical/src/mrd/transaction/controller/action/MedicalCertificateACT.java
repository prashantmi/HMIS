/* 
## Copyright Information		: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg 
 ## Module Name					: MRD
 ## Process/Database Object Name: Medical and Fitness certificate issue process
 ## Purpose						: Medical and Fitness certificate issue process
 ## Date of Creation			: 
 ## Modification Log			:				
 ##		Modify Date				: 04-Dec-2014 
 ##		Reason	(CR/PRS)		:  
 ##		Modify By				: Amit Garg

*/


package mrd.transaction.controller.action;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HisHTMLParserUtil;
import hisglobal.utility.servlets.ServletsUtilityConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;

import mrd.MrdConfig;
import mrd.transaction.controller.fb.MedicalCertificateFB;
import mrd.transaction.controller.utl.MedicalCertificateUTL;
import opd.transaction.controller.fb.ConsentRequestFB;
import opd.transaction.controller.util.ConsentRequestUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 * @author Administrator
 *
 */
public class MedicalCertificateACT extends CSRFGardTokenAction
{
	/*
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		fb.setEmpMappingFlag(Config.MEDICAL_CERTIFICATE_EMPLOYEE_MAPPING);
		MedicalCertificateUTL.setSysdate(request);
		boolean exist=MedicalCertificateUTL.checkEmployeeId(fb,request);
		if(exist==false)
			return this.NEW(mapping, form, request, response);
		else
			return this.EMPMAP(mapping, form, request, response);
	}
	*/
	//Medical and Fitness Certificate 
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}
	public ActionForward EMPMAP(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		return mapping.findForward("NEW");
	}
	
	/** Action is called at the initial loading of a Page
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
/*	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		
		fb.setGenerationMode(Config.MEDICAL_CERTIFICATE_GENERATION);
		fb.setBackDatedFlagMC(Config.GENERATE_MEDICAL_CERTIFICATE_BACK_DATED);
		fb.setBackDatedFlagFC(Config.GENERATE_FITNESS_CERTIFICATE_BACK_DATED);
		MedicalCertificateUTL.setSysdate(request);
		fb.setTempMode(fb.getHmode());
		
		return mapping.findForward("NEW");
	}
	
	*/
	// Medical and  fitness certificate Patient Req List
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		generateToken(request);
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		MedicalCertificateUTL.getMedicalCertificatePatReqList(fb,request);
		return mapping.findForward("NEW");
	}
	/**  Getting The Patient Detail & All Episode of the Patient
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward GETPATDTL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		MedicalCertificateUTL.getAllEpisodeOfThePatient(fb,request);
		fb.setHmode("GETPATDTL");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward MEDCERT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		fb.setFlagForMCSave("");
		fb.setCurrentPage(1);
		fb.setCertificateType(MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE );
		MedicalCertificateUTL.getRestAdvice(fb, request);
		MedicalCertificateUTL.getGeneratedCertificateForFitnessNModify(fb,request);
		fb.setHmode("MEDCERT");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward FITCERT(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		MedicalCertificateUTL.getGeneratedCertificateForFitnessNModify(fb,request);
		fb.setHmode("FITCERT");
		fb.setTempMode(fb.getHmode());
		return mapping.findForward("NEW");
	}
	
	public ActionForward SAVEMC(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		MedicalCertificateUTL.saveMedicalCertificateGeneration(fb,request);
		fb.setHmode(fb.getTempMode());
		if(fb.getIsOverlapped().equals(MrdConfig.IS_OVERLAPPED_YES))
		{
			MedicalCertificateUTL.getRestAdvice(fb,request);
			MedicalCertificateUTL.getGeneratedCertificateForFitnessNModify(fb,request);
			return mapping.findForward("NEW");
		}	
		else
			return this.NEW(mapping, form, request, response);
	}
	public ActionForward SAVEMCNEW(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
	//	fb.setFlagForMCSave("1");
		MedicalCertificateUTL.saveMedicalCertificateGeneration(fb,request);
		fb.setHmode(fb.getTempMode());
		if(fb.getIsOverlapped().equals(MrdConfig.IS_OVERLAPPED_YES))
		{
			MedicalCertificateUTL.getRestAdvice(fb,request);
			return mapping.findForward("NEW");
		}	
		else
			return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward SAVEFC(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		validateToken(request,response);
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		MedicalCertificateUTL.saveFitnessCertificateGeneration(fb,request);
		fb.setHmode(fb.getTempMode());
		if(fb.getIsOverlapped().equals(MrdConfig.IS_OVERLAPPED_YES))
		{
			MedicalCertificateUTL.getGeneratedCertificateForFitnessNModify(fb,request);
			return mapping.findForward("NEW");
		}	
		else
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward CHANGEMODE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)throws Exception
	{
		MedicalCertificateFB fb=(MedicalCertificateFB)form;
		if(fb.getCertificateType().equals(MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE))
			return this.MEDCERT(mapping, form, request, response);
		else
			return this.FITCERT(mapping, form, request, response);
	}
	
	

	
	
	/** Pagination For Episode List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward PAGINATIONEPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		fb.setHmode(fb.getTempMode());
		Status objStatus = new Status();
		objStatus.add(Status.LIST);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	
	
	/** Pagination For Certificate List
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward PAGINATIONMC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		fb.setHmode(fb.getTempMode());
		Status objStatus = new Status();
		objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}
	
	
	
	/** Opening a Popup For Diagnosis
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward POPUP(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		//MedicalCertificateFB fb = (MedicalCertificateFB) form;
		return mapping.findForward("POPUP");
	}
	
	/** Searching the Diagnosis
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public  ActionForward SEARCH(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		MedicalCertificateUTL.searchCode(fb,request);
		return mapping.findForward("POPUP");
	}
	
	/** Populate the Diagnosis 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward POPULATE (ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		MedicalCertificateUTL.populate(fb,request,response);
		return null;
	}
	
	/** Back to initPage
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public  ActionForward CANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		fb.setFlagForMCSave("");
		
		if(fb.getTempMode().equals("GETPATDTL"))
			return this.NEW(mapping, form, request, response);
		else if(fb.getTempMode().equals("MEDCERT"))
			return this.GETPATDTL(mapping, form, request, response);
		else if(fb.getTempMode().equals("FITCERT"))
			return this.GETPATDTL(mapping, form, request, response);
		else
			return this.FINALCANCEL(mapping, form, request, response);
	}
	
	public  ActionForward FINALCANCEL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("CANCEL");
	}
	/* Pagination for Medical and fiitness certificate request list*/
	public ActionForward PAGINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		Status objStatus= new Status();		
		objStatus.add(Status.RECORDFOUND);
		objStatus.add(Status.NEW);
		request.setAttribute(Config.STATUS_OBJECT,objStatus);
		fb.setHmode("");
		return mapping.findForward("NEW");
	}
	/* Medical and fitness certificate issue patient all details */
	public ActionForward GETDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		WebUTIL.refreshTransState(request);	
		fb.reset(mapping, request);
		MedicalCertificateUTL.getMedicalCertificateIssuePatDtl(fb,request);
		return mapping.findForward("GETDTL");
	}
	/* Medical and fitness certificates save details after billing*/
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		if(MedicalCertificateUTL.saveMedicalCertificateIssueDtl(fb,request))
		{
			
				return this.GETDTL(mapping, form, request, response);
				//return this.NEW(mapping, form, request, response);
			
		}
		else
			return mapping.findForward("GETDTL");
		    //return mapping.findForward("NEW");
	}
	
	public ActionForward POPUPCERT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		MedicalCertificateUTL.setCertificateData(fb, request);
		return mapping.findForward("POPUPCERT");
	}
	
	public ActionForward PRINT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		MedicalCertificateFB fb = (MedicalCertificateFB) form;
		//Added by Vasu on 09.Mar.18 for Base64 Decoding
        String base64Data = fb.getConsentHtmlCode();
        String decoded = new String(DatatypeConverter.parseBase64Binary(base64Data));
       //fb.setHtmlCode(HisHTMLParserUtil.freezeHTMLCodeElements(decoded));
		//WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getConsentHtmlCode());
        WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, decoded);
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		return mapping.findForward("POPUPCERT");
	}
	
}
