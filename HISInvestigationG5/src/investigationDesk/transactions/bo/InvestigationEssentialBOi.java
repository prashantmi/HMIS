package investigationDesk.transactions.bo;

import hisglobal.vo.UserVO;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;
import investigationDesk.vo.SampleAcceptanceVO;
import java.util.List;
import java.util.Map;

public interface InvestigationEssentialBOi {
  Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  Map searchLabWiseTestDtls(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  List saveRequisitionDetails(Map<String, Map<String, LabTestVO>> paramMap, Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  List<Inv_EpisodeVO> getPatientEpisodeDetail(Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetail(Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  Map<String, Map<String, List<String>>> getBookMarkDetails(UserVO paramUserVO, String paramString);
  
  Map searchBookMark(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO paramUserVO, String paramString);
  
  List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO paramInv_SampleCollectionVO, UserVO paramUserVO);
  
  Map getBilledPatientList(List<String> paramList, Inv_SampleCollectionVO paramInv_SampleCollectionVO, UserVO paramUserVO);
  
  List saveSampleCollectionDetails(Map<String, Map<String, Map<String, List<Inv_SampleCollectionVO>>>> paramMap, UserVO paramUserVO);
  
  boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO paramInv_SampleCollectionVO, UserVO paramUserVO);
  
  String checkAutoGenFormate(Inv_SampleCollectionVO paramInv_SampleCollectionVO, UserVO paramUserVO);
  
  Map getPackingListGenerationEssentials(Inv_SampleCollectionVO paramInv_SampleCollectionVO, UserVO paramUserVO);
  
  List<Inv_SampleCollectionVO> getSampleCollectionAreaWard(UserVO paramUserVO);
  
  Map getPackingListGenerationOnLoad(Inv_SampleCollectionVO paramInv_SampleCollectionVO, UserVO paramUserVO);
  
  List<Inv_SampleCollectionVO> getPackingListPatList(Inv_SampleCollectionVO paramInv_SampleCollectionVO, UserVO paramUserVO);
  
  Map<String, List<Inv_SampleCollectionVO>> savePackingListDetails(Map<String, List<Inv_SampleCollectionVO>> paramMap, UserVO paramUserVO);
  
  Map<String, List<Inv_SampleCollectionVO>> generateDuplicatePackingList(Map<String, List<Inv_SampleCollectionVO>> paramMap, UserVO paramUserVO);
  
  Map getRequisitionRaisingEssentials(UserVO paramUserVO);
  
  String getTestComboAJAX(String paramString, UserVO paramUserVO);
  
  Map getTestComboAJAXMAP(String paramString, UserVO paramUserVO);
  
  Map getPrvTestDtlAJAXMAP(String paramString, UserVO paramUserVO);
  
  Map getReqData(String paramString1, UserVO paramUserVO, String paramString2);
  
  Map searchTestGroup(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  String getTestGroupAJAX(String paramString, UserVO paramUserVO);
  
  Map getTestsBasedOnGroup(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  Map getAptBasedTest(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  String getAccountNo(Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  List saveRequisitionCollectionDetails(Map<String, Map<String, List<LabTestVO>>> paramMap, Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  List insertRaisingCumSelectionInReqDtl(Map<String, Map<String, List<LabTestVO>>> paramMap, Inv_RequisitionRaisingPatientVO paramInv_RequisitionRaisingPatientVO, UserVO paramUserVO);
  
  String getreqStatusAJAX(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  Map getTestsCodeWiseDtl(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  Map deleteReqDtl(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  boolean checkRequisitionPending(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  Map getPackingListDetails(SampleAcceptanceVO paramSampleAcceptanceVO, UserVO paramUserVO);
  
  Map searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
  
  Map getRequisitionRaisingEssentialsCumColl(UserVO paramUserVO);
  
  Map searchLaboratoryWiseTestGroupOnClickCumColl(InvestigationSearchVO paramInvestigationSearchVO, UserVO paramUserVO);
}
