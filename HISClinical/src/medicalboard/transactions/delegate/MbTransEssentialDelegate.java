package medicalboard.transactions.delegate;

import hisglobal.business.Delegate;
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

import medicalboard.transactions.bo.MedicalBoardEssentialBO;
import medicalboard.transactions.bo.MedicalBoardEssentialBOi;

public class MbTransEssentialDelegate extends Delegate  
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public MbTransEssentialDelegate()
	{
		super(new MedicalBoardEssentialBO()); //// Setting the service provider
	}
	
	public Map setMsNewRegistrationEssentials(UserVO _userVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.setMsNewRegistrationEssentials(_userVO));
	}
	
	public Map getCheckList(String certificateType,UserVO _userVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCheckList(certificateType,_userVO));
	}
	
	public MbOrganizationMstVO getOrganizationDetail(String orgId,UserVO _userVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getOrganizationDetail(orgId,_userVO));
	}
	/** Getting the List of Unit
	 * @param userVO
	 * @return
	 */
	public List getPatientCategory(UserVO userVO)
	{
		MedicalBoardEssentialBOi serviceBO=(MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getPatientCategory(userVO);
	}
	
	public List getCertificateType(UserVO userVO)
	{
		MedicalBoardEssentialBOi serviceBO=(MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getCertificateType(userVO);
	}
	
	public List getOrganisationName(UserVO userVO)
	{
		MedicalBoardEssentialBOi serviceBO=(MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getOrganisationName(userVO);
	}

	public List getPatientListForRequisition(UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO=(MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getPatientListForRequisition(userVO);
	}

	public Map getCertificateTypeForRequisition(UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO=(MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getCertificateTypeForRequisition(userVO);
	}

	public void saveMedicalBoardRequisition(List lstCheckList,
			MedicalBoardRequisitionVO requisitionVO, MedicalBoardBillingVO billingVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO=(MedicalBoardEssentialBOi)super.getServiceProvider();
		serviceBO.saveMedicalBoardRequisition(lstCheckList,requisitionVO,billingVO,userVO);
		
	}

	public Map getEssentialForRequisition(MbCertificateTypeMstVO certificateTypeMstVO, String patCrNo, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getEssentialForRequisition(certificateTypeMstVO,patCrNo,userVO));
	}

	public List getScheduleListForRequisition(
			MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getScheduleListForRequisition(certificateTypeMstVO,userVO));
	}

	public List getCIDNoListForRequisition(
			MbCertificateTypeMstVO certificateTypeMstVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCIDNoListForRequisition(certificateTypeMstVO,userVO));
	}

	public List getPatientMbRequisitionsByCrNo(String patCrNo, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getPatientMbRequisitionsByCrNo(patCrNo,userVO));
	}

	public void saveMedicalBoardRequisitionChange(List requisitionVOList,
			List requisitionChangeVOList, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		serviceBO.saveMedicalBoardRequisitionChange(requisitionVOList,requisitionChangeVOList,userVO);
		
	}

	public List getMbRequisitionsByOrg(String orgId, String orgName,
			UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getMbRequisitionsByOrg(orgId,orgName,userVO));
	}

	// candidate Acceptance
	public List getCertificateTypeList(UserVO userVO,String date) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCertificateTypeList(userVO, date));
	}

	public List getCandidateList(String certificateTypeID, UserVO userVO,String examDate) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCandidateList(certificateTypeID,userVO,examDate));
	}
	public List getPrevCandidateList(String certificateTypeID, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getPrevCandidateList(certificateTypeID,userVO));
	}
	public Map getCandidateAcceptanceEssential(
			MedicalBoardRequisitionVO requisitionVO, UserVO userVO,String examDate) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCandidateAcceptanceEssential(requisitionVO,userVO,examDate));
	}

	public void saveCandidateAcceptance(List checklistVOList,
			MedicalBoardRequisitionVO requisitionVO, EpisodeVO episodeVO,
			PatientVO patientVO, MedicalBoardVisitDtlVO visitDetailVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		serviceBO.saveCandidateAcceptance(checklistVOList,requisitionVO,episodeVO,patientVO,visitDetailVO,userVO);
	}

	public PatientVO getPatientForRequisitionByCrNo(String patCrNo,
			UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getPatientForRequisitionByCrNo(patCrNo,userVO));
	}

	//Medical Exam Inititation
	public List getCertificateTypeListForMedicalExam(UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCertificateTypeListForMedicalExam(userVO));
	}

	public Map getCandidateListForMedicalExam(String certificateTypeID,
			UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCandidateListForMedicalExam(certificateTypeID,userVO));
	}

	public Map getReferMappingList(
			String certificateTypeID, EpisodeRefDtlVO episodeRefDtlVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getReferMappingList(certificateTypeID,episodeRefDtlVO,userVO));
	}

	public void saveCandidateReferDetail(EpisodeRefDtlVO[] episodeRefVO,
			EpisodeVO[] episodeVO, MedicalBoardVisitDtlVO[] visitDetailVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		serviceBO.saveCandidateReferDetail(episodeRefVO,episodeVO,visitDetailVO,userVO);
	}

	public List getMBCandidateRefDetail(String patCrNo, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getMBCandidateRefDetail(patCrNo,userVO);
	}

	public Map getMBInvestigationEssential(MedicalBoardRequisitionVO[] requisitionVOArray, String certificateTypeID,
			UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getMBInvestigationEssential(requisitionVOArray,certificateTypeID,userVO);
	}

	public void raiseCandidateInvestigation(List patientDtlVOList,EpisodeVO[] episodeVO, List<Test> testVOList,
			MedicalBoardVisitDtlVO[] visitDetailVO, String sysdate, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		serviceBO.raiseCandidateInvestigation(patientDtlVOList,episodeVO,testVOList,visitDetailVO,sysdate,userVO);
	}
	
	//Post Medical Entry
	public Map getMBPostEntryEssential(UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getMBPostEntryEssential(userVO);
	}

	public List getCandidateListForPostEntry(String certificateTypeID,
			String boardNo, String fromDate, String toDate, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getCandidateListForPostEntry(certificateTypeID,boardNo,fromDate,toDate,userVO);
	}

	public Map getCandidateDetailForPostEntry(
			MedicalBoardRequisitionVO requisitionVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getCandidateDetailForPostEntry(requisitionVO,userVO);
	}

	public void savePostEntryDetail(MedicalBoardRequisitionVO requisitionVO,
			MedicalBoardRequisitionChangeVO reqChangeVO, String isMedicalPerformed, List checklistVOList,List verificationVOList ,UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		serviceBO.savePostEntryDetail(requisitionVO,reqChangeVO,isMedicalPerformed,checklistVOList,verificationVOList,userVO);
		
	}

	public List getMBCandidateInvDetail(EpisodeVO episodeVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getMBCandidateInvDetail(episodeVO,userVO);
	}

	

	
