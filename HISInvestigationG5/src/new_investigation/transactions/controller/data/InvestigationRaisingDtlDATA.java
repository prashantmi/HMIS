package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import new_investigation.transactions.delegate.InvestigationEssentialDelegate;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_PatientAdmissionDtlVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_ictc_VO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;






public class InvestigationRaisingDtlDATA
{
  public static Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO,String deskType) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getPatientRegistration_EpisodeDetailEssentials(patVO, userVO,deskType);
  }

  
  public static Map searchLabWiseTestDetails(InvestigationSearchVO searchVO, UserVO userVO,HttpServletRequest request) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.searchLabWiseTestDetails(searchVO, userVO,request);
  }


  
  public static Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.searchLaboratoryWiseTestGroupOnClick(searchVO, userVO);
  }


  
  public static List saveRequisitionDetails(Map<String, Map<String, LabTestVO>> mp, Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO, String priorityAll, HttpSession session) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.saveRequisitionDetails(mp, patVO, _userVO, priorityAll, session);
  }

  
  public static List<Inv_EpisodeVO> getPatientEpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getPatientEpisodeDetail(patVO, _userVO);
  }


  
  public static List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getPatientAdmissionDetail(patVO, _userVO);
  }


  
  public static Map<String, Map<String, List<String>>> getBookMarkDetails(UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getBookMarkDetails(userVO);
  }

  
  public static Map searchBookMark(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.searchBookMark(searchVO, userVO);
  }

  
  public static Map getRequisitionRaisingEssentials(UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getRequisitionRaisingEssentials(userVO);
  }

  
  public static String getTestComboAJAX(String labCode, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getTestComboAJAX(labCode, userVO);
  }

  
  public static Map getTestComboAJAXMAP(String labCode, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getTestComboAJAXMAP(labCode, userVO);
  }

  
  public static Map searchTestGroup(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.searchTestGroup(searchVO, userVO);
  }

  
  public static String getTestGroupAJAX(String labCode, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getTestGroupAJAX(labCode, userVO);
  }


  
  public static String getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getreqStatusAJAX(searchVO, userVO);
  }
  
  public static Map getTestsBasedOnGroups(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getTestsBasedOnGroups(searchVO, userVO);
  }
  
  public static Map getPrvTestDtlAJAXMAP(String CrNo, UserVO userVO, String fromwhichcall) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getPrvTestDtlAJAXMAP(CrNo, userVO, fromwhichcall);
  }


  
  public static Map getAptBasedTest(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getAptBasedTest(searchVO, userVO);
  }
  
  public static String getAccountNo(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getAccountNo(patVO, userVO);
  }

  
  public static Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getTestsCodeWiseDtl(searchVO, userVO);
  }


  
  public static Map deleteReqDtl(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.deleteReqDtl(searchVO, userVO, lstPatVO);
  }

  
  public static int checkBillDtl(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.checkBillDtl(searchVO, userVO, lstPatVO);
  }


  
  public static boolean checkRequisitionPending(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.checkRequisitionPending(searchVO, userVO);
  }

  
  public static Map getAptByDesk(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getAptByDesk(searchVO, userVO);
  }

  
  public static Map getAppointment(String reqNo, String crNo, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getAppointment(reqNo, crNo, userVO);
  }

  
  public static List saveAppointmentDetails(List<LabTestVO> mp, LabTestVO patVO, UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.saveAppointmentDetails(mp, patVO, _userVO);
  }

  
  public static Map getAppointmentDetailsOnClickGO(LabTestVO voLabTest, UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getAppointmentDetailsOnClickGO(voLabTest, _userVO);
  }


  
  public static Map getGroupCodeWiseDtl(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getGroupCodeWiseDtl(searchVO, userVO);
  }

  
  public static String getlabcodesaddendum(String reqno, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getlabcodesaddendum(reqno, userVO);
  }

  
  public static String getBillingCheck(UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getBillingCheck(_userVO);
  }

  
  public static String getStringToAddToRowAJAX(String isTestGroup, LabTestVO voLabTest) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getStringToAddToRowAJAX(isTestGroup, voLabTest);
  }

  
  public static String getRequisitionFormMasterData(String patVO, UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getRequisitionFormMasterData(patVO, _userVO);
  }

  
  public static Map getPrvTestDtlAJAXMAPP(String CrNo, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getPrvTestDtlAJAXMAPP(CrNo, userVO);
  }

  
  public static Map deleteReqDtll(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.deleteReqDtll(searchVO, userVO, lstPatVO);
  }


  
  public static Map searchLabWiseTestDetailsNEW(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.searchLabWiseTestDetailsNEW(searchVO, userVO);
  }


  
  public static String getBillingChecktestcode(String testcodess, String ward, String req, String cat, UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getBillingChecktestcode(testcodess, ward, req, cat, _userVO);
  }

  
  public static String issufficientbalance(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.issufficientbalance(searchVO, userVO, lstPatVO);
  }

  
  public static String isduplicatetestraisedtoday(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.isduplicatetestraisedtoday(searchVO, userVO, lstPatVO);
  }

  
  public static String getgrpcode(String user, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getgrpcode(user, userVO);
  }


  
  public static Inv_ictc_VO getictcdetails(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getictcdetails(patVO, userVO);
  }


  
  public static String ispidexist(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.ispidexist(searchVO, userVO);
  }


  
  public static String AJX_IS_LAB_MANDTORY(InvestigationSearchVO searchVO, UserVO userVO, Inv_RequisitionRaisingPatientVO lstPatVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.AJX_IS_LAB_MANDTORY(searchVO, userVO, lstPatVO);
  }

  
  public static String updateappointmentdateinheader(LabTestVO voLabTest, UserVO userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.updateappointmentdateinheader(voLabTest, userVO);
  }

  
  public static Map<String, Map<String, List<String>>> getBookMarkListraising(UserVO userVO, String iscallingfromdesk,String deskType) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getBookMarkListraising(userVO, iscallingfromdesk,deskType);
  }


  
  public static String getfileuploaddatatestwise(String patVO, UserVO _userVO, String testParaMeterCode) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getfileuploaddatatestwise(patVO, _userVO, testParaMeterCode);
  }

  
  public static String getechodata(String reqdno, UserVO _userVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getechodata(reqdno, _userVO);
  }


  
  public static String getlastreqdate(String testcode, String labcode, String crno, UserVO _UserVO) {
    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
    return daoDelegate.getlastreqdate(testcode, labcode, crno, _UserVO);
  }
  
  
  public static Map getcharge(InvestigationSearchVO searchVO, UserVO userVO,HttpServletRequest req) {
	    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
	    return daoDelegate.getcharge(searchVO, userVO,req);
	  }


  
  public static Map setchargestestngroup(InvestigationSearchVO searchVO, UserVO userVO,HttpServletRequest req) {
	    InvestigationEssentialDelegate daoDelegate = new InvestigationEssentialDelegate();
	    return daoDelegate.setchargestestngroup(searchVO, userVO,req);
	  }

  
  
  
}
