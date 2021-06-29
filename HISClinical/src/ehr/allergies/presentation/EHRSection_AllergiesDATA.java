/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.allergies.presentation;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ehr.allergies.business.EHRSection_AllergiesBO;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.chronicdisease.business.EHRSection_ChronicDiseaseBO;
import ehr.chronicdisease.vo.EHRSection_ChronicDiseaseVO;
import ehr.investigation.business.EHRSection_InvestigationAdviceBO;
import ehr.vo.EHRVOUtility;

public class EHRSection_AllergiesDATA extends  ControllerDATA

{		
	
		//Added by Vasu on 07.May.2019 to get Allergies data into SPP Print
		/*public static void getAllergiesData(HttpServletRequest request,UserVO userVO, PatientDetailVO selectedPatientVO)
		{
			EHRSection_AllergiesBO   daoDelegate=new EHRSection_AllergiesBO();
			List<EHRSection_AllergiesVO> lstAllergyDetails= new ArrayList<EHRSection_AllergiesVO>();
			lstAllergyDetails = daoDelegate.getPatientAllergyDetails(userVO,selectedPatientVO);
			
			EHRVOUtility.setAllergiesVO(request, selectedPatientVO.getPatCrNo(), lstAllergyDetails);		
			
		}*/
		
		public static List<EHRSection_AllergiesVO> getAllergiesData(UserVO userVO, PatientDetailVO selectedPatientVO)
		{
			EHRSection_AllergiesBO   daoDelegate=new EHRSection_AllergiesBO();
			List<EHRSection_AllergiesVO> lstAllergies= new ArrayList<EHRSection_AllergiesVO>();
			lstAllergies = daoDelegate.getPatientAllergyDetails(userVO,selectedPatientVO);
			return lstAllergies;
		}		
		
	}
