package new_investigation.transactions.delegate;


import hisglobal.business.Delegate;
import hisglobal.vo.BloodBagDtlVO;
import hisglobal.vo.BloodBankExaminationMstVO;
import hisglobal.vo.BloodDonationVO;
import hisglobal.vo.BloodRequisitionPatientDtlVO;
import hisglobal.vo.DonorAddDtlVO;
import hisglobal.vo.DonorQuestionnaireMasterVO;
import hisglobal.vo.DonorRefreshmentDetailVO;
import hisglobal.vo.DonorVisitDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.externalInvestigationCaptureVO;




public class externalInvestigationCaptureDelegate extends Delegate
{
	public externalInvestigationCaptureDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	//using for external capture 
	public  Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO));
	}
	
	public Map  searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.external_searchLabWiseTestDtls(searchVO,userVO));
	}
	
	
	public Map  searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO));
	}
	
	//Save Logic for external capture
	
	public List  saveRequisitionDetails(List<externalInvestigationCaptureVO> lstExternalCapture,UserVO _userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.external_saveRequisitionDetails(lstExternalCapture ,_userVO);
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
	
	public List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _userVO)
	 
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.getSampleCollectionArea(_userVO);
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
			
	
	//BookMark
	public Map<String,Map<String,List<String>>>  getBookMarkDetails(UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getBookMarkDetails(userVO));
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
	
	//AJAX est Combo using labCode
			public String  getTestGroupAJAX(String labCode,UserVO userVO)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getTestGroupAJAX(labCode,userVO));
			}
			
			
			public String  getreqStatusAJAX(InvestigationSearchVO searchVO,UserVO userVO)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getreqStatusAJAX(searchVO,userVO));
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
		
		
		
		public boolean checkRequisitionPending(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.checkRequisitionPending(searchVO, userVO));
		}
		
		public Map  getAptByDesk(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getAptByDesk(searchVO,userVO));
		}
		
		public Map  getAppointment(String reqNo, String crNo,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getAppointment(reqNo,crNo,userVO));
		}
		
		public List  saveAppointmentDetails(List<LabTestVO> mp,LabTestVO patVO,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveAppointmentDetails(mp,patVO,_userVO);
		}
		
		
		public Map  getAppointmentDetailsOnClickGO(LabTestVO voLabTest,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getAppointmentDetailsOnClickGO(voLabTest,_userVO);
		}
		
		
}
