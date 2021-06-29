package new_investigation.transactions.dao;

import hisglobal.vo.UserVO;
import java.util.List;
import new_investigation.vo.BookMarkVO;
import new_investigation.vo.Inv_EpisodeVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.InvestigationRequistionVO;
import new_investigation.vo.InvestigationSearchVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.RequistionHeaderVO;

public interface InvestigationEssentialDAOi {
  Inv_RequisitionRaisingPatientVO getInvRaisingPatDetails(Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO,String desktype);
  
  List<LabTestVO> searchLabWiseTestDtls(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  String generateRequisitionNoSequence(String paramString, UserVO paramUserVO);
  
  void insertRequisitionSequenceInMaintainer(String paramString1, String paramString2, String paramString3, UserVO paramUserVO);
  
  void updateRequisitionSequenceInMaintainer(String paramString1, String paramString2, UserVO paramUserVO);
  
  void insertRequisitionDtl(InvestigationRequistionVO paramInvestigationRequistionVO, UserVO paramUserVO, String paramString, RequistionHeaderVO paramRequistionHeaderVO);
  
  List<Inv_EpisodeVO> getPatientEpisode(Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  List<BookMarkVO> getBookMarkList(UserVO paramUserVO);
  
  List<LabTestVO> searchBookMark(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  List getLabNames(UserVO paramUserVO);
  
  List getTestNames(UserVO paramUserVO);
  
  List getTestNamesUsingAJAX(String paramString, UserVO paramUserVO);
  
  List<LabTestVO> searchTestGroup(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  List getTestGroupUsingAJAX(String paramString, UserVO paramUserVO);
  
  List<LabTestVO> getTestsUsingGroup(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
}
