package medicalboard.transactions.bo;


import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MbOrganizationMstVO;
import hisglobal.vo.MedicalBoardBillingVO;
import hisglobal.vo.MedicalBoardExternalReferVO;
import hisglobal.vo.MedicalBoardRequisitionChangeVO;
import hisglobal.vo.MedicalBoardRequisitionVO;
import hisglobal.vo.MedicalBoardVisitDtlVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.Test;
import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;


public interface MedicalBoardEssentialBOi
{
	public List getPatientCategory(UserVO userVO);
	
	public List getCertificateType(UserVO userVO);
	
	public List getOrganisationName(UserVO userVO);

	public Map setMsNewRegistrationEssentials(UserVO _userVO);

//	public MedicalBoardChecklistVO[] getAllChecklistDetails(UserVO userVO);
	
	public Map getCheckList(String certificateType,UserVO _userVO);
	
	public  MbOrganizationMstVO getOrganizationDetail(String orgId,UserVO _userVO);

	/* ************************Medical Board Requisition *****************************/ 
	
	public List getPatientListForRequisition(UserVO userVO);
	
	public PatientVO getPatientForRequisitionByCrNo(String crNo,UserVO userVO);
	
	public Map getCertificateTypeForRequisition(UserVO userVO);

	public void saveMedicalBoardRequisition(List lstCheckList,MedicalBoardRequisitionVO requisitionVO, MedicalBoardBillingVO billingVO,UserVO userVO);

	public Map getEssentialForRequisition(MbCertificateTypeMstVO certificateTypeMstVO,String patCrNo, UserVO userVO);

	public List getScheduleListForRequisition(MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO);

	public List getCIDNoListForRequisition(MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO);

	/* **************************Medical Board Requisition Change*****************************/ 
	
	public List getPatientMbRequisitionsByCrNo(String patCrNo, UserVO userVO);

	public void saveMedicalBoardRequisitionChange(List <MedicalBoardRequisitionVO>requisitionVOList,
			List<MedicalBoardRequisitionChangeVO> requisitionChangeVOList, UserVO userVO);

	//public List getMbRequisitions(UserVO userVO);

	public List getMbRequisitionsByOrg(String orgId, String orgName,UserVO userVO);

	public List getCertificateTypeList(UserVO userVO,String date);

	public List getCandidateList(String certificateTypeID, UserVO userVO,String examDate);
	
	public Map getCandidateAcceptanceEssential(MedicalBoardRequisitionVO checklistVO, UserVO userVO,String datr);

	public void saveCandidateAcceptance(List checklistVOList,
			MedicalBoardRequisitionVO requisitionVO, EpisodeVO episodeVO,
			PatientVO patientVO, MedicalBoardVisitDtlVO visitDetailVO, UserVO userVO);

	// medical exam initiation
	public List getCertificateTypeListForMedicalExam(UserVO userVO);
	
	public Map getCandidateListForMedicalExam(String certificateTypeID, UserVO userVO);

	public Map getReferMappingList(String certificateTypeID, EpisodeRefDtlVO episodeRefDtlVO,UserVO userVO);

	public void saveCandidateReferDetail(EpisodeRefDtlVO[] episodeRefVO,
			EpisodeVO[] episodeVO,MedicalBoardVisitDtlVO[] visitDetailVO, UserVO userVO);

	public Map setMedicalBoardInitializationEssentials(UserVO _userVO);
	
	public List getScheduleList(MbCertificateTypeMstVO certificateTypeMstVO,UserVO _UserVO);
	
	public Map getBoardDetail(String certificateTypeId ,String scheduleDate,UserVO _UserVO);
	
	public List getTeamDetailByBoardId(String boardId,UserVO _UserVO);
	
	public List getAvailableBoardDetailList(String certificateTypeId ,String scheduleDate,UserVO _UserVO);
	
	public List getAssignTeamDetail(String boardNo,UserVO _UserVO);

	public List getMBCandidateRefDetail(String patCrNo, UserVO userVO);
	
	public Map getMBInvestigationEssential(MedicalBoardRequisitionVO [] requisitionVO,String certificateTypeID,UserVO userVO);
	
	public List getMBCandidateInvDetail(EpisodeVO episodeVO, UserVO userVO);

	//Post Medical Entry
	
	public Map getMBPostEntryEssential(UserVO userVO);

	public List getCandidateListForPostEntry(String certificateTypeID,
			String boardNo, String fromDate, String toDate, UserVO userVO);
	
	public Map getCandidateDetailForPostEntry(MedicalBoardRequisitionVO requisitionVO, UserVO userVO);

	public void savePostEntryDetail(MedicalBoardRequisitionVO requisitionVO,
			MedicalBoardRequisitionChangeVO reqChangeVO,String isMedicalPerformed, List checklistVOList,List verificationVOList, UserVO userVO);

	public void raiseCandidateInvestigation(List patientDtlVOList,EpisodeVO[] episodeVO, List<Test> testVOList,
			MedicalBoardVisitDtlVO[] visitDetailVO, String sysdate, UserVO userVO);
	
	public Map setCertificateHandoverEssentials(UserVO _userVO);
	
	public Map getReqDateByCertificateTypeId(String certificateTypeId,UserVO _userVO);
	
	public List getCandidateListByReqDate(String certificaetTypeId,String reqDate,UserVO _UserVO);
	
	public List getcandidateListByCerficateType(String certificaetTypeId,UserVO _userVO);
	
	public List getAllCandidateListByCrNo(String patCrNo,UserVO userVO);
	
	public Map getMBCertVerificationEssential(UserVO userVO) ;
	
	public List getCandidateListForCertVerification(String certificateTypeID,String boardNo,String fromDate,String toDate, UserVO userVO);
	
	public Map getMBBoardMemberOpinionDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO);
	
	public String getScanFlag(MedicalBoardRequisitionVO requisitionVO, UserVO userVO);

	public String getLastCertificateNo(String strCertTypeId, UserVO userVO);

	public List getBoardListForPostEntry(String certificateTypeID, UserVO userVO);

	public void saveExternalRefer(MedicalBoardExternalReferVO[] externalReferVO,UserVO userVO);

	public List<MedicalBoardExternalReferVO> getMBCandidateExtRefDetail(String reqID, UserVO userVO);
	
	public void savePatientImage(PatientImageDtlVO patimageVO, UserVO userVO);

	public String getMaxCRNOPatientImage(String strCRNO,UserVO userVO);

	public List getPrevCandidateList(String certificateTypeID, UserVO userVO);

}
