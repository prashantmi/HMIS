
package inpatient.transaction.bo;

import java.util.List;
import java.util.Map;

import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.ANCVisitDetailVO;
import hisglobal.vo.DoctorWardRoundDtlVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

public interface InpatientBOi 
{
	
	/** Getting The Patient Detail By CR No For Desk
	 * @param deskType
	 * @param deskPatTileVO
	 * @param userVO
	 * @return
	 */
	public PatientDetailVO getInpatientDetailByCrNoNAdmNo(String deskType,PatientDetailVO deskPatTileVO,UserVO userVO);
	
	/** Getting The MLC No of The Patient For Desk
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public MlcVO getMlcNo(PatientDetailVO patDtlVO,UserVO userVO);
	
	/** Saving Nurse Progress Notes
	 * @param nurseRoundDtlVO
	 * @param userVO
	 */
	public void saveNurseProgNotes(NurseRoundDtlVO nurseRoundDtlVO,UserVO userVO);
	
	/** Saving Doctor Visit Notes By Nurse
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void saveDoctorVisitNotes(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);
	
	/** Getting Doctor Instruction on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getDoctorInstruction(String patAdmNo,UserVO userVO);
	
	/** Getting the Data Entered by Nurse and Not Verified
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getUnverifiedEntryByNurse(String patAdmNo,UserVO userVO);
	
	/** Getting the Details o record entered by the Nurse
	 * @param unverifiedRecordVO
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO getRecordDetail(DoctorRoundDtlVO unverifiedRecordVO,UserVO userVO);
	
	/**  Updating Nurse Visit Notes & Saving Doctor Instruction,Progress Notes 
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void saveNVerifyNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);
	
	/**  Saving Notes Entered By Doctor
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void saveNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);
	
	/** Saving the Out Take
	 * @param patIntakeOutDtlVO
	 * @param userVO
	 */
	public void saveOutParameter(List<PatIntakeOutDtlVO> listPatIntakeOutTake,UserVO userVO);
	
	/** Getting the Details of out Take Parameter of the Patient
	 * @param dailyPatVO
	 * @param userVO
	 * @return
	 */
	public PatIntakeOutDtlVO[] getOutParaDetail(String mode,PatientDetailVO dailyPatVO,UserVO userVO);
	
	public Map getBulletinDetails(PatientDetailVO patDtlVO,UserVO userVO);

	public void saveAndUpdatePatientBulletinDetails(PatientBulletinDetailVO _oldPatientBulletinVO,PatientBulletinDetailVO _newPatientBulletinVO,UserVO userVO,String revoke);
	
	//saving inpatient detail
	
	public  void saveVitalsDetail(PatientMonitoringMstVO[] patMonitringMstVO,PatientMonitoringMstVO[] modPatMonitringMstVO,UserVO userVO);
	//getting vital detail
	
	public PatientMonitoringMstVO[] getVitalDetail(PatientMonitoringMstVO patMonitringMstVO,UserVO userVO);
	

	public NurseRoundDtlVO[] getPreviousProgressNotes(String patAdmNo,UserVO userVO);
	/** Getting Doctor Prev Round Dtl on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getDoctorPrevRoundDetail(String patAdmNo, UserVO userVO);
	
	/** Revoking the Vitals of the Patients
	 * @param paraId
	 * @param patMonitoringVitalVO
	 * @param userVO
	 */
	public void revokeVitals(String paraId,PatientMonitoringMstVO patMonitoringVitalVO,UserVO userVO);
	
	/**
	 * Getting Essentials for Monitoring Vitals
	 * @param _deskType Desk Type
	 * @param _userDeskMenuTempVO User Desk Menu Detail
	 * @param _patClinicalVO Patient Clinical Data VO
	 * @param patMonitringMstVO Pat Monitoring VO
	 * @param _userVO User VO
	 * @return Map of Essentials
	 */
	public Map<String, Object> getEssentailsForMonitoringVital(String _deskType, UserDeskMenuTemplateMasterVO _userDeskMenuTempVO, 
			PatientClinicalDetailVO _patClinicalVO, PatientMonitoringMstVO _patMonitringMstVO, UserVO _userVO);

