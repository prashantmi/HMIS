/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.investigation.presentation;

import ehr.investigation.vo.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import ehr.investigation.business.EHRSection_InvestigationAdviceBO;
import ehr.investigation.vo.EHRSection_InvestigationAdviceVO;
import ehr.vo.EHRVOUtility;
import ehr.vo.EHR_AccessPermissionVO;
import ehr.vo.EHR_PatientEncounterVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.ProfileInvestigationVO;
import hisglobal.vo.UserVO;

public class EHRSection_InvestigationAdviceDATA extends  ControllerDATA

{		
	
		//Added by VASU on 3-Oct-2017 for Investigation Composition
		public static EHRSection_InvestigationAdviceVO[] getPatientInvestigationDetail(EHR_AccessPermissionVO _voEHRAccess, EHR_PatientEncounterVO _voEHREncounter, EHRSection_InvestigationAdviceVO _voEHRInvestigation)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return (daoDelegate.getPatientInvestigationDetail(_voEHRAccess, _voEHREncounter,  _voEHRInvestigation));
			
		}
		
		public static Map getRequisitionRaisingEssentials(UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getRequisitionRaisingEssentials(userVO);
		}
		
				
		public static EHRSection_InvestigationAdviceVO getPatientRegistration_EpisodeDetailEssentials(EHRSection_InvestigationAdviceVO patVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO);
		}
		
		public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(EHRSection_InvestigationAdviceVO patVO,UserVO _userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getPatientAdmissionDetail(patVO,_userVO);
		}
		public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(EHRSection_InvestigationAdviceVO patVO,UserVO _userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getPatientEpisodeDetail(patVO,_userVO);
		}
	
		
		public static Map<String,Map<String,List<String>>> getBookMarkDetails(UserVO userVO,String deptUnitCode)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getBookMarkDetails(userVO,deptUnitCode);
		}
		
		public static String getAccountNo(EHRSection_InvestigationAdviceVO patVO,UserVO userVO) 
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getAccountNo(patVO,userVO);	
		}
		 
		public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.searchLabWiseTestDtls(searchVO,userVO);
		}
		
		//Requisition Save Logic
		public static List saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,EHRSection_InvestigationAdviceVO patVO,UserVO _userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.saveRequisitionDetails(mp,patVO,_userVO);
		}
		
		public static Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO);
		}
		
		public static Map searchBookMark(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.searchBookMark(searchVO,userVO);
		}
		public static Map getTestsBasedOnGroups(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate = new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getTestsBasedOnGroups(searchVO,userVO);
		}
		
	
		public static String getTestComboAJAX(String labCode,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getTestComboAJAX(labCode,userVO);
		}
		
		public static Map getTestComboAJAXMAP(String labCode,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getTestComboAJAXMAP(labCode,userVO);
		}
		
		public static Map searchTestGroup(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.searchTestGroup(searchVO,userVO);
		}
		
		public static String getTestGroupAJAX(String labCode,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getTestGroupAJAX(labCode,userVO);
		}
		
		public static Map getPrvTestDtlAJAXMAP(String CrNo,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getPrvTestDtlAJAXMAP(CrNo,userVO);
		}
		 
		
		public static Map getAptBasedTest(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getAptBasedTest(searchVO,userVO);
		}
		
		
		public static String getreqStatusAJAX(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getreqStatusAJAX(searchVO,userVO);
		}
		
		public static Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getTestsCodeWiseDtl(searchVO,userVO);
		}
		
		
		public static Map deleteReqDtl(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.deleteReqDtl(searchVO,userVO);
		}
		
		
		
		public static boolean checkRequisitionPending(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.checkRequisitionPending(searchVO, userVO);
		}
		
		//Added by Vasu on 03.May.2019 to get Investigation data into SPP Print
		public static List<EHRSection_InvestigationAdviceVO> getInvestigationAdviceData(UserVO userVO, PatientDetailVO selectedPatientVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			List<EHRSection_InvestigationAdviceVO> lstProfileInvestigationDetailVOs= new ArrayList<EHRSection_InvestigationAdviceVO>();
			lstProfileInvestigationDetailVOs = daoDelegate.getPatientInvestigationDetails(userVO,selectedPatientVO);
			
			
			return lstProfileInvestigationDetailVOs;
		}
		
	}
