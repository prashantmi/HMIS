package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import new_investigation.transactions.delegate.InvestigationEssentialDelegatexray;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;






public class InvestigationRaisingDtlXrayDATA
{
  public static Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getPatientRegistration_EpisodeDetailEssentials(patVO, userVO);
  }

  
  public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.searchLabWiseTestDetails(searchVO, userVO);
  }


  
  public static Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.searchLaboratoryWiseTestGroupOnClick(searchVO, userVO);
  }


  
  public static List saveRequisitionDetails(Map<String, Map<String, LabTestVO>> mp, Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO, String priorityAll, HttpSession session) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.savexrayRequisitionDetails(mp, patVO, _userVO, priorityAll, session);
  }

  
  public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getPatientEpisodeDetail(patVO, _userVO);
  }


  
  public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getPatientAdmissionDetail(patVO, _userVO);
  }


  
  public static Map<String, Map<String, List<String>>> getBookMarkDetails(UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getBookMarkDetails(userVO);
  }

  
  public static Map searchBookMark(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.searchBookMark(searchVO, userVO);
  }

  
  public static Map getRequisitionRaisingEssentials(UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getRequisitionRaisingEssentials(userVO);
  }

  
  public static String getTestComboAJAX(String labCode, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getTestComboAJAX(labCode, userVO);
  }

  
  public static Map getTestComboAJAXMAP(String labCode, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getTestComboAJAXMAP(labCode, userVO);
  }

  
  public static Map searchTestGroup(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.searchTestGroup(searchVO, userVO);
  }

  
  public static String getTestGroupAJAX(String labCode, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getTestGroupAJAX(labCode, userVO);
  }


  
  public static String getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getreqStatusAJAX(searchVO, userVO);
  }
  
  public static Map getTestsBasedOnGroups(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getTestsBasedOnGroups(searchVO, userVO);
  }
  
  public static Map getPrvTestDtlAJAXMAP(String CrNo, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getPrvTestDtlAJAXMAP(CrNo, userVO);
  }


  
  public static Map getAptBasedTest(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getAptBasedTest(searchVO, userVO);
  }
  
  public static String getAccountNo(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getAccountNo(patVO, userVO);
  }

  
  public static Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getTestsCodeWiseDtl(searchVO, userVO);
  }


  
  public static Map deleteReqDtl(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.deleteReqDtlxray(searchVO, userVO, lstPatVO);
  }

  
  public static int checkBillDtl(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.checkBillDtl(searchVO, userVO, lstPatVO);
  }


  
  public static boolean checkRequisitionPending(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.checkRequisitionPending(searchVO, userVO);
  }

  
  public static Map getAptByDesk(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getAptByDesk(searchVO, userVO);
  }

  
  public static Map getAppointment(String reqNo, String crNo, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getAppointment(reqNo, crNo, userVO);
  }

  
  public static List saveAppointmentDetails(List<LabTestVO> mp, LabTestVO patVO, UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.saveAppointmentDetails(mp, patVO, _userVO);
  }

  
  public static Map getAppointmentDetailsOnClickGO(LabTestVO voLabTest, UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getAppointmentDetailsOnClickGO(voLabTest, _userVO);
  }


  
  public static Map getGroupCodeWiseDtl(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getGroupCodeWiseDtl(searchVO, userVO);
  }

  
  public static String getlabcodesaddendum(String reqno, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getlabcodesaddendum(reqno, userVO);
  }

  
  public static String getBillingCheck(UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getBillingCheck(_userVO);
  }

  
  public static String getStringToAddToRowAJAX(String isTestGroup, LabTestVO voLabTest) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getStringToAddToRowAJAX(isTestGroup, voLabTest);
  }

  
  public static String getRequisitionFormMasterData(String patVO, UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getRequisitionFormMasterData(patVO, _userVO);
  }

  
  public static Map getPrvTestDtlAJAXMAPP(String CrNo, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getPrvTestDtlAJAXMAPP(CrNo, userVO);
  }

  
  public static Map deleteReqDtll(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.deleteReqDtll(searchVO, userVO, lstPatVO);
  }


  
  public static Map searchLabWiseTestDetailsNEW(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.searchLabWiseTestDetailsNEW(searchVO, userVO);
  }


  
  public static String getBillingChecktestcode(String testcodess, String ward, String req, String cat, UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getBillingChecktestcode(testcodess, ward, req, cat, _userVO);
  }

  
  public static String issufficientbalancexray(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.issufficientbalancexray(searchVO, userVO, lstPatVO);
  }

  
  public static String isduplicatetestraisedtoday(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.isduplicatetestraisedtoday(searchVO, userVO, lstPatVO);
  }

  
  public static String getgrpcode(String user, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getgrpcode(user, userVO);
  }


  
  public static Inv_ictc_VO getictcdetails(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getictcdetails(patVO, userVO);
  }


  
  public static String ispidexist(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.ispidexist(searchVO, userVO);
  }


  
  public static String AJX_IS_LAB_MANDTORY(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.AJX_IS_LAB_MANDTORY(searchVO, userVO, lstPatVO);
  }

  
  public static String updateappointmentdateinheader(LabTestVO voLabTest, UserVO userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.updateappointmentdateinheader(voLabTest, userVO);
  }

  
  public static Map<String, Map<String, List<String>>> getBookMarkListraising(UserVO userVO, String iscallingfromdesk) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getBookMarkListraisingxray(userVO, iscallingfromdesk);
  }


  
  public static String getfileuploaddatatestwise(String patVO, UserVO _userVO, String testParaMeterCode) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getfileuploaddatatestwise(patVO, _userVO, testParaMeterCode);
  }

  
  public static String getechodata(String reqdno, UserVO _userVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getechodata(reqdno, _userVO);
  }


  
  public static Map getessentialdetailsforxray(UserVO userVO, Inv_RequisitionRaisingPatientVO patVO, List<Inv_PatientAdmissionDtlVO> patadm) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getessentialdetailsforxray(userVO, patVO, patadm);
  }


  
  public static Map<String, LabTestVO> getPrvTestDtlAJAXMAPxray(String CrNo, UserVO userVO, String fromwhichcall) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getPrvTestDtlAJAXMAPxray(CrNo, userVO, fromwhichcall);
  }

  
  public static String issufficientbalance(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.issufficientbalance(searchVO, userVO, lstPatVO);
  }


  
  public static String getlastreqdate(String testcode, String labcode, String crno, UserVO _UserVO) {
    InvestigationEssentialDelegatexray daoDelegate = new InvestigationEssentialDelegatexray();
    return daoDelegate.getlastreqdate(testcode, labcode, crno, _UserVO);
  }
}
