package medicalboard.transactions.dao;

import hisglobal.vo.BoardConfigurationVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MBInvestigationRequisitionDetailVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbLocationEligiblityMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardBillingVO;
import hisglobal.vo.MedicalBoardChecklistVO;
import hisglobal.vo.MedicalBoardInvestigationMappingVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.Test;
import hisglobal.vo.UserVO;

import java.util.List;

public interface MbTransactionEssentialDAOi
{
	public List getPatientCategory(UserVO userVO);
	public MbCertificateTypeMstVO[] getCertificateType(UserVO userVO);
	public List getOrganisationName(UserVO userVO);
	public BoardConfigurationVO getDepartmentDetail(UserVO userVO);
	public MedicalBoardChecklistVO[] getAllChecklistDetails(String certificateType,UserVO userVO);
	public MbOrganizationMstVO getOrganizationDetail(String orgId,UserVO userVO);
	
	public List getPatientListForRequisition(UserVO userVO);
	
	public PatientVO getPatientForRequisitionByCrNo(String crNo,UserVO userVO);
	
	public MbCertificateTypeMstVO[] getCertificateTypeForRequisition(UserVO userVO);
	
	public List getScheduleForRequisition(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO);
	
	public List getCIDNoListForRequisition(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO);

	public List<MbLocationEligiblityMstVO> getLocationEligliblity(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO);

	// Medical Board Requisition change
	public List getPatientMbRequisitionsByCrNo(String crNo,UserVO userVO);
	
	public int checkDuplicateRequisitionForCertitificateType(String patCrNo,String certtificateTypeID,UserVO userVO);
	
	public List getMbRequisitionsByOrg(String orgId,String orgName,UserVO userVO);
	
	public List getBillingDetail(String patCrNo,UserVO userVO);
	
	public void updateBillingQuantity(MedicalBoardBillingVO billingVO,UserVO userVO);
	
	// candidate Acceptance
	public List getCertificateTypeList(UserVO userVO,String date);
	
	public List getcandidateListByCerficateType(String certificateTypeId,UserVO userVO,String Date);
	
	public List getCheckListForAcceptance(String certificateTypeId,String reqID,UserVO userVO);
	
	public List getBoardListByCerficateType(String certificateTypeId,UserVO userVO,String date);
	
	public EpisodeVO getEpisodeDetailByCrNo(String crNo,UserVO userVO);
	
	public EpisodeVO getEpisodeCodeByCrNo(String crNo,UserVO userVO);

	//Medical Exam initiation
	public String getMedicalBoardDepartment(UserVO userVO);
	
	public List getCandidateListForMedicalByCerficateType(String certificateTypeId,String reqStaus,UserVO userVO);
	
	public List getCertificateTypeListForMedicalExam(UserVO userVO);
	
	public List getAllCertificateTypeList(UserVO userVO);
	
	public List getScheduleList(MbCertificateTypeMstVO certificateTypeMstVO,UserVO userVO);
	
	public List getBoardDetailByCerficateType(String certificateTypeId,UserVO userVO);
	
	public List getTeamDetailByBoardId(String boardId,UserVO _UserVO);
	
	public List getAssignBoardDetailList(String certificateTypeId,String scheduleDate,UserVO userVO);
	
	public List getAvailableBoardDetailList(String certificateTypeId,String scheduleDate,UserVO userVO);
	
	public List getAssignTeamDetail(String boardNo,UserVO _UserVO);

	
	public List<EpisodeRefDtlVO> getMBCandidateRefDetail(String crNo,UserVO userVO);
	
	public List<MedicalBoardInvestigationMappingVO> getMBInvestigationMapping(String certificateTypeID,UserVO userVO);
	
	public List getMBInvestigationTestSamples(UserVO userVO);
	
	public EpisodeVO getEpisodeDetailByEpisodeCode(EpisodeVO episodeVO,UserVO userVO);
	
	public PatientDetailVO getPatientDetailByEpisodeCode(PatientDetailVO patientDtlVO,UserVO userVO);
	
	public List<MBInvestigationRequisitionDetailVO> getInvDetailByEpisodeCode(EpisodeVO episodeVO,UserVO userVO);
	
	//Post Medical Entry
	public List getCertificateTypeListForPostEntry(UserVO userVO);
	
	public List getAssginedBoard(UserVO userVO);
	
	public List getConsultantList(UserVO userVO);
	
	public List getCandidateListForPostEntry(String certificateTypeID,
			String reqStaus,String boardNo,String fromDate,String toDate, UserVO userVO);
	
	public List getCheckListForPostEntry(String certificateTypeId,UserVO userVO);
	

	public String[] raiseMBInvestigationRequest(PatientDetailVO _patVO, EpisodeVO _episodeVO, List<Test> _lstTestDtl,
			String _sysDate, String _reqArea, String _reqType, UserVO _userVO);
	
	public List getVarifiedCertificateTypeList(UserVO userVO);
	
	public List getReqDateByCertificateTypeId(String certificateTypeId,UserVO userVO);
	
	public List getCandidateListByReqDate(String certificateTypeId,String reqDate,UserVO userVO);
	
	public List getAllCandidateListByCertificateType(String certificateTypeId,UserVO userVO);
	
	public List getHandOverByEmpList(UserVO _userVO);
	
	public List getAllCandidateListByCrNo(String patCrNo,UserVO userVO);
	
	public List getOpinionListByReqId(String requisitonId,UserVO userVO);
	
	public List getBoardMemberListByReqId(String requisitonId,UserVO userVO);
	
	public List getCertTypeListForCertVerification(UserVO userVO);
	
	public List getAssginedBoardForVerification(UserVO userVO);
	
	public List getCandidateListForCertVerification(String certificateTypeId,String reqStaus,String boardNo,String fromDate,String toDate,UserVO userVO);
	
	public List getBoardMemberOpinionListByReqId(String reqId,UserVO userVO);
	
	public String getScanFlag(String crNo,UserVO userVO);

	public String getLastCertMeDcNo(String strCertTypeId, UserVO userVO);

	public List getAssginedBoardCerttificateBased(String certificateTypeID,UserVO userVO);

	public List getcandidateListByCerficateTypePrevdate(String certificateTypeId,UserVO userVO);

	//For candidate accentance on previous date
	public EpisodeVO getEpisodeCodeByCrNoPrevDate(String crNo,UserVO userVO,String date);

}