/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.serviceprocedure.presentation;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import ehr.allergies.business.EHRSection_AllergiesBO;
import ehr.allergies.vo.EHRSection_AllergiesVO;
import ehr.investigation.business.EHRSection_InvestigationAdviceBO;
import ehr.patientreferral.business.EHRSection_PatientReferralBO;
import ehr.patientreferral.vo.EHRSection_PatientReferralVO;
import ehr.serviceprocedure.business.EHRSection_ServiceProcedureBO;
import ehr.serviceprocedure.vo.EHRSection_ServiceProcedureVO;
import ehr.vo.EHRVOUtility;
import emr.vo.PatientClinicalDocDetailVO;

public class EHRSection_ServiceProcedureDATA extends  ControllerDATA

{		
	
		//Added by Vasu on 21.May.2019
		public static List<EHRSection_ServiceProcedureVO> getServiceProcedures(UserVO userVO, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO)
		{
			EHRSection_ServiceProcedureBO   daoDelegate=new EHRSection_ServiceProcedureBO();
			List<EHRSection_ServiceProcedureVO> lstServiceProcedures= new ArrayList<EHRSection_ServiceProcedureVO>();
			lstServiceProcedures = daoDelegate.getServiceProcedures(userVO,selectedPatientVO,clinicalDocVO);
			
			return lstServiceProcedures;
		}
		
	}
