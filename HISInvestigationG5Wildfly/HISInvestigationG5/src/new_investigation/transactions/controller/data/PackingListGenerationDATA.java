package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.delegate.InvestigationEssentialDelegate;
import new_investigation.transactions.delegate.SampleAcceptanceDelegate;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.SampleAcceptanceVO;


public class PackingListGenerationDATA
{
	public static List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getSampleCollectionArea(userVO);
	}
	
	public static List<Inv_SampleCollectionVO> getSampleCollectionAreaNew(Inv_SampleCollectionVO objSampleVO,UserVO userVO)
	{
		
		InvestigationEssentialBO   dao=new InvestigationEssentialBO();
		return dao.getSampleCollectionAreaSeatBased( objSampleVO, userVO);
	}
	
	public static List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO, UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getPackingListGenerationPatList(objSampleCollectionVO,userVO);
	}
	/*
	 * public static Map searchLabWiseTestDetails(InvestigationSearchVO
	 * searchVO,UserVO userVO) { InvestigationEssentialDelegate daoDelegate=new
	 * InvestigationEssentialDelegate(); return
	 * daoDelegate.searchLabWiseTestDetails(searchVO,userVO); }
	 */
	
	//Packing List Generation Save Logic
	public static Map<String,List<Inv_SampleCollectionVO>> savePackingListDetails(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO)
	{
		InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.savePackingListDetails(mp,_userVO);
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
		
		public static Map getPackingListGenerationEssentials(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPackingListGenerationEssentials(voSample,userVO);
		}
		
		
		public static Map getPackingListGenerationOnLoad(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.getPackingListGenerationOnLoad(voSample,userVO);
		}
		
		//Packing List Generation Save Logic getPackingListGenerationOnLoad
		public static Map<String,List<Inv_SampleCollectionVO>> generateDuplicatePackingList(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO)
		{
			InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.generateDuplicatePackingList(mp,_userVO);
		}
		
		//to fetch the packing list details for duplicate generation
		public static Map getPackingListDetails(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
		{
			InvestigationEssentialDelegate masterDelegate = new InvestigationEssentialDelegate();
			return masterDelegate.getPackingListDetails(sampleAcceptanceVO, _UserVO);
		}

		
		public static List machinelist(UserVO userVO)
		{
			InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
			return daoDelegate.machinelist(userVO);
		}
}
