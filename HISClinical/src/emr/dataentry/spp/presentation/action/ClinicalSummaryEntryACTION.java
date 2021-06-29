package emr.dataentry.spp.presentation.action;


	import hisglobal.hisconfig.Config;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import emr.dataentry.spp.presentation.fb.UniPagePrescriptionFB;
import emr.dataentry.spp.presentation.util.UniPagePrescriptionUTL;
import emr.datafetch.patientClinicalDocuments.presentation.fb.PatientClinicalDocumentsFB;
import emr.datafetch.patientClinicalDocuments.presentation.util.PatientClinicalDocumentsUTL;
import opd.OpdConfig;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

	public class ClinicalSummaryEntryACTION extends DispatchAction
	{
		/*public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			return mapping.findForward("NEW");
		}*/
		public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
				throws Exception
		{
			Status objStatus = new Status();
			objStatus.add(Status.NEW);
			WebUTIL.setStatus(request, objStatus);
			return this.DIRECT(mapping, form, request, response);
		}

		
		public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			generateToken(request);
			UniPagePrescriptionFB fb = (UniPagePrescriptionFB) form;
			return this.GETPATDTL(mapping, form, request, response);
		}
		
		public ActionForward DIRECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{
			WebUTIL.refreshTransState(request);
			return mapping.findForward("GETPATDETAIL");
		}

		public ActionForward GETPATDTL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)		{
			UniPagePrescriptionFB _fb = (UniPagePrescriptionFB) form;
				//UniPagePrescriptionUTL.setPatientDtlByCrno(request, response,_fb);				
				UniPagePrescriptionUTL.getMRDPatientDtlByCrNo(request, response,_fb);
				return mapping.findForward("GETPATDETAIL");
		}
		
		
		
	
}