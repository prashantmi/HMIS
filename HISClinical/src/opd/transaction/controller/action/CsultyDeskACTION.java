/**
## 		Modification Log		: PATIENTSUMMARY
##		Modify Date				: 16-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/
package opd.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import opd.transaction.controller.fb.CsultyDeskFB;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class CsultyDeskACTION extends DispatchAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("NEW");
	}

	public ActionForward DESKDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DESKDIAGNOSIS");
	}

	public ActionForward DESKREFERPATIENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DESKREFERPATIENT");
	}

	public ActionForward DESKALLERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DESKALLERGIES");
	}
	public ActionForward DESKPATIENTALERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DESKPATIENTALERGIES");
	}
	
	public ActionForward GENERICTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("GENERICTEMPLATE");
	}

	public ActionForward ECONSULTATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("ECONSULTATION");
	}

	public ActionForward OPDSECONDARYCATEGORY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("OPDSECONDARYCATEGORY");
	}

	public ActionForward CONSULTATIONINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CONSULTATIONINBOX");
	}

	public ActionForward OPDNEXTVISITAPPOINTMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("OPDNEXTVISITAPPOINTMENT");
	}

	public ActionForward SERVICEREQUISITION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("SERVICEREQUISITION");
	}

	public ActionForward DOCUMENTARCHIVAL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DOCUMENTARCHIVAL");
	}

	public ActionForward IMAGEEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("IMAGEEXAMINATION");
	}

	public ActionForward MLCDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("MLCDETAIL");
	}

	public ActionForward PATBELONGING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PATBELONGING");
	}

	public ActionForward TRIAGEDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("TRIAGEDETAIL");
	}

	public ActionForward PATPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PATPROFILE");
	}
	
	public ActionForward PATPROFILEINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PATPROFILEINBOX");
	}

	public ActionForward AUDIOVIDEOPLAYER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("AUDIOVIDEOPLAYER");
	}

	public ActionForward PATIENTDEATHDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PATIENTDEATHDETAIL");
	}
	
	public ActionForward DEFAULT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DEFAULT");
	}

	public ActionForward CHANGEUNITROOM(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CHANGEUNITROOM");
	}

	public ActionForward BLOODBAGREQ(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("BLOODBAGREQ");
	}

	public ActionForward REQUISITIONRAISING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("REQUISITIONRAISING");
	}

	public ActionForward RESULTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("RESULTVIEWING");
	}

	public ActionForward PACREQUEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PACREQUEST");
	}

	public ActionForward OTLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("OTLIST");
	}

	public ActionForward ADMISSIONADVICE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("ADMISSIONADVICE");
	}

	public ActionForward GENERICPATIENTALERTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("GENERICPATIENTALERTS");
	}

	

	public ActionForward DESKTREATMENTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DESKTREATMENTDETAIL");
	}

	public ActionForward OTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("OTVIEWING");
	}

	public ActionForward OPDNEXTVISITDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		return mapping.findForward("OPDNEXTVISITDETAIL");
	}	
	public ActionForward POLICEEXAMREQT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		return mapping.findForward("POLICEEXAMREQT");
	}
	public ActionForward POLICEEXAMRPTGEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		return mapping.findForward("POLICEEXAMRPTGEN");
	}
	public ActionForward PATIENTSUMMARY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		return mapping.findForward("PATIENTSUMMARY");
	}

	public ActionForward EMRDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		CsultyDeskFB fb = (CsultyDeskFB) form;
		WebUTIL.getSession(request).setAttribute("crNo", fb.getPatCrNo());
		WebUTIL.getSession(request).setAttribute("departmentUnitCode", fb.getDepartmentUnitCode());
		return mapping.findForward("EMRDETAIL");
	}
	public ActionForward MEDICALCERTIFICATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("MEDICALCERTIFICATE");
	}
	
	public ActionForward ESTIMATEREQUEST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("ESTIMATEREQUEST");
	}
		
	public ActionForward CONSENTINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CONSENTINBOX");
	}

	public ActionForward CHARTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CHARTDETAIL");
	}
	
	public ActionForward EXTERNALEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("EXTERNALEXAMINATION");
	}
	public ActionForward DIETADVICE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DIETADVICE");
	}
	
	public ActionForward UNIPAGEPRESCRIPTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("UNIPAGEPRESCRIPTION");
	}
}
