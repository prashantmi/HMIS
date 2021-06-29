package ehr.visitreason.dataentry;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.EHRConfig;
import emr.vo.PatientClinicalDocDetailVO;

public class EHRSection_VisitReasonACT extends CSRFGardTokenAction {

	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
			return mapping.findForward("NEW");
	}
	
	
	public ActionForward OPDNEXTVISITDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//fb.reset(mapping, request);
		EHRSection_VisitReasonUTL.getEssentials(fb, request,response);
		return mapping.findForward("OPDNEXTVISITDETAIL");
	}
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		EHRSection_VisitReasonUTL.saveDetails(request,response,fb);
		return null;
		
	}
	
	
	public ActionForward PATCLINICALDOC_OPDNEXTVISITDETAIL_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		EHRSection_VisitReasonUTL.getEssentials(fb, request,response);
		return mapping.findForward("OPDNEXTVISITDETAIL");
	}
	
	public ActionForward PATCLINICALDOC_OPDNEXTVISITDETAIL_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		EHRSection_VisitReasonUTL.getEssentials(fb, request,response);
		return mapping.findForward("ENCNEXTVISITDETAIL");
	}
	
	
	public ActionForward PATCLINICALDOC_OPDNEXTVISITDETAIL_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		return mapping.findForward("PREVIEW");
	}
	
	//Added by Vasu on 03.Dec.2018
	
	public ActionForward PATCLINICALDOC_ENC_CC_ROV_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		//fb.reset(mapping, request);
		HttpSession session = request.getSession();
        PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();
		EHRSection_VisitReasonUTL.getEssentials(fb, request,response);

		clinicalDocVO =  (PatientClinicalDocDetailVO)session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
		/*if(clinicalDocVO.getDocumentType().equals("51"))
		{
		    return mapping.findForward("OPDNEXTVISITDETAIL");
		}
		else
		{
			return mapping.findForward("CHEIFCOMPLAINTSDETAIL");
		}*/
		return mapping.findForward("OPDNEXTVISITDETAIL");
	}
	
	//Added by Vasu on 12.Dec.2018
	public ActionForward PATCLINICALDOC_ENC_CC_ROV_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_VisitReasonFB fb = (EHRSection_VisitReasonFB) form;
		EHRSection_VisitReasonUTL.saveDetails(request,response,fb);
		EHRSection_VisitReasonUTL.getEssentials(fb, request,response);
		return null;
		
	}
}
