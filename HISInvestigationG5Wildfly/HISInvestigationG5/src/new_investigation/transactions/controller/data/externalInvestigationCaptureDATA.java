package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import new_investigation.transactions.delegate.externalInvestigationCaptureDelegate;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.externalInvestigationCaptureVO;


public class externalInvestigationCaptureDATA
{
	//using for external capture
	public static Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO);
	}
	
	public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.searchLabWiseTestDetails(searchVO,userVO);
	}
	
	
	public static Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO);
	}
	
	//Requisition Save Logic for external capture 
	public static List saveRequisitionDetails(List<externalInvestigationCaptureVO> lstExternalCapture,UserVO _userVO)
	{
		externalInvestigationCaptureDelegate daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.saveRequisitionDetails(lstExternalCapture,_userVO);
	}
	 
	public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		externalInvestigationCaptureDelegate daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getPatientEpisodeDetail(patVO,_userVO);
	}
	
	 
	public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		externalInvestigationCaptureDelegate daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getPatientAdmissionDetail(patVO,_userVO);
	}
	
		
	public static Map<String,Map<String,List<String>>> getBookMarkDetails(UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getBookMarkDetails(userVO);
	}
	
	public static Map searchBookMark(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.searchBookMark(searchVO,userVO);
	}
	
	public static Map getRequisitionRaisingEssentials(UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getRequisitionRaisingEssentials(userVO);
	}
	
	public static String getTestComboAJAX(String labCode,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getTestComboAJAX(labCode,userVO);
	}
	
	public static Map getTestComboAJAXMAP(String labCode,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getTestComboAJAXMAP(labCode,userVO);
	}
	
	public static Map searchTestGroup(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.searchTestGroup(searchVO,userVO);
	}
	
	public static String getTestGroupAJAX(String labCode,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getTestGroupAJAX(labCode,userVO);
	}
	
	
	public static String getreqStatusAJAX(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getreqStatusAJAX(searchVO,userVO);
	}
	public static Map getTestsBasedOnGroups(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getTestsBasedOnGroups(searchVO,userVO);
	}
	public static Map getPrvTestDtlAJAXMAP(String CrNo,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getPrvTestDtlAJAXMAP(CrNo,userVO);
	}
	 
	
	public static Map getAptBasedTest(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getAptBasedTest(searchVO,userVO);
	}
	public static String getAccountNo(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO) 
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getAccountNo(patVO,userVO);	
	}
	
	public static Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getTestsCodeWiseDtl(searchVO,userVO);
	}
	
	
	public static Map deleteReqDtl(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.deleteReqDtl(searchVO,userVO);
	}
	
	
	
	public static boolean checkRequisitionPending(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.checkRequisitionPending(searchVO, userVO);
	}
	
	public static Map getAptByDesk(InvestigationSearchVO searchVO,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getAptByDesk(searchVO,userVO);
	}
	
	public static Map getAppointment(String reqNo,String crNo,UserVO userVO)
	{
		externalInvestigationCaptureDelegate   daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getAppointment(reqNo,crNo,userVO);
	}
	
	public static List saveAppointmentDetails(List<LabTestVO> mp,LabTestVO patVO,UserVO _userVO)
	{
		externalInvestigationCaptureDelegate daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.saveAppointmentDetails(mp,patVO,_userVO);
	}
	
	public static Map getAppointmentDetailsOnClickGO(LabTestVO voLabTest,UserVO _userVO)
	{
		externalInvestigationCaptureDelegate daoDelegate=new externalInvestigationCaptureDelegate();
		return daoDelegate.getAppointmentDetailsOnClickGO(voLabTest,_userVO);
	}
	
}
