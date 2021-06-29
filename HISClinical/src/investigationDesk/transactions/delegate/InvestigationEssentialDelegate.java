package investigationDesk.transactions.delegate;

/**
 * @author C-DAC, Noida Project : HISInvestigationG5 Module 
 * @Date 20 Aug, 2008 
 * Process: Donor Registration
 * Modified By: Pawan Kumar B N
 * Modified On: 18-11-2011
 * 
 */
import hisglobal.business.Delegate;
import hisglobal.vo.BloodBagDtlVO;
import hisglobal.vo.BloodBankExaminationMstVO;
import hisglobal.vo.BloodDonationVO;
import hisglobal.vo.BloodRequisitionPatientDtlVO;
import hisglobal.vo.DonorAddDtlVO;
import hisglobal.vo.DonorQuestionnaireMasterVO;
import hisglobal.vo.DonorRefreshmentDetailVO;
import hisglobal.vo.DonorVisitDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.Inv_SampleCollectionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import investigationDesk.transactions.bo.InvestigationEssentialBO;
import investigationDesk.transactions.bo.InvestigationEssentialBOi;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;
import investigationDesk.vo.SampleAcceptanceVO;




public class InvestigationEssentialDelegate extends Delegate
{
	public InvestigationEssentialDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	public  Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO));
	}
	
	public Map  searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchLabWiseTestDtls(searchVO,userVO));
	}
	
	
	public Map  searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO));
	}
	
	//Save Logic
	
	public List  saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.saveRequisitionDetails(mp,patVO,_userVO);
	}
	
	public List<Inv_EpisodeVO>  getPatientEpisodeDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getPatientEpisodeDetail(patVO,_userVO);
	}
	
	public List<Inv_PatientAdmissionDtlVO>  getPatientAdmissionDetail(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getPatientAdmissionDetail(patVO,_userVO);
	}
	
	public List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _userVO,String wardCode)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getSampleCollectionArea(_userVO,wardCode);
	}
	
	
	public List<Inv_SampleCollectionVO> getSampleCollectionAreaWard(UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getSampleCollectionAreaWard(_userVO);
	}
	
	
	public List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getPatList(objSampleCollectionVO,_userVO);
	}
	
	
	//Essentials
		public Map  getRequisitionRaisingEssentials(UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getRequisitionRaisingEssentials(userVO));
		}
	
	//AJAX est Combo using labCode
			public String  getTestComboAJAX(String labCode,UserVO userVO)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getTestComboAJAX(labCode,userVO));
			}
			
			
			public Map  getTestComboAJAXMAP(String labCode,UserVO userVO)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getTestComboAJAXMAP(labCode,userVO));
			}
			
			
			public Map  getPrvTestDtlAJAXMAP(String CrNo,UserVO userVO)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getPrvTestDtlAJAXMAP(CrNo,userVO));
			}	
			
			//////////////////////////////////req data//////////////////////////////////////////////////////////
			public Map  getReqData(String CrNo,UserVO userVO)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getReqData(CrNo,userVO));
			}	
			
	
	//BookMark
	public Map<String,Map<String,List<String>>>  getBookMarkDetails(UserVO userVO,String deptUnitCode)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getBookMarkDetails(userVO,deptUnitCode));
	}
	
	public Map  searchBookMark(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchBookMark(searchVO,userVO));
	}
	
	public Map  searchTestGroup(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchTestGroup(searchVO,userVO));
	}
	
	public Map  getTestsBasedOnGroups(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getTestsBasedOnGroup(searchVO,userVO));
	}
	
	 
	//AJAX est Combo using labCode
			public String  getTestGroupAJAX(String labCode,UserVO userVO)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getTestGroupAJAX(labCode,userVO));
			}
	
	//Sample Collection Process
	
	//Getting Billed/Unbilled Patient List
	public Map  getBilledPatList(List<String> reqList,Inv_SampleCollectionVO voSample,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getBilledPatientList(reqList,voSample,userVO));
	}
	
	//Save Logic
	//Sample Collection Process
	public List  saveSampleCollectionDetails(Map<String,Map<String,Map<String,List<Inv_SampleCollectionVO>>>> mp,UserVO _userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.saveSampleCollectionDetails(mp,_userVO);
	}
	
	//Duplicacy Check
		public boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.checkSampleNoDuplicacy(voSample, userVO));
		}
		
		
		//Auto Gen Check For Sample Collection Process
		public String checkAutoGenFormate(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.checkAutoGenFormate(voSample, userVO));
		}
		
	// Packing List Generation Process
	// Getting Packing List Generation Essentials
		//Getting Billed/Unbilled Patient List
		public Map  getPackingListGenerationEssentials(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getPackingListGenerationEssentials(voSample,userVO));
		}
		
		public Map  getPackingListGenerationOnLoad(Inv_SampleCollectionVO voSample,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getPackingListGenerationOnLoad(voSample,userVO));
		}
		
		// Get Packing List Generation Patient List getPackingListGenerationOnLoad
		public List<Inv_SampleCollectionVO> getPackingListGenerationPatList(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _userVO)
		 
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getPackingListPatList(objSampleCollectionVO,_userVO);
		}
		
		//Save Logic
		//Packing List Process
		public Map<String,List<Inv_SampleCollectionVO>>  savePackingListDetails(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.savePackingListDetails(mp, _userVO);
		}
		
		//Save Logic
		//Packing List Process
		public Map<String,List<Inv_SampleCollectionVO>>  generateDuplicatePackingList(Map<String,List<Inv_SampleCollectionVO>> mp,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.generateDuplicatePackingList(mp, _userVO);
		}
		
		public Map  getAptBasedTest(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getAptBasedTest(searchVO,userVO));
		}
		public String getAccountNo(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getAccountNo(patVO,userVO));
		}
		
		
		public List  saveRequisitionCollectionDetails(Map<String,Map<String,List<LabTestVO>>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveRequisitionCollectionDetails(mp,patVO,_userVO);
		}
		
		public List  insertRequisitionCollectionDetails(Map<String,Map<String,List<LabTestVO>>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.insertRaisingCumSelectionInReqDtl(mp,patVO,_userVO);
		}


		public Map  getTestsCodeWiseDtl(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getTestsCodeWiseDtl(searchVO,userVO));
		}
		
		
		public Map  deleteReqDtl(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.deleteReqDtl(searchVO,userVO));
		}
		
		
		public String  getreqStatusAJAX(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getreqStatusAJAX(searchVO,userVO));
		}

//Sample Collection Process 
		
		public boolean checkRequisitionPending(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.checkRequisitionPending(searchVO, userVO));
		}
		
		//to fetch packing list details for duplicate generation
		public Map getPackingListDetails(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.getPackingListDetails(sampleAcceptanceVO, _UserVO);
		}

		
		public Map  searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.searchLabWiseTestDtlsRaisingCumCollection(searchVO,userVO));
		}
		
		
		//Essentials
		public Map  getRequisitionRaisingEssentialsCumColl(UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getRequisitionRaisingEssentialsCumColl(userVO));
		}
		
		public Map  searchLaboratoryWiseTestGroupOnClickCumColl(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.searchLaboratoryWiseTestGroupOnClickCumColl(searchVO,userVO));
		}
		
	
}
