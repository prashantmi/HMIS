/**
##		Date					: 27-02-2017
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Manisha Gangwar
*/


package ehr.investigation.dataentry;


import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;

import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;



import java.util.List;
import java.util.Map;

import ehr.diagnosis.vo.EHRSection_DiagnosisVO;
import ehr.investigation.vo.EHRSection_InvestigationAdviceVO;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public class EHRSection_InvestigationAdviceDATA extends  ControllerDATA

{	
	
		
	
		
		public static EHRSection_InvestigationAdviceVO getPatientRegistration_EpisodeDetailEssentials(EHRSection_InvestigationAdviceVO patVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO);
		}
		
		public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.searchLabWiseTestDtls(searchVO,userVO);
		}
		
		
		public static Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO);
		}
		
		//Requisition Save Logic
		public static List saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,EHRSection_InvestigationAdviceVO patVO,UserVO _userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.saveRequisitionDetails(mp,patVO,_userVO);
		}
		 
		public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(EHRSection_InvestigationAdviceVO patVO,UserVO _userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getPatientEpisodeDetail(patVO,_userVO);
		}
		
		 
		public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(EHRSection_InvestigationAdviceVO patVO,UserVO _userVO)
		{
			EHRSection_InvestigationAdviceBO daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getPatientAdmissionDetail(patVO,_userVO);
		}
		
			
		public static Map<String,Map<String,List<String>>> getBookMarkDetails(UserVO userVO,String deptUnitCode)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getBookMarkDetails(userVO,deptUnitCode);
		}
		
		public static Map searchBookMark(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.searchBookMark(searchVO,userVO);
		}
		
		public static Map getRequisitionRaisingEssentials(UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getRequisitionRaisingEssentials(userVO);
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
		public static Map getTestsBasedOnGroups(InvestigationSearchVO searchVO,UserVO userVO)
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getTestsBasedOnGroup(searchVO,userVO);
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
		public static String getAccountNo(EHRSection_InvestigationAdviceVO patVO,UserVO userVO) 
		{
			EHRSection_InvestigationAdviceBO   daoDelegate=new EHRSection_InvestigationAdviceBO();
			return daoDelegate.getAccountNo(patVO,userVO);	
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
		
	}