	public void saveDischargeRequest(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO);
	
	public boolean getPatientStatus(String admNo,UserVO userVO);
	
	public PatDischargeReqDtlVO getDischargeRemarks(String admNo,UserVO userVO);

	public PatientBulletinDetailVO[] getAllAdmittedPatientListBulletin(UserVO userVO);
	

	public boolean saveDoctorCallDetails(DoctorCallBookVO callBookVO,UserVO _userVO);
	
	public boolean ModifyDoctorCallDetails(DoctorCallBookVO callBookVO,UserVO _userVO);

	public PatIntakeOutDtlVO[] getIntakeSummary(String patCrNo,UserVO userVO);
	

	public void saveDrugTreatmentExe(List drugAdminDtlVoList,List ivFluidDrugAdminVOList,List sosDrugList,List patIntakeOutDtlVOList,UserVO _userVO);
	
	public void saveExtTreatmentExe(List actvityList,List extAdminVoList,UserVO _userVO);
	
	
	public void saveDrugReactionDtl(DrugReactionVO drugReactionVO,List templateParaList, UserVO _userVO);
	

	public PatIntakeOutDtlVO[] getOuttakeSummary(String patCrNo,UserVO userVO);
	
	public void saveExternalBloodStockDtl(PatBloodStockDtlVO[] patBloodStockDtlVO,UserVO _userVO);
	
	public void saveDoctorWardRoundDtl(DoctorWardRoundDtlVO _doctorWardRoundDtlVO,List callBookList, UserVO _userVO);


	public List getDeptUnitList(UserVO userVO);
	
	public DoctorCallBookVO[] getCallBookDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	

	//public DoctorCallBookVO[] getCallInboxDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	

