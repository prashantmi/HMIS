package inpatient.transaction.controller.action;

import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ehr.EHRConfig;

public class NursingDeskACT extends DispatchAction
{
	
	private void clearRequest(HttpServletRequest request)
	{
		request.removeAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_KEY);
		request.removeAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_CLINICAL_AREA_CODE);
		request.removeAttribute(EHRConfig.EHR_OBJECT_FIELD_NAME_EHR_ACTION_KEY);
		WebUTIL.getSession(request).removeAttribute("crNo");
		WebUTIL.getSession(request).removeAttribute("departmentUnitCode");
	}
	
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("NEW");
	}

	public ActionForward DEFAULT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DEFAULT");
	}

	public ActionForward BLOODBAGREQ(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("BLOODBAGREQ");
	}

	public ActionForward NURSEROUND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("NURSEROUND");
	}

	public ActionForward DOCTORROUNDBYOTHER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("DOCTORROUNDBYOTHER");
	}
	
	public ActionForward PATPROFILEINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
			throws Exception
	{
		 clearRequest(request);
		 return mapping.findForward("PATPROFILEINBOX");
	}
	
	public ActionForward PATPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		 
		clearRequest(request);
		return mapping.findForward("PATPROFILE");
	}

	public ActionForward OUTTAKE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("OUTTAKE");
	}

	public ActionForward MONITORVITALS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("MONITORVITALS");
	}

	public ActionForward PATBELONGING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("PATBELONGING");
	}

	public ActionForward GENERICPATIENTALERTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("GENERICPATIENTALERTS");
	}

	public ActionForward GENERICTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("GENERICTEMPLATE");
	}

	public ActionForward VITALSRECORDING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return mapping.findForward("VITALSRECORDING");
	}

	public ActionForward REQUISITIONRAISING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("REQUISITIONRAISING");
	}
	
	public ActionForward RESULTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("RESULTVIEWING");
	}

	public ActionForward SAMPLECOLLECTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("SAMPLECOLLECTION");
	}

	public ActionForward PACKINGLISTGENERATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PACKINGLISTGENERATION");
	}
	
	public ActionForward PACKINGLISTGENERATIONREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PACKINGLISTGENERATION");
	}
	
	public ActionForward DRUGADMINIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DRUGADMINIS");
	}
	
	public ActionForward EXTADMINIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("EXTADMINIS");
	}
	
	public ActionForward DOCTORCALLBOOK(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DOCTORCALLBOOK");
	}
	
	public ActionForward DOCTORCALLINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DOCTORCALLINBOX");
	}
	
	public ActionForward DRUGREACTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DRUGREACTION");
	}

	public ActionForward STOCKENTRYOFBLOOD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("STOCKENTRYOFBLOOD");
	}
	
	public ActionForward DOCWARDROUNDDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("DOCWARDROUNDDTL");
	}
	
	public ActionForward BLOODTRANSFUSION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("BLOODTRANSFUSION");
	}
	
	public ActionForward BLOODTRANSFUSIONREACTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("BLOODTRANSFUSIONREACTION");
	}
	
	public ActionForward PRINTLABEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PRINTLABEL");
	}
	
	public ActionForward PENDINGTASKLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PENDINGTASKLIST");
	}
		
	public ActionForward CONSENTINBOX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CONSENTINBOX");
	}
		
	public ActionForward VALIDATEUSER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("VALIDATEUSER");
	}	
		
	public ActionForward WARDENQUIRY(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("WARDENQUIRY");
	}
	
	public ActionForward DOCUMENTARCHIVAL(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("DOCUMENTARCHIVAL");
	}
	
	public ActionForward EXTERNALEXAMINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("EXTERNALEXAMINATION");
	}
	
	public ActionForward DESKPMR(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		return mapping.findForward("DESKPMR");
	}

	public ActionForward AUDIOVIDEOPLAYER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("AUDIOVIDEOPLAYER");
	}
	
	public ActionForward NURSEROLEDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("NURSEROLEDTL");
	}
	
	public ActionForward ANCNEONATHANDOVER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("ANCNEONATHANDOVER");
	}

	public ActionForward ANCTRIMESTERCHKLST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("ANCTRIMESTERCHKLST");
	}
	
	public ActionForward CHARTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CHARTDETAIL");
	}
	
	public ActionForward VIEWSCANDOCS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("VIEWSCANDOCS");
	}
	
	public ActionForward NOTREPORTEDINWARD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("NOTREPORTEDINWARD");
	}
	
	public ActionForward WARDACCEPTANCE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("WARDACCEPTANCE");
	}
	
	public ActionForward LEAVEJOINRECORD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("LEAVEJOINRECORD");
	}
	
	public ActionForward CANCELTRANSFER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("CANCELTRANSFER");
	}
	
	public ActionForward PATIENTMOVEMENT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PATIENTMOVEMENT");
	}
	
	public ActionForward PATIENTDEATHDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PATIENTDEATHDETAIL");
	}
	
	public ActionForward PATIENTSUMMARY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		return mapping.findForward("PATIENTSUMMARY");
	}
	
	public ActionForward DESKALLERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		return mapping.findForward("DESKALLERGIES");
	}
	
	public ActionForward DESKPATIENTALERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		return mapping.findForward("DESKPATIENTALERGIES");
	}
	
	public ActionForward DESKTREATMENTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		 
		return mapping.findForward("DESKTREATMENTDETAIL");
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
	
	public ActionForward ANCDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		clearRequest(request);
		return mapping.findForward("ANCDETAIL");
	}

}
