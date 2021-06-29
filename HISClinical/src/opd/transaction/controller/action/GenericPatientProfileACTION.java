package opd.transaction.controller.action;

import hisglobal.exceptions.HisException;
import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.servlets.ServletsUtilityConfig;
import hisglobal.vo.PatientProfileDetailVO;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import opd.OpdConfig;
import opd.transaction.controller.fb.GenericPatientProfileFB;
import opd.transaction.controller.util.ChartReportingUTIL;
import opd.transaction.controller.util.GenericPatientProfileUTIL;

import org.apache.commons.codec.binary.Base64;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import emr.dataentry.spp.presentation.util.UniPagePrescriptionUTL;

public class GenericPatientProfileACTION extends CSRFGardTokenAction
{
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		return this.NEW(mapping, form, request, response);
	}

	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		generateToken(request);
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
		request.getSession().removeAttribute(OpdConfig.DRUG_SHEDULE_MAP);
		fb.reset();
		GenericPatientProfileUTIL.setEssentials(fb, request);
		return mapping.findForward("NEW");
	}

	public ActionForward MODIFYPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request,response);
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form; 
		GenericPatientProfileUTIL.modifyProfile(fb, request);
		GenericPatientProfileUTIL.setEssentials(fb, request);
		Status objStatus = new Status();
		objStatus.add(Status.NEW, "", "Profile Modified Successfully");
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}

	public ActionForward REMOVEPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.removeProfile(fb, request);
		GenericPatientProfileUTIL.setEssentials(fb, request);
		Status objStatus = new Status();
		objStatus.add(Status.NEW, "", "Profile Removed Successfully");
		WebUTIL.setStatus(request, objStatus);
		return mapping.findForward("NEW");
	}

	public ActionForward SETACCESSPRIV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setAccessPrivlEssentials(fb, request);
		return mapping.findForward("ACCESS");
	}

	public ActionForward SAVEACCESSPRIV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		//validateToken(request,response);
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.saveAccessPrivlEssentials(fb, request);
		GenericPatientProfileUTIL.setAccessPrivlEssentials(fb, request);
		return mapping.findForward("ACCESS");
	}

	public ActionForward DELETEUSERPRIV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.removeUserPriv(fb, request);
		// GenericPatientProfileUTIL.setAccessPrivlEssentials(fb, request);
		return mapping.findForward("ACCESS");
	}

	public ActionForward SEARCHUSER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setSearchUsers(fb, request);
		return mapping.findForward("ACCESS");
	}

	public ActionForward ADDACCESSPRIVUSER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.addSearchUsers(fb, request);
		return mapping.findForward("ACCESS");
	}

	public ActionForward PROFILEOPTIONS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		fb.setSelectedMenu("");
		if (GenericPatientProfileUTIL.setProfileOptionsList(fb, request)) return mapping.findForward("OPTIONS");
		else return this.NEW(mapping, form, request, response);
	}

	
	// Advice On Discharge
	public ActionForward ADVICEONDISCHARGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientAdviceOnDischargeDetail(fb, request)) return mapping.findForward("ADVICEONDISCHARGE");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETADVICEONDISCHARGE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientAdviceOnDischargeDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}
	
	
	//VITAL CHART DETAIL Added by manisha gangwar date: 10.08.2017
	
	public ActionForward CHARTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getChartReportingEssentials(fb, request)) return mapping.findForward("CHARTDETAIL");
		else return mapping.findForward("OPTIONS");
	}
	
	

	public ActionForward GETVITALCHARTVIEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if(GenericPatientProfileUTIL.getChartViewData(fb, request))
			return mapping.findForward("CHARTDETAIL");
		else
			return mapping.findForward("OPTIONS");
		
	}
	
	public ActionForward SETCHARTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setChartViewDetail(fb, request);
		return mapping.findForward("OPTIONS");
		
	}
	
	
	//END VITAL CHART DETAIL
	

	public ActionForward OTHERINSTRUCTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		String choice = (String) WebUTIL.getSession(request).getAttribute(OpdConfig.SELECTED_CHOICE);
		fb.setInstActivityType(choice);
		// DrugAdministrationUTL.getIntruction(fb,request);
		return mapping.findForward("OTHERINSTRUCTION");
	}

	public ActionForward SAVEOTHERINSTRUCTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.saveOtherInstruction(fb, request);
		return mapping.findForward("OTHERINSTRUCTION");
	}

	public ActionForward ALLINSTRUCTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.getAllInstructionAndActivity(fb, request);
		fb.setInstActivityType("1");
		WebUTIL.setAttributeInSession(request, OpdConfig.SELECTED_CHOICE, "1");
		return mapping.findForward("OTHERINSTRUCTION");
	}

	public ActionForward DEPTWISEINSTRUCTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.getDeptWiseInstructionAndActivity(fb, request);
		fb.setInstActivityType("0");
		WebUTIL.setAttributeInSession(request, OpdConfig.SELECTED_CHOICE, "0");
		return mapping.findForward("OTHERINSTRUCTION");
	}

	public ActionForward ADDDRUGROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.addNewDrugRow(fb, request);
		return mapping.findForward("ADVICEONDISCHARGE");
		/*
		 * HttpSession session =WebUTIL.getSession(request); String
		 * deskType=(String)session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		 * if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK)) { return mapping.findForward("IPDDRUGDETAIL"); }
		 * else { return mapping.findForward("DRUGDETAIL"); }
		 */
	}

	public ActionForward ADDEXTTREATMENTROW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.addNewExtTretmentRow(fb, request);
		return mapping.findForward("ADVICEONDISCHARGE");
	}

	public ActionForward DISCHARGEPROFILEFOOTER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientProfileFooterDetail(fb, request)) return mapping.findForward("DISCHARGEPROFILEFOOTER");
		else return mapping.findForward("OPTIONS");
	}
	
	

	public ActionForward SETDISCHARGEPROFILEFOOTER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientDischargeProfileFooterDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}
	

	public ActionForward DRUGSCHEDULE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		HttpSession session = WebUTIL.getSession(request);
		session.removeAttribute(OpdConfig.LIST_OF_DRUGDOSE_FOR_POPUP);
		GenericPatientProfileUTIL.getDefaultSchedule(fb, request);
		return mapping.findForward("DRUGTIME");
	}

	public ActionForward SAVEDRUGSHEDULE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		validateToken(request,response);
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.saveDrugSchedule(fb, request);
		return mapping.findForward("DRUGTIME");
	}

	// Diagnosis
	public ActionForward DESKDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientDiagnosisDetail(fb, request)) return mapping.findForward("DESKDIAGNOSIS");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETDESKDIAGNOSIS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientDiagnosisDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Allergies
	public ActionForward DESKALLERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientAllergiesDetail(fb, request)) return mapping.findForward("DESKALLERGIES");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETDESKALLERGIES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientAllergiesDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Operation Theatre
	public ActionForward OTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientOperationDetail(fb, request)) return mapping.findForward("OTVIEWING");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETOTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientOperationDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Treatment
	public ActionForward DESKTREATMENTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientTreatmentDetail(fb, request)) return mapping.findForward("DESKTREATMENTDETAIL");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETDESKTREATMENTDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientTreatmentDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Patient Chronic Disease
	public ActionForward GENERICPATIENTALERTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientAlertsDetail(fb, request)) return mapping.findForward("DESKPATIENTALERTS");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETGENERICPATIENTALERTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientAlertsDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Complaints
	public ActionForward GENERICTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientComplaintsDetail(fb, request)) return mapping.findForward("DESKGENERICTEMPLATE");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward CHANGEREPORTMODE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setTempReportEssentials(fb, request);
		return mapping.findForward("DESKGENERICTEMPLATE");
	}

	// --- Not In Use
	public ActionForward VIEWDATEWISECOMPLAINTS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		// fb.setViewMode(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT);
		/*
		 * HttpSession session = request.getSession(); Map mpTemps = null; mpTemps =
		 * (HashMap)session.getAttribute(GenericTemplateConfig.GENERIC_CHART_TEMPLATE_LIST_MAP);
		 */
		GenericPatientProfileUTIL.getViewDateWiseComplaint(fb, request);
		return mapping.findForward("DATEWISECOMPLAINT");
	}

	public ActionForward SETGENERICTEMPLATE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
		GenericPatientProfileUTIL.setPatientComplaintsDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	/*
	 * public ActionForward GOGETREPORT(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	 * HttpServletResponse response) { GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
	 * fb.setViewMode(GenericTemplateConfig.GENERIC_CHART_VIEW_MODE_TEMP_WISE_TABLE);
	 * GenericPatientProfileUTIL.setViewReportEssentials(fb, request); return mapping.findForward("VIEWREPORT"); }
	 */

	// Progress Notes
	public ActionForward PROGRESSNOTES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientProgressNotes(fb, request)) return mapping.findForward("PROGRESSNOTES");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward OPDPROGRESSNOTES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientOPDProgressNotes(fb, request)) return mapping.findForward("OPDPROGRESSNOTES");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETPROGRESSNOTES(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientProgressNotes(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Image Examination
	public ActionForward IMAGEEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientImageExamDetail(fb, request)) return mapping.findForward("IMAGEEXAMINATION");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETIMAGEEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientImageExamDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Next Visit Detail
	public ActionForward OPDNEXTVISITDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientNextVisitDetail(fb, request)) return mapping.findForward("OPDNEXTVISITDETAIL");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETOPDNEXTVISITDETAIL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientNextVisitDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Profile Footer
	public ActionForward PROFILEFOOTER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientProfileFooterDetail(fb, request)) return mapping.findForward("PROFILEFOOTER");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETPROFILEFOOTER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientProfileFooterDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Investigation
	public ActionForward RESULTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientInvestigationDetail(fb, request)) return mapping.findForward("RESULTVIEWING");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETRESULTVIEWING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientInvestigationDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// IPD Doctor/Nurse Progress Notes
	public ActionForward DOCTORROUND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientEpisodeProgressNotes(fb, request)) return mapping.findForward("DOCTORROUND");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETDOCTORROUND(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientEpisodeProgressNotes(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// External Investigation
	public ActionForward EXTERNALEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientExtInvestigationDetail(fb, request)) return mapping.findForward("EXTERNALEXAMINATION");
		else return mapping.findForward("OPTIONS");
	}

	public ActionForward SETEXTERNALEXAMINATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setPatientExtInvestigationDetail(fb, request);
		return mapping.findForward("OPTIONS");
	}

	// Outtake Investigation
	public ActionForward OUTTAKE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.getPatientOutTakeDetail(fb, request)) return mapping.findForward("OUTTAKE");
		else return mapping.findForward("OPTIONS");
	}

	// get the detail of the profile to generating
	public ActionForward VIEWGENRATEDPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		System.out.println("In GenericPatientProfileACTION In VIEWGENRATEDPROFILE()");
		GenericPatientProfileUTIL.fetchDetailsForGenerate(fb, request);
		return mapping.findForward("GENERATE");
	}

	// Save the Profile
	public ActionForward GENRATEPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request,response);
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		
		if(GenericPatientProfileUTIL.setProfileOptionsList(fb, request))
		{
			if (GenericPatientProfileUTIL.genrateProfile(fb, request))
			{
				//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
				fb.reset();
				GenericPatientProfileUTIL.setEssentials(fb, request);
				Status objStatus = new Status();
				objStatus.add(Status.NEW, "", "Profile Saved Successfully");

                request.getSession().removeAttribute(OpdConfig.PATIENT_PROFILE_BASED_DESK_MENUS_LIST);
				
				WebUTIL.setStatus(request, objStatus);
				return mapping.findForward("NEW");
			}
			else
			{
				return mapping.findForward("OPTIONS");
			}
		}
		else
		{
			return this.NEW(mapping, form, request, response);
		}
	}

	public ActionForward VIEWPRINTPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		
		if(fb.getIsSinglePageFlag()!= null && fb.getIsSinglePageFlag().equals("1"))
		{
			GenericPatientProfileUTIL.getViewSinglePageDoc(request,response,fb);
			return null;
		}
		//else
		//{
		//Added by Dheeraj to get PDF Profile
		/*GenericPatientProfileUTIL.viewPatientProfile(fb,request,response);

		return null;*/
		
		
		/*String getcontent=GenericPatientProfileUTIL.getViewDoc(fb,request,response);
		*/
		/*HttpSession session = WebUTIL.getSession(request);
		List<PatientProfileDetailVO> lstPrevProfiles = null;
		PatientProfileDetailVO patProfileDtlVO = null;
		
		lstPrevProfiles = (List<PatientProfileDetailVO>) session.getAttribute(OpdConfig.PATIENT_PROFILE_EPISODE_PROFILES_LIST);
		patProfileDtlVO = lstPrevProfiles.get(0);*/
		//GenericPatientProfileUTIL.setEssentials(fb, request);
		String getcontent=GenericPatientProfileUTIL.viewHtmlPatientProfile(fb,request,response);
		fb.setProfileHTML(getcontent);
		
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getProfileHTML());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		//}
		return mapping.findForward("VIEWPRINT");
	}
	
	
	
	// View or print the profile
	/*public ActionForward VIEWPRINTPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;

		String winPath = Config.PATIENT_PROFILE_STORAGE_PATH_WINDOWS;
		String linuxPath = Config.PATIENT_PROFILE_STORAGE_PATH_LINUX;
		String fileName = fb.getProfileId() + Config.PATIENT_PROFILE_FILE_STORAGE_EXT;

		HisFileControlUtil fileUtil = new HisFileControlUtil(fileName, winPath, linuxPath);
		fileUtil.readFile();
		fb.setProfileHTML(fileUtil.getFileContentInString());
		
		
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getProfileHTML());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());

		return mapping.findForward("VIEWPRINT");
	}
	*/
	
	
	

	// Viewing Profile
	public ActionForward VIEWPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if(!fb.getReqDnoList().equals(""))
		{
			GenericPatientProfileUTIL.getPatientInvestigationTestDetail(fb, request);	
		}
		return mapping.findForward("PROFILELAYOUT");
	}

	// Moving Option Up
	public ActionForward OPTIONUP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.moveOptionUp(fb, request);
