package ehr.vo;

import java.util.List;

import hisglobal.utility.HelperMethods;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import ehr.ImageExam.EHRSection_ImageExamVO;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.casesummary.vo.EHRSection_CaseSummaryVO;
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;
import ehr.complaints.vo.EHRSection_ComplaintsVO;
import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.examination.vo.EHRSection_ExaminationVO;
import ehr.followup.vo.EHRSection_FollowupVO;
import ehr.history.vo.EHRSection_HistoryVO;
import ehr.hospitalcourse.vo.EHRSection_HospitalCourseVO;
import ehr.investigation.vo.EHRSection_InvestigationAdviceVO;
import ehr.medicationAdvice.vo.EHRSection_Medication_AdviceVO;
import ehr.ot.vo.EHRSection_OTDetailsVO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import ehr.questionnaire.vo.EHRSection_QuestionnaireVO;
import ehr.serviceprocedure.vo.EHRSection_ServiceProcedureVO;
import ehr.statusdischarge.vo.EHRSection_StatusAtDischargeVO;
import ehr.treatmentdetail.vo.EHRSection_TreatmentVO;
import ehr.visitreason.vo.EHRSection_VisitReasonVO;
//import ehr.vitals.vo.EHRSection_VitalVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ehr.EHRConfig;
import emr.vo.EHR_PatEncounterDetailsVO;
import emr.vo.PatientClinicalDocDetailVO;
//import hisglobal.utility.HttpServletRequest;

