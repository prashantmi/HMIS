package new_investigation.transactions.delegate;

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
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import new_investigation.reports.bo.InvTrackingReportBOI;
import new_investigation.transactions.bo.InvestigationEssentialBO;
import new_investigation.transactions.bo.InvestigationEssentialBOi;
import new_investigation.vo.InvTrackingReportVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.SampleAcceptanceVO;




public class InvestigationEssentialDelegate extends Delegate
{
	public InvestigationEssentialDelegate()
	{
		super(new InvestigationEssentialBO());
	}

	public  Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO,String deskType)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO,deskType));
	}
	
	public Map  searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO,HttpServletRequest request)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchLabWiseTestDtls(searchVO,userVO,request));
	}//searchLabWiseTestDtlsRaisingCumCollection
	
	
	public Map  searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO));
	}//searchLaboratoryWiseTestGroupOnClickRaisingCumCollection
	
	//Save Logic
	
	public List  saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO,String priorityAll,HttpSession session)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
		 return serviceBO.saveRequisitionDetails(mp,patVO,_userVO,priorityAll,session);
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
			
			
			public Map  getPrvTestDtlAJAXMAP(String CrNo,UserVO userVO,String fromwhichcall)
			{
				InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
				return(serviceBO.getPrvTestDtlAJAXMAP(CrNo,userVO,fromwhichcall));
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
	}//searchBookMarkRaisingCumCollection
	
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
	 
	
	public Map  deleteReqDtl(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.deleteReqDtl(searchVO,userVO,  lstPatVO));
	}
	
	public int checkBillDtl(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.checkBillDtl(searchVO,userVO,  lstPatVO));
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
	
	public List<String> getStaffDetails(String crNo, UserVO _UserVO)
	{
		InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
		return(serviceBO.getStaffDetails(crNo, _UserVO));
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
		
		//to fetch packing list details for duplicate generation
		public Map getPackingListDetails(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.getPackingListDetails(sampleAcceptanceVO, _UserVO);
		}
		
		
		//raise cum sample collection
		
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
		
		public Map  searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.searchLabWiseTestDtlsRaisingCumCollection(searchVO,userVO));
		}
		

		public Map  searchBookMarkRaisingCumCollection(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.searchBookMarkRaisingCumCollection(searchVO,userVO));
		}
		
		
		public Map  searchLaboratoryWiseTestGroupOnClickRaisingCumCollection(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.searchLaboratoryWiseTestGroupOnClickRaisingCumCollection(searchVO,userVO));
		}
		
		
		public Map  getAptByDeskRaiseCumColl(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getAptByDeskRaiseCumColl(searchVO,userVO));
		}
		
		
		public Map  getAppointmentRaiseCumColl(String reqNo, String crNo,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getAppointmentRaiseCumColl(reqNo,crNo,userVO));
		}
		
		public List  saveAppointmentDetailsRaiseCumColl(List<LabTestVO> mp,LabTestVO patVO,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.saveAppointmentDetailsRaiseCumColl(mp,patVO,_userVO);
		}
		
		//to get tests based on user group codes
		public Map  getGroupCodeWiseDtl(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getGroupCodeWiseDtl(searchVO,userVO));
		}
		
		
		//to get tests based on user group codes
				public Map  getGroupCodeWiseDtlCumColl(InvestigationSearchVO searchVO,UserVO userVO)
				{
					InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
					return(serviceBO.getGroupCodeWiseDtlCumColl(searchVO,userVO));
				}
				
				public Map  getTestsCodeWiseDtlCumColl(InvestigationSearchVO searchVO,UserVO userVO)
				{
					InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
					return(serviceBO.getTestsCodeWiseDtlCumColl(searchVO,userVO));
				}
				
				public List<Inv_SampleCollectionVO> getPatListBarcode(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _userVO)
				 
				{
					InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
					 return serviceBO.getPatListBarcode(objSampleCollectionVO,_userVO);
				}
				
				public String getlabcodesaddendum(String reqno,UserVO userVO)
				 
				{
					InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
					 return serviceBO.getlabcodesaddendum(reqno,userVO);
				}
				
				public String  getBillingCheck(UserVO _userVO)
				{
					InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
					 return serviceBO.getBillingCheck(_userVO);
				}
				
		public String  getStringToAddToRowAJAX(String isTestGroup,LabTestVO voLabTest)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getStringToAddToRowAJAX(isTestGroup,voLabTest));
		}
				
		public String  getRequisitionFormMasterData(String patVO,UserVO _userVO)
		 
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getRequisitionFormMasterData(patVO,_userVO);
		}

		public Map  getPrvTestDtlAJAXMAPP(String CrNo,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getPrvTestDtlAJAXMAPP(CrNo,userVO));
		}	

		public Map  deleteReqDtll(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.deleteReqDtll(searchVO,userVO,  lstPatVO));
		}
		
		

		public List machinelist(UserVO _userVO)
		 
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.machinelist(_userVO);
		}

		public Map  searchLabWiseTestDetailsNEW(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.searchLabWiseTestDtlsNEW(searchVO,userVO));
		}
		
		public String  getBillingChecktestcode(String testcodess,String ward,String req,String cat,UserVO _userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getBillingChecktestcode(testcodess,ward,req,cat,_userVO);
		}

		
		public  String  getcollectionareafromward(String wardcode,String hospitalcode,String seatid)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getcollectionareafromward(wardcode,hospitalcode,seatid));
		}
		
		public String  issufficientbalance(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.issufficientbalance(searchVO,userVO,  lstPatVO));
		}
		
		public String  isduplicatetestraisedtoday(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.isduplicatetestraisedtoday(searchVO,userVO,  lstPatVO));
		}
		
		public String  getgrpcode(String searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getgrpcode(searchVO,userVO));
		}

		
		public  Inv_ictc_VO getictcdetails(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getictcdetails(patVO,userVO));
		}

		
		public String  ispidexist(InvestigationSearchVO searchVO,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.ispidexist(searchVO,userVO));
		}
		
		public String  AJX_IS_LAB_MANDTORY(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.AJX_IS_LAB_MANDTORY(searchVO,userVO,  lstPatVO));
		}
		
		public String  updateappointmentdateinheader(LabTestVO voLabTest,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.updateappointmentdateinheader(voLabTest,userVO));
		}
		
		public Map<String,Map<String,List<String>>>  getBookMarkListraising(UserVO userVO,String iscallingfromdesk,String deskType)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getBookMarkListraising(userVO, iscallingfromdesk,deskType));
		}

		
		public String  getfileuploaddatatestwise(String patVO,UserVO _userVO,String testParaMeterCode)
		 
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getfileuploaddatatestwise(patVO,_userVO,testParaMeterCode);
		}
		
		//added by krishnan nema on 25042019
		public List<Inv_SampleCollectionVO> getPatListSampleColAdvance(Inv_SampleCollectionVO objSampleCollectionVO,UserVO _userVO)
		 
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getPatListSampleColAdvance(objSampleCollectionVO,_userVO);
		}

		public String  getechodata(String reqdno,UserVO _userVO)
		 
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.getechodata(reqdno,_userVO);
		}
		
		public Map  getessentialdetailsforxray(UserVO userVO,Inv_RequisitionRaisingPatientVO patVO,List<Inv_PatientAdmissionDtlVO> patadm)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getessentialdetailsforxray(userVO,patVO,patadm));
		}

		
		public List  savexrayRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO,String priorityAll,HttpSession session)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			 return serviceBO.savexrayRequisitionDetails(mp,patVO,_userVO,priorityAll,session);
		}

		public Map<String,Map<String,List<String>>>  getBookMarkListraisingxray(UserVO userVO,String iscallingfromdesk)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getBookMarkListraisingxray(userVO, iscallingfromdesk));
		}

		
		public String  issufficientbalancexray(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.issufficientbalancexray(searchVO,userVO,  lstPatVO));
		}
		
		public String getlastreqdate(String testcode,String labcode, String crno,UserVO _UserVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getlastreqdate(testcode,labcode,crno,_UserVO));				
		}

		

		public Map<String,LabTestVO>  getPrvTestDtlAJAXMAPxray(String CrNo,UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getPrvTestDtlAJAXMAPxray(CrNo,userVO));
		}	

		

		public Map  deleteReqDtlxray(InvestigationSearchVO searchVO,UserVO userVO,Inv_RequisitionRaisingPatientVO  lstPatVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.deleteReqDtlxray(searchVO,userVO,  lstPatVO));
		}

		
		public Map  getcharge(InvestigationSearchVO searchVO,UserVO userVO,HttpServletRequest req)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getcharge(searchVO,userVO,req));
		}//

		

		public String getsamplebarcodeconfig(UserVO userVO)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.getsamplebarcodeconfig( userVO));
		}

		
		public Map  setchargestestngroup(InvestigationSearchVO searchVO,UserVO userVO,HttpServletRequest req)
		{
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)super.getServiceProvider();
			return(serviceBO.setchargestestngroup(searchVO,userVO,req));
		}//sea
		
		public String AjaxBilledUnbilledDetails(Inv_SampleCollectionVO vo, UserVO userVO) {
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.AjaxBilledUnbilledDetails(vo, userVO);
		}
		
		public String AjaxGetDetails(Inv_SampleCollectionVO vo, UserVO userVO) {
			InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi) super.getServiceProvider();
			return serviceBO.AjaxGetDetails(vo, userVO);
		}
}
