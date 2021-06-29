package opd.transaction.controller.util;

import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.dynamicdesk.controller.util.DynamicDeskUTIL;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.ConsentRequestVO;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.DeskDetailVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mrd.transaction.bo.EMRPatientDetailBO;
import mrd.vo.EMRPateintDataPolicyVO;
import opd.OpdConfig;
import opd.transaction.controller.data.DoctorDeskDashboardDATA;
import opd.transaction.controller.fb.DoctorDeskFB;
import investigationDesk.vo.viewInvestigationVO;

import org.apache.struts.action.ActionMapping;

import ehr.EHRConfig;
import ehr.history.presentation.EHRSection_HistoryDATA;
import ehr.history.vo.EHRSection_HistoryVO;
import emr.vo.PatientClinicalDocDetailVO;

public class DoctorDeskDashboardUTIL extends ControllerUTIL 
{
	public static StringBuffer AJX_G_PATIENTS_VISIT_SUMMARY(DoctorDeskFB doctorDeskFB, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
	ActionMapping objMapping_p) {
		String strMsgText = "";
		 String dataShouldShow = "1";
		HttpSession objSession = objRequest_p.getSession();
		StringBuffer sbAjaxRes = new StringBuffer();
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		HttpSession session = objRequest_p.getSession();
		PatientDetailVO patientDetailVO = new PatientDetailVO();	

		try
		{
			String episodeCode = doctorDeskFB.getEpisodeCode();
			String episodeVisitNo = doctorDeskFB.getEpisodeVisitNo();
			String patCrNo = doctorDeskFB.getPatCrNo();
			patientDetailVO.setPatCrNo(patCrNo);
			patientDetailVO.setEpisodeVisitNo(episodeVisitNo);
			patientDetailVO.setEpisodeCode(episodeCode);
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getDailyPatientDetail(patientDetailVO, ControllerUTIL.getUserVO(objRequest_p));
			try
			{
				//List<PatientDetailVO> patDetail = (List<PatientDetailVO>)objSession.getAttribute(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
				List<PatientDetailVO> patDetail = (List<PatientDetailVO>) mapEssential.get(OpdConfig.OPD_DESK_SELECTED_PATIENT_VO);
				if(patDetail!=null && patDetail.size()>0)
				{
					sbAjaxRes.append("[");
					for(PatientDetailVO vo : patDetail)
					{
						//commented on date: 15.12.2016 visit summary auto tab opening not working, in case of old department visit 
						//if((vo.getEpisodeVisitNo().equals("1")) && (vo.getIsConfirmed().equals("1")))
						if(vo.getIsConfirmed().equals("1"))	
						{
							dataShouldShow="0";
						}
						sbAjaxRes.append("{");
						sbAjaxRes.append("strHeader");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append("Visit Summary");sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("strIsDataPresent");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append("1");sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("strShowprocessFlag");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(dataShouldShow);sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("}");sbAjaxRes.append(",");
					}
					if(patDetail.size()>0)	sbAjaxRes.delete(sbAjaxRes.length()-1, sbAjaxRes.length());
					sbAjaxRes.append("]");
					System.out.println("JSON Object value is :-"+sbAjaxRes);
				}
			}
			catch (Exception e)
			{
				strMsgText = "DoctorDeskDashboardUTIL.getDailyPatientDetail1() -> " + e.getMessage();
				HisException eObj = new HisException("opd DoctorDesk", "DoctorDeskDashboardUTIL.AJX_G_PATIENTS_VISIT_SUMMARY() --> ", strMsgText);
				eObj = null;
			}
			System.out.println("JSON Object value is :-"+sbAjaxRes);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sbAjaxRes;	
	}

	public static void VISITDETAIL(DoctorDeskFB doctorDeskFB, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
			ActionMapping objMapping_p) {
		String strMsgText = "";
		 String dataShouldShow = "1";
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		PatientDetailVO patientDetailVO = new PatientDetailVO();	

		try
		{
			String episodeCode = doctorDeskFB.getEpisodeCode();
			String episodeVisitNo = doctorDeskFB.getEpisodeVisitNo();
			String patCrNo = doctorDeskFB.getPatCrNo();
			patientDetailVO.setPatCrNo(patCrNo);
			patientDetailVO.setEpisodeVisitNo(episodeVisitNo);
			patientDetailVO.setEpisodeCode(episodeCode);
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getDailyPatientDetail(patientDetailVO, ControllerUTIL.getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mapEssential, objRequest_p);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void DIAGNOSIS_DETAIL(DoctorDeskFB doctorDeskFB, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
			ActionMapping objMapping_p) {
		HttpSession objSession = objRequest_p.getSession();
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		EpisodeDiagnosisVO episodeDiagnosisVO = new EpisodeDiagnosisVO();
		try {
			String episodeCode = doctorDeskFB.getEpisodeCode();
			String episodeVisitNo = doctorDeskFB.getEpisodeVisitNo();
			String patCrNo = doctorDeskFB.getPatCrNo();
			episodeDiagnosisVO.setPatCrNo(patCrNo);
			episodeDiagnosisVO.setEpisodeVisitNo(episodeVisitNo);
			episodeDiagnosisVO.setEpisodeCode(episodeCode);
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getEpisodeDiagnosisDetail(episodeDiagnosisVO, ControllerUTIL.getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mapEssential, objRequest_p);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void TREATMENT_DETAIL(DoctorDeskFB doctorDeskFB,HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
			ActionMapping objMapping_p) {
		HttpSession objSession = objRequest_p.getSession();
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		PatDrugTreatmentDetailVO patDrugTreatmentDetailVO = new PatDrugTreatmentDetailVO();
		try {
			String episodeCode = doctorDeskFB.getEpisodeCode();
			String episodeVisitNo = doctorDeskFB.getEpisodeVisitNo();
			String patCrNo = doctorDeskFB.getPatCrNo();
			patDrugTreatmentDetailVO.setPatCrNo(patCrNo);
			patDrugTreatmentDetailVO.setEpisodeVisitNo(episodeVisitNo);
			patDrugTreatmentDetailVO.setEpisodeCode(episodeCode);
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getEpisodeDrugDetail(patDrugTreatmentDetailVO, ControllerUTIL.getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mapEssential, objRequest_p);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void MEDICAL_HISTORY(DoctorDeskFB doctorDeskFB,HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
			ActionMapping objMapping_p) {
		HttpSession objSession = objRequest_p.getSession();
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		PatientAlertsDetailVO patientAlertsDetailVO = new PatientAlertsDetailVO();
		PatAllergyDtlVO patAllergyDtlVO = new PatAllergyDtlVO();
		try {
			String patCrNo = doctorDeskFB.getPatCrNo();
			patientAlertsDetailVO.setPatCrNo(patCrNo);
			patAllergyDtlVO.setPatCrNo(patCrNo);
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getPatMedicalHistory(patientAlertsDetailVO, patAllergyDtlVO, ControllerUTIL.getUserVO(objRequest_p));
			WebUTIL.setMapInSession(mapEssential, objRequest_p);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void INVESTIGATION_DETAIL(DoctorDeskFB doctorDeskFB,HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,ActionMapping objMapping_p) {
				
				HttpSession objSession = objRequest_p.getSession();
				DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
				viewInvestigationVO patviewInvestigationVO= new viewInvestigationVO();
				try{
					String patCrNo = doctorDeskFB.getPatCrNo();
					patviewInvestigationVO.setPatCrNo(patCrNo);
					Map<String, Object> mapEssential = doctorDeskDashboardDATA.getInvestigationDetail(patviewInvestigationVO, ControllerUTIL.getUserVO(objRequest_p));
					WebUTIL.setMapInSession(mapEssential, objRequest_p);
					
				}
				catch (Exception e) {
					// TODO: handle exception
				}
				
			}

	public static void getDeskPatDtl(DoctorDeskFB objFB_p, HttpServletRequest objRequest_p, HttpServletResponse objResponse_p,
			ActionMapping objMapping_p)
	{
		System.out.println("DoctorDeskDashboardUTIL.getDeskPatDtl()");
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		PatientDetailVO patientDetailVO = new PatientDetailVO();	
		try
		{
			HelperMethods.populate(patientDetailVO, objFB_p);
			Map<String, Object> mapEssential = doctorDeskDashboardDATA.getDeskPatDtl(patientDetailVO, ControllerUTIL.getUserVO(objRequest_p),objFB_p.getDeskType());
			DynamicDeskUTIL.setMapInSession(mapEssential, objRequest_p);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}	

	public static StringBuffer getDeskMenusdetails(DoctorDeskFB objFB, HttpServletRequest objRequest_p,HttpServletResponse objResponse_p) 
	{
		StringBuffer sbAjaxRes = new StringBuffer();
		HttpSession objSession = objRequest_p.getSession();
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		try
		{			
			PatientDetailVO patientDetailVO = new PatientDetailVO();
			HelperMethods.populate(patientDetailVO, objFB);
			
			List<DeskDetailVO> lstMenus_NonPat = (List<DeskDetailVO>)objSession.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_NONPATIENT_CENTRIC_MENU_DTL);
			List<DeskDetailVO> lstMenus_LEFT= (List<DeskDetailVO>)objSession.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_LEFT_MENU_DTL);
			List<DeskDetailVO> lstMenus_RIGHT = (List<DeskDetailVO>)objSession.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_RIGHT_MENU_DTL);
			List<DeskDetailVO> lstMenus_BOTTOM = (List<DeskDetailVO>)objSession.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_BOTTOM_MENU_DTL);
			//List<DeskDetailVO> lstMenus_TOP = (List<DeskDetailVO>)objSession.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TOP_MENU_DTL);

			//--Map<String,Object> mp = new HashMap<String, Object>();
			//--List<Map<String,String>> lstDatamap = new ArrayList<Map<String,String>>();		
			
			sbAjaxRes.append("[");
			
			// For Non-Patient Centric Tabs
			if(lstMenus_NonPat!=null && lstMenus_NonPat.size()>0)
			{					
				for(DeskDetailVO deskDetailVO : lstMenus_NonPat)
				{
					//List<DeskDetailVO> lstDeskMenus = DoctorDeskDashboardDATA.getDeskMenusdetails(deskDetailVO,patientDetailVO,ControllerUTIL.getUserVO(objRequest_p));
					DeskDetailVO voDeskDetail = getDeskMenuStatus(deskDetailVO,patientDetailVO,objRequest_p);
					if(voDeskDetail.getCount()!=null)
					{
						sbAjaxRes.append("{");
						sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("deskMenuURL");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("count");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("isToAddCount");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getIsToAddCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("}");
						sbAjaxRes.append(",");
					}
				}
			}
			// For Non-Patient Centric Tabs
			if(lstMenus_LEFT!=null && lstMenus_LEFT.size()>0)
			{					
				for(DeskDetailVO deskDetailVO : lstMenus_LEFT)
				{
					//List<DeskDetailVO> lstDeskMenus = DoctorDeskDashboardDATA.getDeskMenusdetails(deskDetailVO,patientDetailVO,ControllerUTIL.getUserVO(objRequest_p));
					DeskDetailVO voDeskDetail = getDeskMenuStatus(deskDetailVO,patientDetailVO,objRequest_p);
					if(voDeskDetail.getCount()!=null)
					{
						sbAjaxRes.append("{");
						sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("deskMenuURL");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("count");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("isToAddCount");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getIsToAddCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("}");
						sbAjaxRes.append(",");
					}
				}
			}
			
			if(lstMenus_RIGHT!=null && lstMenus_RIGHT.size()>0)
			{					
				for(DeskDetailVO deskDetailVO : lstMenus_RIGHT)
				{
					//List<DeskDetailVO> lstDeskMenus = DoctorDeskDashboardDATA.getDeskMenusdetails(deskDetailVO,patientDetailVO,ControllerUTIL.getUserVO(objRequest_p));
					DeskDetailVO voDeskDetail = getDeskMenuStatus(deskDetailVO,patientDetailVO,objRequest_p);
					if(voDeskDetail.getCount()!=null)
					{
						sbAjaxRes.append("{");
						sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("deskMenuURL");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("count");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("isToAddCount");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getIsToAddCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("}");
						sbAjaxRes.append(",");
					}
				}
			}
			
			if(lstMenus_BOTTOM!=null && lstMenus_BOTTOM.size()>0)
			{					
				for(DeskDetailVO deskDetailVO : lstMenus_BOTTOM)
				{
					//List<DeskDetailVO> lstDeskMenus = DoctorDeskDashboardDATA.getDeskMenusdetails(deskDetailVO,patientDetailVO,ControllerUTIL.getUserVO(objRequest_p));
					DeskDetailVO voDeskDetail = getDeskMenuStatus(deskDetailVO,patientDetailVO,objRequest_p);
					if(voDeskDetail.getCount()!=null)
					{
						sbAjaxRes.append("{");
						sbAjaxRes.append("deskMenuId");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskMenuId());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("deskMenuURL");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getDeskUrl());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("count");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("isToAddCount");sbAjaxRes.append(":");
						sbAjaxRes.append("\'");sbAjaxRes.append(voDeskDetail.getIsToAddCount());sbAjaxRes.append("\'");sbAjaxRes.append(",");
						sbAjaxRes.append("}");
						sbAjaxRes.append(",");
					}
				}
			}
			sbAjaxRes.append("]");
			System.out.println(sbAjaxRes);
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sbAjaxRes;
	}
	
	private static DeskDetailVO getDeskMenuStatus(DeskDetailVO voDeskDtl_p, PatientDetailVO patientDetailVO_p, HttpServletRequest objRequest_p) 
	{
		HttpSession objSession = objRequest_p.getSession();
		DeskDetailVO voDeskDtl = new DeskDetailVO();
		DoctorDeskDashboardDATA doctorDeskDashboardDATA = new DoctorDeskDashboardDATA();
		try
		{			
			HelperMethods.populate(voDeskDtl, voDeskDtl_p);
			EMRPateintDataPolicyVO voEMRPolicy = new EMRPateintDataPolicyVO();
			UserVO voUser = ControllerUTIL.getUserVO(objRequest_p);
			EMRPatientDetailBO boEMRPat = new EMRPatientDetailBO();
			
			// Consultation Inbox
			if(voDeskDtl.getDeskUrl().equals("CONSULTATIONINBOX"))
			{
				HelperMethods.populate(voEMRPolicy, voDeskDtl_p);
				List<ConsultationDtlVO> lstDeskMenusDtl = boEMRPat.getConsultationInboxDetails(voEMRPolicy, voDeskDtl_p, patientDetailVO_p, voUser);
				System.out.println("Size of CONSULTATIONINBOX List is:- "+lstDeskMenusDtl.size());
				String count = Integer.toString(lstDeskMenusDtl.size());
				System.out.println("count is :- "+count);
				voDeskDtl.setCount(count);
				voDeskDtl.setIsToAddCount("true");
				
			}
			// Consent Inbox
			if(voDeskDtl.getDeskUrl().equals("CONSENTINBOX"))
			{
				HelperMethods.populate(voEMRPolicy, voDeskDtl_p);
				List<ConsentRequestVO> lstDeskMenusDtl = boEMRPat.getConsentInboxDetails(voEMRPolicy, voDeskDtl_p, patientDetailVO_p, voUser);
				System.out.println("Size of CONSENTINBOX List is:- "+lstDeskMenusDtl.size());
				String count = Integer.toString(lstDeskMenusDtl.size());
				System.out.println("count is :- "+count);
				voDeskDtl.setCount(count);
				voDeskDtl.setIsToAddCount("true");
				
			}
		}
		catch (NumberFormatException e)
		{
			e.printStackTrace();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return voDeskDtl;
	}

	//Added by Vasu on 31.Dec.2019 to get Template history data into dashboard
	public static void getEssentialsForTemplateHistory(DoctorDeskFB doctorDeskFB, HttpServletRequest _request,HttpServletResponse objResponse_p, ActionMapping objMapping_p)
	{
		HttpSession session = _request.getSession();
		Status objStatus = new Status();
		String strHtmlCode="";

		try
		{
			Map<String, Object> mpTempParaValues = new HashMap<String, Object>();
			String deskMenuId = "2";//For History
			String deskType = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_TYPE);
			String deskId = (String) session.getAttribute(DynamicDeskConfig.DYNAMIC_DESK_ID);
			
			PatientClinicalDocDetailVO clinicalDocVO= new PatientClinicalDocDetailVO();	
			UserVO userVO = getUserVO(_request);
			PatientDetailVO selectedPatientVO =null;
			selectedPatientVO = (PatientDetailVO) session.getAttribute(DynamicDeskConfig.DESK_SELECTED_PATIENT_DTL_VO);
			//clinicalDocVO = (PatientClinicalDocDetailVO) session.getAttribute(EHRConfig.CLINICAL_DOCUMENT_TYPE_DETAILS);
			clinicalDocVO.setDocumentType("0");
			clinicalDocVO.setClinicalSectionCode("0");
			mpTempParaValues = EHRSection_HistoryDATA.getTemplateClinicalData(userVO,selectedPatientVO,clinicalDocVO,deskMenuId,deskType,deskId);
			 

			List<GenericTemplateUtility.TempParameter> voTempPara  = (List)mpTempParaValues.get("TEMPLATE_CONTENT");

			List<EHRSection_HistoryVO> voHistory = new ArrayList<EHRSection_HistoryVO>();
			
			EHRSection_HistoryVO ehrSecHistoryVo=null;
			if(voTempPara!=null)
			
			for(GenericTemplateUtility.TempParameter voPara : voTempPara)
			{
				 String templateContent=voPara.getTemplateContent();
				 
				 ehrSecHistoryVo= new EHRSection_HistoryVO();
				 
				 ehrSecHistoryVo.setTemplateContent(templateContent);
				 
				 ehrSecHistoryVo.setTemplateName(voPara.getTemplateName());
				 ehrSecHistoryVo.setRecordDate(voPara.getRecordDate());
				 ehrSecHistoryVo.setTemplateContentSummarized(voPara.getTemplateContentSummarized());
				 voHistory.add(ehrSecHistoryVo);
				
			}
			
			_request.getSession().setAttribute(EHRConfig.EPISODE_HISTORY_LIST_FOR_DASHBOARD, voHistory);
					
					
		}
		catch (HisRecordNotFoundException e)
		{
			e.printStackTrace();
			objStatus.add(Status.UNSUCESSFULL, e.getMessage(), "");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			WebUTIL.setStatus(_request, objStatus);
			/*response.getWriter().write(strHtmlCode);*/
		}
		
	}	
}
