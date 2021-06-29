package ehr.patientTile.dataentry;

import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import inpatient.InpatientConfig;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class EHRSection_PatientTileACT extends DispatchAction
{
	
		
	public ActionForward PATCLINICALDOC_PATIENTSUMMARY_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_PatientTileFB fb = (EHRSection_PatientTileFB) form;
		EHRSection_PatientTileUTL.getInpatientDetailByCrNo(fb, request);
		return mapping.findForward("PATIENTSUMMARY");
	}
	
	public ActionForward PATCLINICALDOC_DISCHARGESUMMARY_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_PatientTileFB fb = (EHRSection_PatientTileFB) form;
		EHRSection_PatientTileUTL.getInpatientDischargeDetailByCrNo(fb, request);
		return mapping.findForward("DISCHARGESUMMARY");
	}
	public ActionForward PATCLINICALDOC_DISCHARGESUMMARY_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_PatientTileFB fb = (EHRSection_PatientTileFB) form;
		return mapping.findForward("PREVIEWDISCH");
	}
	
	public ActionForward PATCLINICALDOC_PATIENTSUMMARY_VIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_PatientTileFB fb = (EHRSection_PatientTileFB) form;
		return mapping.findForward("PREVIEW");
	}
	
	//Added by Vasu on 03.Dec.2018 for Patient Demographic and Admission Summary
	public ActionForward PATCLINICALDOC_PAT_DTL_DEMO_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_PatientTileFB fb = (EHRSection_PatientTileFB) form;
		EHRSection_PatientTileUTL.getInpatientDetailByCrNo(fb, request);
		return mapping.findForward("PATIENTSUMMARY");
	}
	
	public ActionForward PATCLINICALDOC_ENC_DTL_ADM_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		EHRSection_PatientTileFB fb = (EHRSection_PatientTileFB) form;
		EHRSection_PatientTileUTL.getInpatientDischargeDetailByCrNo(fb, request);
		return mapping.findForward("DISCHARGESUMMARY");
	}
}
