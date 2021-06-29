/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: ANANT PATEL
 ## Module Name					        : INVESTIGATION
 ## Process/Database Object Name	    :Sample Collection Cum Acceptance Process
 ## Purpose						        :  
 ## Date of Creation					:25-May-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.SampleCollectionCumAcceptanceDelegate;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.SampleCollectionCumAcceptanceVO;
import new_investigation.vo.InvestigationSearchVO;

public class SampleCollectionCumAcceptanceDATA {
	public static List<SampleCollectionCumAcceptanceVO> getSampleCollectionArea(UserVO userVO)
	{
		SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
		return daoDelegate.getSampleCollectionArea(userVO);
	}
	
	public static List<SampleCollectionCumAcceptanceVO> getPatList(SampleCollectionCumAcceptanceVO objSampleCollectionVO, UserVO userVO)
	{
		SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
		return daoDelegate.getPatList(objSampleCollectionVO,userVO);
	}
	public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
		return daoDelegate.searchLabWiseTestDetails(searchVO,userVO);
	}
	
	//Sample Collection Save Logic
	public static List saveSampleCollectionDetails(Map<String,Map<String,Map<String,List<SampleCollectionCumAcceptanceVO>>>> mp,UserVO _userVO)
	{
		SampleCollectionCumAcceptanceDelegate daoDelegate=new SampleCollectionCumAcceptanceDelegate();
		return daoDelegate.saveSampleCollectionDetails(mp,_userVO);
	}
	 
		public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			SampleCollectionCumAcceptanceDelegate daoDelegate=new SampleCollectionCumAcceptanceDelegate();
			return daoDelegate.getPatientEpisodeDetail(patVO,_userVO);
		}
		
		 
		public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			SampleCollectionCumAcceptanceDelegate daoDelegate=new SampleCollectionCumAcceptanceDelegate();
			return daoDelegate.getPatientAdmissionDetail(patVO,_userVO);
		}
		
		public static Map getBilledPatList(List<String> reqList,SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
		{
			SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
			return daoDelegate.getBilledPatList(reqList,voSample,userVO);
		}
		
		public static List<String> getStaffDetails(String crNo, UserVO _UserVO)
		{
			SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
			return daoDelegate.getStaffDetails(crNo, _UserVO);
		}
		
		public static boolean checkSampleNoDuplicacy(SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
		{
			SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
			return daoDelegate.checkSampleNoDuplicacy(voSample, userVO);
		}
		
		public static String checkAutoGenFormate(SampleCollectionCumAcceptanceVO voSample,UserVO userVO)
		{
			SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
			return daoDelegate.checkAutoGenFormate(voSample, userVO);
		}
		public static Map getLabTestMachine(String labcode,UserVO userVO)
		{
			SampleCollectionCumAcceptanceDelegate   daoDelegate=new SampleCollectionCumAcceptanceDelegate();
			return daoDelegate.getLabTestMachine(labcode,userVO);
		}
		
	


}
