package investigationDesk.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import investigationDesk.transactions.delegate.InvestigationEssentialDelegate;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;


public class SampleCollectionDATA
{
	public static List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO userVO,String wardCode)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getSampleCollectionArea(userVO,wardCode);
	}
	
	public static List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO, UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getPatList(objSampleCollectionVO,userVO);
	}
	public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchLabWiseTestDetails(searchVO,userVO);
	}
	
	//Sample Collection Save Logic
	public static List saveSampleCollectionDetails(Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp,UserVO _userVO)
	{
		InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.saveSampleCollectionDetails(mp,_userVO);
	}
	 
		public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPatientEpisodeDetail(patVO,_userVO);
		}
		
		 
		public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPatientAdmissionDetail(patVO,_userVO);
		}
		
		public static Map getBilledPatList(List<String> reqList,Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getBilledPatList(reqList,voSample,userVO);
		}
		
		public static boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.checkSampleNoDuplicacy(voSample, userVO);
		}
	
		
		public static String checkAutoGenFormate(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.checkAutoGenFormate(voSample, userVO);
		}
		
}
