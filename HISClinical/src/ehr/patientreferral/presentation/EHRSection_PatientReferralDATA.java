/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.patientreferral.presentation;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import ehr.patientreferral.business.EHRSection_PatientReferralBO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import emr.vo.PatientClinicalDocDetailVO;

public class EHRSection_PatientReferralDATA extends  ControllerDATA

{		
	
		//Added by Vasu on 17.May.2019
		/*public static void getPatientReferralData(HttpServletRequest request,UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
		{
			EHRSection_PatientReferralBO   daoDelegate=new EHRSection_PatientReferralBO();
			List<EHRSection_PatientReferralVO> lstReferralDetails= new ArrayList<EHRSection_PatientReferralVO>();
			lstReferralDetails = daoDelegate.getPatientRefferalDetails(userVO,selectedPatientVO,clinicalDocVO);		
			
		}
		*/
		public static List<EHRSection_PatientReferralVO> getPatientReferralData(UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
		{
			EHRSection_PatientReferralBO   daoDelegate=new EHRSection_PatientReferralBO();
			List<EHRSection_PatientReferralVO> lstReferralDetails= new ArrayList<EHRSection_PatientReferralVO>();
			lstReferralDetails = daoDelegate.getPatientRefferalDetails(userVO,selectedPatientVO,clinicalDocVO);
			return lstReferralDetails;
		}

		public static Map getReferralEssentialsData(String patCrNo, UserVO userVO)
		{
			EHRSection_PatientReferralBO   daoDelegate=new EHRSection_PatientReferralBO();
			return daoDelegate.getPatientReferralEssentials(patCrNo,userVO);
			
		}	
		
	}
