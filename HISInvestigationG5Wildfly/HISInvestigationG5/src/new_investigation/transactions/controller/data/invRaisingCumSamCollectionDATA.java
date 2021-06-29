package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.InvestigationEssentialDelegate;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;


public class invRaisingCumSamCollectionDATA
{
	public static Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO);
	}
	
	public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchLabWiseTestDtlsRaisingCumCollection(searchVO,userVO);
	}
	
	
	public static Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchLaboratoryWiseTestGroupOnClickRaisingCumCollection(searchVO,userVO);
	}
	
	//Requisition Save Logic
	public static List saveRequisitionDetails(Map<String,Map<String,List<LabTestVO>>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.saveRequisitionCollectionDetails(mp,patVO,_userVO);
	}
	
	public static List insertRequisitionDetails(Map<String,Map<String,List<LabTestVO>>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.insertRequisitionCollectionDetails(mp,patVO,_userVO);
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
	
		
	public static Map<String,Map<String,List<String>>> getBookMarkDetails(UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getBookMarkDetails(userVO);
	}
	
	public static Map searchBookMark(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchBookMarkRaisingCumCollection(searchVO,userVO);
	}
	
	public static Map getRequisitionRaisingEssentials(UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getRequisitionRaisingEssentials(userVO);
	}
	
	public static String getTestComboAJAX(String labCode,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getTestComboAJAX(labCode,userVO);
	}
	
	public static Map getTestComboAJAXMAP(String labCode,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getTestComboAJAXMAP(labCode,userVO);
	}
	
	public static Map searchTestGroup(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchTestGroup(searchVO,userVO);
	}
	
	public static String getTestGroupAJAX(String labCode,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getTestGroupAJAX(labCode,userVO);
	}
	public static Map getTestsBasedOnGroups(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getTestsBasedOnGroups(searchVO,userVO);
	}
	public static Map getPrvTestDtlAJAXMAP(String CrNo,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getPrvTestDtlAJAXMAP(CrNo,userVO);
	}
	 
	
	public static Map getAptBasedTest(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getAptBasedTest(searchVO,userVO);
	}
	public static String getAccountNo(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO) 
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getAccountNo(patVO,userVO);	
	}
	
	public static Map getAptByDesk(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getAptByDeskRaiseCumColl(searchVO,userVO);
	}
	
	
	public static Map getAppointment(String reqNo,String crNo,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getAppointmentRaiseCumColl(reqNo,crNo,userVO);
	}
	
	
	public static List saveAppointmentDetails(List<LabTestVO> mp,LabTestVO patVO,UserVO _userVO)
	{
		InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.saveAppointmentDetailsRaiseCumColl(mp,patVO,_userVO);
	}
	
	public static Map getGroupCodeWiseDtl(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getGroupCodeWiseDtlCumColl(searchVO,userVO);
	}
	
	public static Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getTestsCodeWiseDtlCumColl(searchVO,userVO);
	}
	
	
}
