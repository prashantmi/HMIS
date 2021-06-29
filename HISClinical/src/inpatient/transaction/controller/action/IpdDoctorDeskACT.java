/**
## 		Modification Log		: PATIENTSUMMARY					
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/
package inpatient.transaction.controller.action;

import hisglobal.presentation.WebUTIL;
import inpatient.transaction.controller.fb.IpdDoctorDeskFB;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.EHRConfig;

public class IpdDoctorDeskACT extends DispatchAction
{
	private void clearRequest(HttpServletRequest request)
	{
		request.removeAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_KEY);
		request.removeAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_CODE);
		request.removeAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_EHR_ACTION_KEY);
		WebUTIL.getSession(request).removeAttribute("crNo");
		WebUTIL.getSession(request).removeAttribute("departmentUnitCode");
	}
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		clearRequest(request);
		return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		clearRequest(request);
		return mapping.findForward("NEW");
	}
	
	public ActionForward DEFAULT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		clearRequest(request);
		return mapping.findForward("DEFAULT");
	}
	
	public ActionForward DESKDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("DESKDIAGNOSIS");
	}
	
	public ActionForward DESKALLERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("DESKALLERGIES");
	}
	
	
	public ActionForward DESKPATIENTALERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("DESKPATIENTALERGIES");
	}
	
	public ActionForward DOCUMENTARCHIVAL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("DOCUMENTARCHIVAL");
	}
	
	public ActionForward DESKREFERPATIENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("DESKREFERPATIENT");
	}
	
	public ActionForward BLOODBAGREQ(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("BLOODBAGREQ");
	}
	
	public ActionForward IMAGEEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("IMAGEEXAMINATION");
	}
	
	public ActionForward ECONSULTATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("ECONSULTATION");
	}
	
	public ActionForward DOCTORROUND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("DOCTORROUND");
	}
	
	public ActionForward PATPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("PATPROFILE");
	}

	public ActionForward GENERICPATIENTALERTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("GENERICPATIENTALERTS");
	}

	public ActionForward CONSENTINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("CONSENTINBOX");
	}

	public ActionForward CONSULTATIONINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("CONSULTATIONINBOX");
	}

	public ActionForward REQUISITIONRAISING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("REQUISITIONRAISING");
	}

	public ActionForward DESKTREATMENTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		clearRequest(request);
		return mapping.findForward("DESKTREATMENTDETAIL");
	}
	
	public ActionForward MONITORVITALS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {

		clearRequest(request);
		return mapping.findForward("MONITORVITALS");
	}

	public ActionForward GENERICTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {

		clearRequest(request);
		return mapping.findForward("GENERICTEMPLATE");
	}

	public ActionForward PATIENTDEATHDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {

		clearRequest(request);
		return mapping.findForward("PATIENTDEATHDETAIL");
	}

	public ActionForward OPDSECONDARYCATEGORY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {

		clearRequest(request);
		return mapping.findForward("OPDSECONDARYCATEGORY");
	}
	
	public ActionForward DISCHARGEREQUEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws Exception {

		clearRequest(request);
		return mapping.findForward("DISCHARGEREQUEST");
	}

	public ActionForward RESULTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("RESULTVIEWING");
	}

	public ActionForward PACREQUEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("PACREQUEST");
	}

	public ActionForward OTLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("OTLIST");
	}
	
	public ActionForward DOCCALLACKNOWLEDGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("DOCCALLACKNOWLEDGE");
	}

	public ActionForward ANCDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("ANCDETAIL");
	}

	public ActionForward ANCDELIVERYDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("ANCDELIVERYDTL");
	}

	public ActionForward ANCNEONATALDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("ANCNEONATALDTL");
	}
	
	public ActionForward DOCTORROUNDBYOTHER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("DOCTORROUNDBYOTHER");
	}
	
	public ActionForward PATPROFILEINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("PATPROFILEINBOX");
	}

	public ActionForward AUDIOVIDEOPLAYER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("AUDIOVIDEOPLAYER");
	}

	public ActionForward EXTERNALEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("EXTERNALEXAMINATION");
	}

	public ActionForward OTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("OTVIEWING");
	}

	public ActionForward ANCNEONATHANDOVER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("ANCNEONATHANDOVER");
	}

	public ActionForward ANCTRIMESTERCHKLST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("ANCTRIMESTERCHKLST");
	}

	public ActionForward EMRDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		IpdDoctorDeskFB fb = (IpdDoctorDeskFB) form;
		WebUTIL.getSession(request).setAttribute("crNo", fb.getPatCrNo());
		WebUTIL.getSession(request).setAttribute("departmentUnitCode", fb.getDepartmentUnitCode());
		return mapping.findForward("EMRDETAIL");
	}
	
	public ActionForward CHARTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("CHARTDETAIL");
	}

	public ActionForward VIEWSCANDOCS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("VIEWSCANDOCS");
	}
	
	public ActionForward WARDENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("WARDENQUIRY");
	}
	
	public ActionForward PATIENTSUMMARY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("PATIENTSUMMARY");
	}
	
	public ActionForward MEDICALCERTIFICATE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("MEDICALCERTIFICATE");
	}
	
	public ActionForward ESTIMATEREQUEST(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("ESTIMATEREQUEST");
	}
	public ActionForward OTRECORENTRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("OTRECORENTRY");
	}
	public ActionForward OFFLINESERVICEAREAREQUISITION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("OFFLINESERVICEAREAREQUISITION");
	}
	public ActionForward SERVICEREQUISITION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("SERVICEREQUISITION");
	}
	public ActionForward DIETADVICE(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("DIETADVICE");
	}
	
	public ActionForward PATIENTCLINICALDOCUMENTS(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		return mapping.findForward("PATIENTCLINICALDOCUMENTS");
	}

	public ActionForward DM_SP_E_DISCHARGE_SUMMARY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		request.setAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_KEY, EHRConfig.EHR_CLINICAL_AREA_DISCHARGE_SUMMARY_KEY);
		request.setAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_CODE, EHRConfig.EHR_CLINICAL_AREA_DISCHARGE_SUMMARY_CODE);
		request.setAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_EHR_ACTION_KEY, EHRConfig.EHR_ACTIONS_EDIT_KEY);

		return mapping.findForward("DM_SP_E_DISCHARGE_SUMMARY");
	}
	public ActionForward DM_SP_P_DISCHARGE_SUMMARY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		clearRequest(request);
		request.setAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_KEY, EHRConfig.EHR_CLINICAL_AREA_DISCHARGE_SUMMARY_KEY);
		request.setAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_CODE, EHRConfig.EHR_CLINICAL_AREA_DISCHARGE_SUMMARY_CODE);
		request.setAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_EHR_ACTION_KEY, EHRConfig.EHR_ACTIONS_PREPARE_KEY);

		return mapping.findForward("DM_SP_P_DISCHARGE_SUMMARY");
	}
	
	public ActionForward VITALSRECORDING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("VITALSRECORDING");
	}

}
