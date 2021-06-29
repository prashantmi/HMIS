package investigationDesk.transactions.delegate;

import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;
import investigationDesk.transactions.bo.InvestigationEssentialBO;
import investigationDesk.transactions.bo.InvestigationEssentialBOi;
import investigationDesk.vo.Inv_EpisodeVO;
import investigationDesk.vo.Inv_PatientAdmissionDtlVO;
import investigationDesk.vo.Inv_RequisitionRaisingPatientVO;
import investigationDesk.vo.Inv_SampleCollectionVO;
import investigationDesk.vo.InvestigationSearchVO;
import investigationDesk.vo.LabTestVO;
import investigationDesk.vo.SampleAcceptanceVO;
import java.util.List;
import java.util.Map;
























public class InvestigationEssentialDelegate
  extends Delegate
{
  public InvestigationEssentialDelegate() { super(new InvestigationEssentialBO()); }


  
  public Inv_RequisitionRaisingPatientVO getPatientRegistration_EpisodeDetailEssentials(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPatientRegistration_EpisodeDetailEssentials(patVO, userVO);
  }

  
  public Map searchLabWiseTestDetails(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.searchLabWiseTestDtls(searchVO, userVO);
  }


  
  public Map searchLaboratoryWiseTestGroupOnClick(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.searchLaboratoryWiseTestGroupOnClick(searchVO, userVO);
  }



  
  public List saveRequisitionDetails(Map<String, Map<String, LabTestVO>> mp, Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.saveRequisitionDetails(mp, patVO, _userVO);
  }


  
  public List<Inv_EpisodeVO> getPatientEpisodeDetail(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPatientEpisodeDetail(patVO, _userVO);
  }


  
  public List<Inv_PatientAdmissionDtlVO> getPatientAdmissionDetail(Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPatientAdmissionDetail(patVO, _userVO);
  }


  
  public List<Inv_SampleCollectionVO> getSampleCollectionArea(UserVO _userVO, String wardCode) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getSampleCollectionArea(_userVO, wardCode);
  }



  
  public List<Inv_SampleCollectionVO> getSampleCollectionAreaWard(UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getSampleCollectionAreaWard(_userVO);
  }



  
  public List<Inv_SampleCollectionVO> getPatList(Inv_SampleCollectionVO objSampleCollectionVO, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPatList(objSampleCollectionVO, _userVO);
  }



  
  public Map getRequisitionRaisingEssentials(UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getRequisitionRaisingEssentials(userVO);
  }


  
  public String getTestComboAJAX(String labCode, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getTestComboAJAX(labCode, userVO);
  }


  
  public Map getTestComboAJAXMAP(String labCode, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getTestComboAJAXMAP(labCode, userVO);
  }


  
  public Map getPrvTestDtlAJAXMAP(String CrNo, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPrvTestDtlAJAXMAP(CrNo, userVO);
  }


  
  public Map getReqData(String CrNo, UserVO userVO, String fromwhichcall) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getReqData(CrNo, userVO, fromwhichcall);
  }



  
  public Map<String, Map<String, List<String>>> getBookMarkDetails(UserVO userVO, String deptUnitCode) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getBookMarkDetails(userVO, deptUnitCode);
  }

  
  public Map searchBookMark(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.searchBookMark(searchVO, userVO);
  }

  
  public Map searchTestGroup(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.searchTestGroup(searchVO, userVO);
  }

  
  public Map getTestsBasedOnGroups(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getTestsBasedOnGroup(searchVO, userVO);
  }



  
  public String getTestGroupAJAX(String labCode, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getTestGroupAJAX(labCode, userVO);
  }




  
  public Map getBilledPatList(List<String> reqList, Inv_SampleCollectionVO voSample, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getBilledPatientList(reqList, voSample, userVO);
  }



  
  public List saveSampleCollectionDetails(Map<String, Map<String, Map<String, List<Inv_SampleCollectionVO>>>> mp, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.saveSampleCollectionDetails(mp, _userVO);
  }


  
  public boolean checkSampleNoDuplicacy(Inv_SampleCollectionVO voSample, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.checkSampleNoDuplicacy(voSample, userVO);
  }



  
  public String checkAutoGenFormate(Inv_SampleCollectionVO voSample, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.checkAutoGenFormate(voSample, userVO);
  }




  
  public Map getPackingListGenerationEssentials(Inv_SampleCollectionVO voSample, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPackingListGenerationEssentials(voSample, userVO);
  }

  
  public Map getPackingListGenerationOnLoad(Inv_SampleCollectionVO voSample, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPackingListGenerationOnLoad(voSample, userVO);
  }



  
  public List<Inv_SampleCollectionVO> getPackingListGenerationPatList(Inv_SampleCollectionVO objSampleCollectionVO, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPackingListPatList(objSampleCollectionVO, _userVO);
  }



  
  public Map<String, List<Inv_SampleCollectionVO>> savePackingListDetails(Map<String, List<Inv_SampleCollectionVO>> mp, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.savePackingListDetails(mp, _userVO);
  }



  
  public Map<String, List<Inv_SampleCollectionVO>> generateDuplicatePackingList(Map<String, List<Inv_SampleCollectionVO>> mp, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.generateDuplicatePackingList(mp, _userVO);
  }

  
  public Map getAptBasedTest(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getAptBasedTest(searchVO, userVO);
  }
  
  public String getAccountNo(Inv_RequisitionRaisingPatientVO patVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getAccountNo(patVO, userVO);
  }


  
  public List saveRequisitionCollectionDetails(Map<String, Map<String, List<LabTestVO>>> mp, Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.saveRequisitionCollectionDetails(mp, patVO, _userVO);
  }

  
  public List insertRequisitionCollectionDetails(Map<String, Map<String, List<LabTestVO>>> mp, Inv_RequisitionRaisingPatientVO patVO, UserVO _userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.insertRaisingCumSelectionInReqDtl(mp, patVO, _userVO);
  }


  
  public Map getTestsCodeWiseDtl(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getTestsCodeWiseDtl(searchVO, userVO);
  }


  
  public Map deleteReqDtl(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.deleteReqDtl(searchVO, userVO);
  }


  
  public String getreqStatusAJAX(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getreqStatusAJAX(searchVO, userVO);
  }



  
  public boolean checkRequisitionPending(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.checkRequisitionPending(searchVO, userVO);
  }


  
  public Map getPackingListDetails(SampleAcceptanceVO sampleAcceptanceVO, UserVO _UserVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getPackingListDetails(sampleAcceptanceVO, _UserVO);
  }


  
  public Map searchLabWiseTestDtlsRaisingCumCollection(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.searchLabWiseTestDtlsRaisingCumCollection(searchVO, userVO);
  }



  
  public Map getRequisitionRaisingEssentialsCumColl(UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.getRequisitionRaisingEssentialsCumColl(userVO);
  }

  
  public Map searchLaboratoryWiseTestGroupOnClickCumColl(InvestigationSearchVO searchVO, UserVO userVO) {
    InvestigationEssentialBOi serviceBO = (InvestigationEssentialBOi)getServiceProvider();
    return serviceBO.searchLaboratoryWiseTestGroupOnClickCumColl(searchVO, userVO);
  }
}