/*	public MedicalBoardChecklistVO[] getAllChecklistDetails(UserVO userVO)
	{
		MedicalBoardEssentialBOi serviceBO=(MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getAllChecklistDetails(userVO);
	}
	*/
	
	public Map setMedicalBoardInitializationEssentials(UserVO _userVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.setMedicalBoardInitializationEssentials(_userVO));
	}
	
	public List getScheduleList(MbCertificateTypeMstVO certificateTypeMstVO,UserVO _UserVO)
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getScheduleList(certificateTypeMstVO,_UserVO));
	}
	
	public Map getBoardDetail(String certificateTypeId ,String scheduleDate,UserVO _UserVO)
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getBoardDetail(certificateTypeId,scheduleDate,_UserVO));
	}
	
	public List getTeamDetailByBoardId(String boardId,UserVO _UserVO)
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getTeamDetailByBoardId(boardId,_UserVO));
	}
	
	public List getAvailableBoardDetailList(String certificateTypeId ,String scheduleDate,UserVO _UserVO)
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getAvailableBoardDetailList(certificateTypeId,scheduleDate,_UserVO));
	}
	
	public List getAssignTeamDetail(String boardNo,UserVO _UserVO)
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getAssignTeamDetail(boardNo,_UserVO));
	}
	
	public Map setCertificateHandoverEssentials(UserVO _userVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.setCertificateHandoverEssentials(_userVO));
	}
	
	public Map getReqDateByCertificateTypeId(String certificateTypeId,UserVO _userVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getReqDateByCertificateTypeId(certificateTypeId,_userVO));
	}
	
	public List getCandidateListByReqDate(String certificaetTypeId,String reqDate,UserVO _UserVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getCandidateListByReqDate(certificaetTypeId,reqDate,_UserVO));
	}
	
	public List getcandidateListByCerficateType(String certificaetTypeId,UserVO _UserVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getcandidateListByCerficateType(certificaetTypeId,_UserVO));
	}
	
	public List getAllCandidateListByCrNo(String patCrNo,UserVO userVO){
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getAllCandidateListByCrNo(patCrNo,userVO));
	}
	
	public Map getMBCertVerificationEssential(UserVO userVO) 
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getMBCertVerificationEssential(userVO));
	}
	
	public List getCandidateListForCertVerification(String certificateTypeID,
			String boardNo, String fromDate, String toDate, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getCandidateListForCertVerification(certificateTypeID,boardNo,fromDate,toDate,userVO);
	}
	
	public Map getMBBoardMemberOpinionDetail(MedicalBoardRequisitionVO requisitionVO,UserVO userVO) 
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getMBBoardMemberOpinionDetail(requisitionVO,userVO));
	}
	
	public String getScanFlag(MedicalBoardRequisitionVO requisitionVO,UserVO userVO) 
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getScanFlag(requisitionVO,userVO));
	}
	
	public String getLastCertificateNo(String strCertTypeId, UserVO userVO) 
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return (serviceBO.getLastCertificateNo(strCertTypeId, userVO));
	}

	public List getBoardListForPostEntry(String certificateTypeID,UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getBoardListForPostEntry(certificateTypeID,userVO);
	}

	public void saveExternalRefer(MedicalBoardExternalReferVO[] externalReferVO,		UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		serviceBO.saveExternalRefer(externalReferVO,userVO);
		
	}

	public List<MedicalBoardExternalReferVO> getMBCandidateExtRefDetail(String reqID, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi)super.getServiceProvider();
		return serviceBO.getMBCandidateExtRefDetail(reqID,userVO);
	}
	
	public void savePatientImage(PatientImageDtlVO patimageVO, UserVO userVO) {
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi) super.getServiceProvider();
		serviceBO.savePatientImage(patimageVO, userVO);
	}

	public String getMaxCRNOPatientImage(String patCRNO,UserVO userVO)
	{
		MedicalBoardEssentialBOi serviceBO = (MedicalBoardEssentialBOi) super.getServiceProvider();
		return serviceBO.getMaxCRNOPatientImage(patCRNO, userVO);
		
	}
}
	