public class EHRVOUtility
{
    public static boolean setEHRVO(HttpServletRequest objRequest, String strPatCRNo)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		HttpSession objSession = objRequest.getSession();
    		voEHR = (EHRVO) objSession.getAttribute(EHRConfig.EHR_OBJECT_VO);
    		if(voEHR==null || !voEHR.getPatCrNo().equals(strPatCRNo))
    		{
    			voEHR = new EHRVO();
    			voEHR.setPatCrNo(strPatCRNo);
    			objSession.setAttribute(EHRConfig.EHR_OBJECT_VO, voEHR);
    		}
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;
    }
    
    public static EHRVO getEHRVO(HttpServletRequest objRequest, String strPatCRNo)
    {
    	EHRVO voEHR = null;
    	try
    	{
    		HttpSession objSession = objRequest.getSession();
    		voEHR = (EHRVO) objSession.getAttribute(EHRConfig.EHR_OBJECT_VO);
    		if(voEHR==null || !voEHR.getPatCrNo().equals(strPatCRNo))
    		{
    			if(!setEHRVO(objRequest, strPatCRNo))
    				voEHR = null;
    			else
    				voEHR = (EHRVO) objSession.getAttribute(EHRConfig.EHR_OBJECT_VO);
    		}
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
		}
        return voEHR;
    }

    public static boolean setPatientDetailVO(HttpServletRequest objRequest, String strPatCRNo, PatientDetailVO voPatientDtl)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setVoPatientDtl(voPatientDtl);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }

    public static boolean populateEmptyPatientDetailVO(HttpServletRequest objRequest, String strPatCRNo, PatientDetailVO _voPatientDtl)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    		{
    			PatientDetailVO voPatDtl = voEHR.getVoPatientDtl();
    			if(voPatDtl==null)
    				setPatientDetailVO(objRequest,strPatCRNo,_voPatientDtl);
    			else
    			{
    				HelperMethods.populatetToNullOrEmpty(voPatDtl, _voPatientDtl);
    			}
    		}
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setHospitalVO(HttpServletRequest objRequest, String strPatCRNo, HospitalMstVO voHospital)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setVoHospital(voHospital);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setDiagnosisDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_DiagnosisVO> voDiagnosis)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListDiagnosisVO(voDiagnosis);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setTreatmentDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_TreatmentVO> voTreatment)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListTreatmentVO(voTreatment);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setFollowUpDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_FollowupVO> voFollowUp)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListFollowUpVO(voFollowUp);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setInvestigationDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_InvestigationAdviceVO> voInvestigation)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListInvestigationResultsVO(voInvestigation);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setEpisodeDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<EpisodeVO> voEpisode)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListEpisodeVO(voEpisode);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setClinicalDocDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<PatientClinicalDocDetailVO> voClinicalDoc)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListClinicalDocEssentials(voClinicalDoc);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setDrugAdviceVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_TreatmentVO> voTreatment)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListDrugAdviceVO(voTreatment);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setCheifComplaintsDetailVO(HttpServletRequest objRequest, String strPatCRNo, EHRSection_VisitReasonVO voCheifComplaintsDtl)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setCheifComplaintsVO(voCheifComplaintsDtl);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setHospitalCourseVO(HttpServletRequest objRequest, String strPatCRNo, EHRSection_HospitalCourseVO voHospitalCourse)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setHospitalCourseVO(voHospitalCourse);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }

    
    public static boolean setDischargeFollowUpVO(HttpServletRequest objRequest, String strPatCRNo, EHRSection_FollowupVO voFollowUp)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setFollowUpVO(voFollowUp);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
   //Added by Prachi on 03-05-2019
    public static boolean setOPDFollowUpVO(HttpServletRequest objRequest, String strPatCRNo, EHRSection_FollowupVO voFollowUp_OPD)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setFollowUpVO(voFollowUp_OPD);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    //end
    
    public static boolean setOTDetailsVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_OTDetailsVO> voOTDtl)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListOTDetailVO(voOTDtl);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setCaseSummaryVO(HttpServletRequest objRequest, String strPatCRNo, EHRSection_CaseSummaryVO voCaseSummary)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setCaseSummaryVO(voCaseSummary);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setmedicationVO(HttpServletRequest objRequest, String strPatCRNo, EHRSection_Medication_AdviceVO voMedication)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setMedicationVO(voMedication);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    public static boolean setStatusAtDischargeVO(HttpServletRequest objRequest, String strPatCRNo, EHRSection_StatusAtDischargeVO  statusAtDischargeVO)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setStatusAtDischargeVO(statusAtDischargeVO);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    //Added by Vasu on 06.May.19
    public static boolean setOPDINVDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_InvestigationAdviceVO> voInv)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListOPDINVDtl(voInv);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setChronicDiseaseVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_ChronicDiseaseVO> voChronicDisease)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListChronicDiseaseVO(voChronicDisease);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setAllergiesVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_AllergiesVO> voAllergies)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListAllergies(voAllergies);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setPatientReferralVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_PatientReferralVO> voPatientReferral)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListPatReferrals(voPatientReferral);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setPatientServiceProcedureVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_ServiceProcedureVO> voServiceProcedure)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setListServiceProcedures(voServiceProcedure);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setPatHistory(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_HistoryVO> voPatHistory)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setVoPatHistory(voPatHistory);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setEncExamination(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_ExaminationVO> voEncExam)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setVoEncExamination(voEncExam);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setPatComplaints(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_ComplaintsVO> voComplaints)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setVoComplaints(voComplaints);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    //Added By shweta
   /* public static boolean setEncVital(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_VitalVO> voEncVital)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setVoEncVital(voEncVital);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }*/
    
    public static boolean setPatQuestionnaire(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_QuestionnaireVO> voQuestionnaire)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setVoQuestionnaire(voQuestionnaire);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
    public static boolean setImageExamDetailVO(HttpServletRequest objRequest, String strPatCRNo, List<EHRSection_ImageExamVO> voImage)
    {
    	boolean flg = true;
    	EHRVO voEHR = null;
    	try
    	{
    		voEHR = getEHRVO(objRequest, strPatCRNo);
    		if(voEHR!=null)
    			voEHR.setImageVO(voImage);
    		else
    			flg = false;
		} 
    	catch (Exception e)
		{
			e.printStackTrace();
			voEHR = null;
			flg = false;
		}
        return flg;    	
    }
    
}//End of Class