//		return mapping.findForward("PROFILELAYOUT");
		return mapping.findForward("GENERATE");
	}

	// Moving Option Down
	public ActionForward OPTIONDOWN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.moveOptionDown(fb, request);
//		return mapping.findForward("PROFILELAYOUT");
		return mapping.findForward("GENERATE");
	}

	// Removing Option
	public ActionForward REMOVEOPTION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.removeOption(fb, request);
		return mapping.findForward("PROFILELAYOUT");
	}

	// get the detail of the profile for modification
	public ActionForward FETCHPROFILEDETAILS(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.fetchProfileDetails(fb, request);
		return mapping.findForward("GETPROFILEHEADER");
	}

	// set the profile option in case of modification
	public ActionForward PROFILEOPTIONSNEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.setProfileOptionsNewList(fb, request)) return mapping.findForward("OPTIONS");
		else return mapping.findForward("NEW");
	}

	// save the modified profile
	public ActionForward SAVEPROFILEHEADER(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		validateToken(request,response);
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.saveProfileHeader(fb, request))
		{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			fb.reset();
			GenericPatientProfileUTIL.setEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW, "", "Profile Modified Successfully");
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("NEW");
		}
		else
		{
			return mapping.findForward("FETCHPROFILEDETAILS");
		}
	}

	// generate the profile
	public ActionForward PROFILEGENERATION(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		if (GenericPatientProfileUTIL.profileGeneration(fb, request))
		{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			fb.reset();
			GenericPatientProfileUTIL.setEssentials(fb, request);
			Status objStatus = new Status();
			objStatus.add(Status.NEW, "", "Profile Genrated Successfully");
			WebUTIL.setStatus(request, objStatus);
			return mapping.findForward("NEW");
		}
		else
		{
			return mapping.findForward("OPTIONS");
		}
	}

	public ActionForward CANCEL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		// GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		// GenericPatientProfileUTIL.setPatientAllergiesDetail(fb, request);

		return mapping.findForward("OPTIONS");
	}

	// get the detail of the profile to Automatic Profile Generating
	public ActionForward VIEWAUTOMATICGENRATEDPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{		

		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		//Check Duplicate profile trial Code by Dheeraj
		/*Boolean flag = GenericPatientProfileUTIL.setProfileOptionsList(fb, request);
		System.out.println(flag);
		 if(!flag)
		   {
			 PROFILEOPTIONS(mapping, form, request, response);
				return null;
		   }

		 else
		{*/
		String patCrNo = (String)request.getParameter("patCrNo");
		String profileType = (String)request.getParameter("profileType").replaceAll("\\$","#");
		String deskId = (String)request.getParameter("deskId");
		deskId = "-1";
		String profileGenerationMode = (String)request.getParameter("profileGenerationMode");
		String episodeCode = (String)request.getParameter("episodeCode");
		String episodeVisitNo= (String)request.getParameter("episodeVisitNo");
		String autoProfileCalledFrom= (String)request.getParameter("autoProfileCalledFrom");
		System.out.println("Profile Generation Mode is :"+fb.getProfileGenerationMode()+" episodeCode :"+episodeCode+" episodeVisitNo :"+episodeVisitNo+" autoProfileCalledFrom :"+autoProfileCalledFrom);
		System.out.println("in fun : VIEWAUTOMATICGENRATEDPROFILE  " +deskId);	
		String deskType = (String) request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType==null || deskType.equals(""))
			deskType = DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK;
		fb.setDeskType(deskType);
		GenericPatientProfileUTIL.fetchDetailsForAutomaticGenerate(fb, request, patCrNo, profileType, deskId, profileGenerationMode, episodeCode, episodeVisitNo, autoProfileCalledFrom);
		return mapping.findForward("AUTOMATICGENERATE");
		/*}*/
		 
		 //return this.NEW(mapping, form, request, response);
	}
	
	public ActionForward GENRATEAUTOMATICPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception 
	{
		//validateToken(request,response);
		System.out.println("in GenericPatientProfileACTION class in GENRATEAUTOMATICPROFILE fun");
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		Status objStatus = new Status();
			//Saving Profile
			//Added by Vasu on 14.Aug.2018 for Base64 Decoding
				String base64Data = fb.getProfileHTML();
				String decoded = new String(Base64.decodeBase64(base64Data.getBytes(StandardCharsets.UTF_8))); 
				fb.setProfileHTML(decoded);
			if(GenericPatientProfileUTIL.autometicGenrateProfile(fb, request))	
			{
			//WebUTIL.refreshTransState(request); // Commented By Pragya 2014.12.26 as required by New Desk
			fb.reset();
			GenericPatientProfileUTIL.setEssentials(fb, request);
			objStatus.add(Status.NEW, "", "Profile Saved Successfully");
			WebUTIL.setStatus(request, objStatus);
			}
			return mapping.findForward("NEW");
		
	}

	// View or print the profile
	public ActionForward PRINTPROFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.HTML_TO_PDF_UTIL_HTML_CODE, fb.getProfileHTML());
		// Water Marking
		WebUTIL.setAttributeInSession(request, ServletsUtilityConfig.WATERMARK_PDF_TEXT, "CR No.:" + fb.getPatCrNo());
		return mapping.findForward("AUTOMATICGENERATE");
	}

	public ActionForward AJX_G_SUMMARY(ActionMapping objMapping_p, ActionForm objForm_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p) 
	throws HisException, Exception, SQLException
	{
		StringBuffer strBuff= new StringBuffer();
		strBuff.append("[{summary:\'Patient Profiles Detail\'}]");
		System.out.println("GenericPatientProfileACTION.AJX_G_SUMMARY()   " +strBuff.toString() );
		objResponse_p.setHeader("Cache-Control", "no-cache");
		objResponse_p.getWriter().print(strBuff.toString());
		return null;	
	}
