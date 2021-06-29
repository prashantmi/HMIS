/*
 * Nilesh Gupta
 * Date:- 30-10-2017
 * New Process Status At Discharge
*/
package ehr.statusdischarge.dataentry;

import hisglobal.presentation.CSRFGardTokenAction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeFB;
import ehr.statusdischarge.dataentry.EHRSection_StatusAtDischargeUTL;;

public class EHRSection_StatusAtDischargeACT extends CSRFGardTokenAction {

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
	
	
	public ActionForward DISCHARGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_StatusAtDischargeFB fb = (EHRSection_StatusAtDischargeFB) form;
		//fb.reset(mapping, request);
		EHRSection_StatusAtDischargeUTL.getEssentials(fb, request,response);
		return mapping.findForward("DISCHARGE");
	}

	public ActionForward SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_StatusAtDischargeFB fb = (EHRSection_StatusAtDischargeFB) form;
		EHRSection_StatusAtDischargeUTL.saveDetails( request, response, fb);
		return null;
		
	}
	
	
	/*PAT CLINICAL DOCUMENTS SECTION */
	
	
	public ActionForward PATCLINICALDOC_DISCHARGESTATUS_ADDSAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_StatusAtDischargeFB fb = (EHRSection_StatusAtDischargeFB) form;
		//fb.reset(mapping, request);
		EHRSection_StatusAtDischargeUTL.getEssentials(fb, request,response);
		return mapping.findForward("DISCHARGE");
	}

	/*
	 * For Adding 'Diagnosis' key 'ENCDIAGNOSIS' Composition for Patient Document 
	 * */
	public ActionForward PATCLINICALDOC_DISCHARGESTATUS_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		EHRSection_StatusAtDischargeFB fb = (EHRSection_StatusAtDischargeFB) form;
		//fb.reset(mapping, request);
		EHRSection_StatusAtDischargeUTL.getEssentials(fb, request,response);
		return mapping.findForward("SELECT");
		
	}
	
	public ActionForward PATCLINICALDOC_DISCHARGESTATUS_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		return mapping.findForward("PREVIEW");
	}
	
	public ActionForward DISCHARGESTATUS_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_StatusAtDischargeFB fb = (EHRSection_StatusAtDischargeFB) form;
		EHRSection_StatusAtDischargeUTL.saveDetails( request, response, fb);
		return null;
		
	}
	
	//Added by Vasu on 03.Dec.2018
	public ActionForward PATCLINICALDOC_ENC_PAT_COND_DIS_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_StatusAtDischargeFB fb = (EHRSection_StatusAtDischargeFB) form;
		//fb.reset(mapping, request);
		EHRSection_StatusAtDischargeUTL.getEssentials(fb, request,response);
		return mapping.findForward("DISCHARGE");
	}
	
	//Added by Vasu on 12.Dec.2018
	public ActionForward PATCLINICALDOC_ENC_PAT_COND_DIS_SAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{		
		validateToken(request,response);
		EHRSection_StatusAtDischargeFB fb = (EHRSection_StatusAtDischargeFB) form;
		EHRSection_StatusAtDischargeUTL.saveDetails( request, response, fb);
		EHRSection_StatusAtDischargeUTL.getEssentials(fb, request,response);
		return null;
		
	}
	
}
