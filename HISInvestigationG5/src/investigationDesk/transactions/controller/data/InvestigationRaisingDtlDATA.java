package investigationDesk.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import investigationDesk.transactions.delegate.InvestigationEssentialDelegate;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;


public class InvestigationRaisingDtlDATA
{
	public static Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getPatientRegistration_EpisodeDetailEssentials(patVO,userVO);
	}
	
	public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchLabWiseTestDetails(searchVO,userVO);
	}
	
	
	public static Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchLaboratoryWiseTestGroupOnClick(searchVO,userVO);
	}
	
	//Requisition Save Logic
	public static List saveRequisitionDetails(Map<String,Map<String,LabTestVO>> mp,Inv_RequisitionRaisingPatientVO patVO,UserVO _userVO)
	{
		InvestigationEssentialDelegate daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.saveRequisitionDetails(mp,patVO,_userVO);
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
	
		
	public static Map<String,Map<String,List<String>>> getBookMarkDetails(UserVO userVO,String deptUnitCode)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getBookMarkDetails(userVO,deptUnitCode);
	}
	
	public static Map searchBookMark(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.searchBookMark(searchVO,userVO);
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
	
	public static String getreqStatusAJAX(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getreqStatusAJAX(searchVO,userVO);
	}
	
	public static Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.getTestsCodeWiseDtl(searchVO,userVO);
	}
	
	
	public static Map deleteReqDtl(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.deleteReqDtl(searchVO,userVO);
	}
	
	
	
	public static boolean checkRequisitionPending(InvestigationSearchVO searchVO,UserVO userVO)
	{
		InvestigationEssentialDelegate   daoDelegate=new InvestigationEssentialDelegate();
		return daoDelegate.checkRequisitionPending(searchVO, userVO);
	}
	
}
