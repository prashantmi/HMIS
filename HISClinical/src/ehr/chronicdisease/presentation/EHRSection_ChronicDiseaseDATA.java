/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.chronicdisease.presentation;

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

public class EHRSection_ChronicDiseaseDATA extends  ControllerDATA

{

	public static List<EHRSection_ChronicDiseaseVO> getChronicDiseaseData(UserVO userVO, PatientDetailVO selectedPatientVO)
	{
		EHRSection_ChronicDiseaseBO   daoDelegate=new EHRSection_ChronicDiseaseBO();
		List<EHRSection_ChronicDiseaseVO> lstChronicDisease= new ArrayList<EHRSection_ChronicDiseaseVO>();
		lstChronicDisease = daoDelegate.getPatientChronicDiseaseDetails(userVO,selectedPatientVO);
		return lstChronicDisease;
	}		
	//Added by Vasu on 08.May.2019
		
		/*public static List<EHRSection_ChronicDiseaseVO> getChronicDiseaseData(UserVO userVO, PatientDetailVO selectedPatientVO)
		{
			EHRSection_ChronicDiseaseBO   daoDelegate=new EHRSection_ChronicDiseaseBO();
			List<EHRSection_ChronicDiseaseVO> lstChronicDisease= new ArrayList<EHRSection_ChronicDiseaseVO>();
			lstChronicDisease = daoDelegate.getPatientChronicDiseaseDetails(userVO,selectedPatientVO);
			return lstChronicDisease;
		}*/
		
	}
