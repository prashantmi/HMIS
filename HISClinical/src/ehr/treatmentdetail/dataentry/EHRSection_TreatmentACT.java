/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.treatmentdetail.dataentry;
import inpatient.InpatientConfig;

import java.sql.SQLException;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.vo.PatientDetailVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.fb.PatientTreatmentDetailFB;
import opd.transaction.controller.util.PatientTreatmentDetailUTIL;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.diagnosis.dataentry.EHRSection_DiagnosisFB;
import ehr.diagnosis.dataentry.EHRSection_DiagnosisUTL;

public class EHRSection_TreatmentACT extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		return this.NEW(mapping, form, request, response);
	} 

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType!=null && deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPD");
			return mapping.findForward("NEW");
		}
		else
		{
			
			return mapping.findForward("NEW");
		}
		
	}
	
	public ActionForward VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		generateToken(request);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		EHRSection_TreatmentUTL.setEssentials(fb, request);
		
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType!=null && deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPD");
			return mapping.findForward("VIEW");
		}
		else
		{
			return mapping.findForward("NEW");
		}
		
	}
	
	public ActionForward PREVDRUGSHEDULE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EHRSection_TreatmentFB fb=(EHRSection_TreatmentFB)form;
		EHRSection_TreatmentUTL.getPrevDrugShedule(fb,request);
		return mapping.findForward("PREVDRUGSCHEDULE");		
	}
	
	public ActionForward DrugTime(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		EHRSection_TreatmentFB fb=(EHRSection_TreatmentFB)form;
		HttpSession session=WebUTIL.getSession(request);
		session.removeAttribute(OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP);
		EHRSection_TreatmentUTL.getDefaultShedule(fb,request);
		return mapping.findForward("DRUGTIME");		
	}
	
	
	public ActionForward INSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("INSTRUCTION");	
	}
	
	
	public ActionForward DRUGINSTRUCTION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
	
		return mapping.findForward("DRUGINSTRUCTION");
	}
	
	
	
	public ActionForward GETDRUGADVICEALERTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		EHRSection_TreatmentUTL.getDrugAdviceAlerts(fb, request, response);
		return null;
	} 
	
	

	public ActionForward SAVEDRUGSHEDULE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		EHRSection_TreatmentFB fb=(EHRSection_TreatmentFB)form;
		EHRSection_TreatmentUTL.saveDrugShedule(fb,request);
		return mapping.findForward("DRUGTIME");		
	}
	
	
	
	
	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
		EHRSection_TreatmentUTL.saveDetails(request,response,fb);
		//fb.reset(mapping, request);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
		return null;
		
	}
	
	
	public ActionForward PATCLINICALDOC_ADVICEONDISCHARGE_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.reset(mapping, request);
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DISCHARGE);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
	
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType!=null && deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPD");
			return mapping.findForward("DESKTREATMENT");
		}
		else
		{
			return mapping.findForward("DESKTREATMENT");
		}

	}

	/*
	 * For Adding key 'ENCTREATMENT' Composition for Patient Document 
	 * */
	public ActionForward PATCLINICALDOC_ADVICEONDISCHARGE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{	
		generateToken(request);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.reset(mapping, request);
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		return mapping.findForward("ENCTREATMENT");
		
	}
	
	public ActionForward PATCLINICALDOC_ADVICEONDISCHARGE_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		return mapping.findForward("PREVIEW");
	}
	
	
	public ActionForward ADVICEONDISCHARGE_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DISCHARGE);
		EHRSection_TreatmentUTL.saveDetails(request,response,fb);
		//fb.reset(mapping, request);
		//EHRSection_TreatmentUTL.setEssentials(fb, request);
		return null;
		
	}
	
	//Added by Vasu on 31.Oct.2018	
	public ActionForward PATCLINICALDOC_ENC_TREATMENT_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.reset(mapping, request);
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType!=null && deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPD");
			return mapping.findForward("DESKTREATMENT");
		}
		else
		{
			
			return mapping.findForward("DESKTREATMENT");
		}
		
	}
	
	//Added by Vasu on 03.Dec.2018
	public ActionForward PATCLINICALDOC_ENC_MED_ADV_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//generateToken(request);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.reset(mapping, request);
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DISCHARGE);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
	
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType!=null && deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			//return mapping.findForward("IPD");
			return mapping.findForward("DESKTREATMENT");
		}
		else
		{
			return mapping.findForward("DESKTREATMENT");
		}

	}
	
	/**Added by Vasu on 03.Dec.2018 for TreatmentGiven Details*/
	public ActionForward PATCLINICALDOC_ENC_MED_ADV_DRUG_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		//generateToken(request);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.reset(mapping, request);
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
		EHRSection_TreatmentUTL.setEssentialsForTreatmentGiven(fb, request);
		HttpSession session =WebUTIL.getSession(request);
		String deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType!=null && deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
			return mapping.findForward("TREATMENTGIVEN");
		}
		else
		{	
			return mapping.findForward("TREATMENTGIVEN");
		}
		
	}
	
	public ActionForward PATCLINICALDOC_ENC_MED_ADV_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//validateToken(request,response);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DISCHARGE);
		EHRSection_TreatmentUTL.saveDetails(request,response,fb);
		//fb.reset(mapping, request);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
		return null;

	}
	
	//Added by Vasu
	public ActionForward PATCLINICALDOC_ENC_TREATMENT_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
		EHRSection_TreatmentUTL.saveDetails(request,response,fb);
		//fb.reset(mapping, request);
		EHRSection_TreatmentUTL.setEssentials(fb, request);
		return null;
		
	}
	
	/**Added by Vasu on 23.July.2019 for Treatment Given section*/
	public ActionForward PATCLINICALDOC_ENC_MED_ADV_DRUG_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		//validateToken(request,response);
		EHRSection_TreatmentFB fb = (EHRSection_TreatmentFB) form;
		fb.setAdviceType(Config.TREATMENT_ADVICE_TYPE_DEFUALT);
		//EHRSection_TreatmentUTL.saveDetails(request,response,fb);
		EHRSection_TreatmentUTL.saveTreatmentGivenDetails(request,response,fb);
		//fb.reset(mapping, request);
		EHRSection_TreatmentUTL.setEssentialsForTreatmentGiven(fb, request);
		return null;
		
	}
	
}