	public DoctorCallBookVO[] getAllCalls(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	
	public DoctorCallBookVO getCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	
	public DoctorCallBookVO[] getCallAcknowledgeDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	
	public DoctorCallBookVO[] getAllCallsAcknowledge(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	
	public boolean saveDoctorCallAcknowledgeDetails(DoctorCallBookVO callBookVO,UserVO _userVO);


	public void saveBloodTransfusionDtl(Map<String, TransfusionReactionDtlVO> mapTrasReaction,Map<String, List<TransfusionReactionParaDtlVO>> mapTrasReactionPara,List bloodTrasVOList,List patIntakeOutDtlVOList,String[] selectedBag,UserVO _userVO);

	
	public void saveBloodTransReactionDtl(TransfusionReactionDtlVO vo,List bloodTrasReactionParaVOList,UserVO _userVO);
	
	
	
	public Map getExtPatTransReaction(UserVO _userVO);
	
	
	
	public void saveExtBloodTransReactionDtl(TransfusionReactionDtlVO transReactionDtlVO,List bloodTrasReactionParaVOList,UserVO _userVO);

	/**
	 * Getting ANC Detail 
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map getANCDetails(ANCDetailVO _ancDetailVO, UserVO _userVO);

	/**
	 * Saving ANC Detail
	 * @param _ancDetailVO
	 * @param _userVO
	 */
	public void saveANCDetail(ANCDetailVO _ancDetailVO, UserVO _userVO, JDBCTransactionContext tx);

	/**
	 * Updating ANC Detail
	 * @param _ancDetailVO
	 * @param _userVO
	 */
	public void updateANCDetail(ANCDetailVO _ancDetailVO, UserVO _userVO, JDBCTransactionContext tx);
	
	/**
	 * Saving ANC Visit Detail
	 * @param _ancVisitDetailVO ANc Visit Detail VO
	 * @param _userVO User Detail
	 * @param tx Transaction Context Object
	 */
	public void saveANCVisitDetail(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO, JDBCTransactionContext tx);

	/**
	 * Getting ANC History Detail 
	 * @param _patCrNo Patient Cr No
	 * @param _userVO User Detail
	 * @return List of ANC History
	 */
	public List<ANCHistoryDetailVO> getANCHistoryDetail(String _patCrNo, UserVO _userVO);

	/**
	 * Saving Complete ANC Detail
	 * @param _ancDtlFlag ANC Detail Flag
	 * @param _patDtlVO Patient Other Demographic Detail
	 * @param _ancDetailVO ANC Detail
	 * @param _ancVisitDtlVO ANC Visit Detail
	 * @param _lstHistVOs ANC History Detail
	 * @param _lstHistChildVOs ANC History Delivery Detail
	 * @param _userVO User Detail
	 */
	public void saveCompleteANCDetail(String _ancDtlFlag, PatientDetailVO _patDtlVO, ANCDetailVO _ancDetailVO,
			ANCVisitDetailVO _ancVisitDtlVO, List<ANCHistoryDetailVO> _lstHistVOs, List<ANCHistoryDeliveryDetailVO> _lstHistChildVOs, UserVO _userVO);

	
	// Jsy Detail
	public Map getPatDetailForJSYRegistration(String _patCrNo, UserVO _userVO);

	public void saveJSYDetail(JSYRegitrationVO jRegitrationVO,PatientDetailVO pDetailVO,String jsyCategoryCode,SecondaryCategoryChangeVO sChangeVO,UserVO _userVO);

	


	/**
	 * Getting ANC Detail for Delivery
	 * @param _ancDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map getANCDetailsForDelivery(ANCDetailVO _ancDetailVO, UserVO _userVO);
	
	/**
	 * Saving ANC Delivery Detail
	 * @param _ancDeliveryDetailVO
	 * @param _userVO User Detail
	 */
	public void saveANCDeliveryDetail(ANCDeliveryDetailVO _ancDeliveryDetailVO, ANCDetailVO _ancDetailVO, ANCHistoryDetailVO _ancHistVO,
			List<ANCNeonatalDetailVO> _lstANCChildren, List<ANCHistoryDeliveryDetailVO> _lstANCHistChildren, UserVO _userVO);

	/**
	 * Saving Complete ANC New Natal Detail & Apgar Detail
	 * @param _ancNewNatalVO ANc New Natal Detail VO
	 * @param _lstApgars List of Apgar Details
	 * @param _userVO User Detail
	 */
	public void saveANCNewNatalDetail(ANCNeonatalDetailVO _ancNewNatalVO, List<ANCNeonatalApgarVO> _lstApgars, 
			UserVO _userVO);
	
	public PatIntakeOutDtlVO[] getViewSummaryDetail(String admNo,String fromDate,String toDate,UserVO userVO);
	
	
	public DoctorCallBookVO[] getOnCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	
	public List getDrugReactionDtl(DrugReactionVO drugReactionVO,UserVO _userVO);

	/**
	 * Saving ANC Child Handover Detail
	 * @param _ancChildHandoverVO Child Handover Detail
	 * @param _userVO User Detail
	 */
	public void saveANCChildHandoverDetail(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO);

	/**
	 * Saving ANC Trimester Checklist Detail
	 * @param _lstDrugChkLst List of Drug CheckList
	 * @param _lstInvstChkLst List of Invest CheckList
	 * @param _userVO User Detail
	 */
	public void saveANCTrimesterChecklistDetail(List<ANCChecklistDetailVO> _lstDrugChkLst, List<ANCChecklistDetailVO> _lstInvstChkLst, UserVO _userVO);

	//addedBy:NehaRajgariya Date:1sept2016
	public String getSmsFlagDetails(DoctorCallBookVO _callBookVO, UserVO _userVO);

	public void getSnomedIdTerm(PatIntakeOutDtlVO patIntakeOutDtlVO, UserVO userVO);
	
	//Added by Vasu on 26.Sept.2018 to update doctor Notes
	public void updateNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);
}