/**
## 		Modification Log		: PATIENTSUMMARY
##		Modify Date				: 16-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/

	// get the detail of the profile to Automatic Profile Generating
	public ActionForward PATIENTSUMMARY(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{		
		GenericPatientProfileFB fb = (GenericPatientProfileFB) form;
		GenericPatientProfileUTIL.setEssentials(fb, request);
		System.out.println("patCrNo:- "+fb.getPatCrNo()+" ProfileType:- "+fb.getProfileType()+" EpisodeCode:- "+fb.getEpisodeCode()+" Episode VisitNo:-"+fb.getEpisodeVisitNo());
		String deskId = "-1";
		String profileType= "10";
		String profileGenerationMode = "3";
		String autoProfileCalledFrom= "0";		
		System.out.println("in fun : VIEWAUTOMATICGENRATEDPROFILE  " +deskId);	
		String deskType = (String) request.getSession().getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
		if(deskType==null || deskType.equals(""))
			deskType = DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK;
		fb.setDeskType(deskType);
		GenericPatientProfileUTIL.fetchDetailsForAutomaticGenerate(fb, request, fb.getPatCrNo(), profileType, deskId, profileGenerationMode, fb.getEpisodeCode(), fb.getEpisodeVisitNo(), autoProfileCalledFrom);
		return mapping.findForward("PATIENTSUMMARY");
	}
}
