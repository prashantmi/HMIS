package registration.bo;

import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.ChangeCategoryVO;
import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.DocumentUploadDtlVO;
import hisglobal.vo.EmpMasterVO;
import hisglobal.vo.EpisodeActionDtlVO;
import hisglobal.vo.EpisodeDeathVO;
import hisglobal.vo.EpisodeDiagnosisVO;
import hisglobal.vo.EpisodeRefDtlVO;
import hisglobal.vo.EpisodeReferVO;
import hisglobal.vo.EpisodeTriageDetailVO;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.FileNumberChangeVO;
import hisglobal.vo.MCTSRegistrationVO;
import hisglobal.vo.MLCRevokeEpisodeDetailVO;
import hisglobal.vo.MlcDocUploadDtlVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.PatientAdhaarDtlVO;
import hisglobal.vo.PatientBroughtByDtlVO;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientImageDtlVO;
import hisglobal.vo.PatientParichitVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PoliceExaminationReqtDtlVO;
import hisglobal.vo.PoliceVerificationDtlVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.RegCardPrintVO;
import hisglobal.vo.RegChargeDtlVO;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.UnitChangeVO;
import hisglobal.vo.UnknownChangeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.VerificationDocumentVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * PatientBOi is an interface that declares the methods associated with all the transactions of registration.
 * 
 * @author AHIS
 */
public interface PatientBOi
{
	
	//public  EpisodeVO[] registerNewPatient(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO,PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO,String ProcessType);
	/**
	 * Registers a patient when he/she visits the hospital for the first time. Generates the CR No of the Patient. Calculates
	 * age of the patient if DOB is provided and DOB if age is provided. Saves data in Patient Dtl, Address dtl, Daily
	 * Patient dtl, and Episode dtl tables.
	 */
	public EpisodeVO[] newPatRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO, HttpServletRequest  _request) ;// each
	public EpisodeVO[] newPatSplRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO, HttpServletRequest  _request) ;// each

	// VisitStampVO
	// keeps
	// PatientVO

	/**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table. Provides age of the patient according to
	 * the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 */
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Retrieves patient details on the basis of CR No and visit type from the Patient Dtl Table. Provides age of the patient
	 * according to the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 */
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO, String visitType);

	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 */
	// //////////////Search Patient /////////////////
	public PatientVO[] searchPatient(HashMap _searchMap, UserVO _userVO);

	public String checkPatientStatus(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Retrieves all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 */
	public PatientVO[] getCrNoForModification(UserVO _userVO, String episodeVisitType);

	/**
	 * Retrieves all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 */
	public List getCrNoModification(UserVO _userVO);

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and isConfirmed status.
	 * Provides last visit details of all episodes which are open presently.
	 */
	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO, UserVO _userVO, String visitType, String isConfirmed);

	/**
	 * Stamps the visit of a patient when he/she visits a department for the first time. Saves data in Daily Patient dtl, and
	 * Episode dtl tables.
	 */
	public EpisodeVO[] newDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO);

	public EpisodeVO[] newDepartmentVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO);
	public EpisodeVO[] newSplUnitVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO);
	public EpisodeVO[] newDepartmentVisitStampRoomWise(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO);
	
	//public EpisodeVO[] newDepartmentVisitStampForDuplicate(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO);

	/**
	 * Stamps the visit of a patient when he/she re-visits a department. Saves data in Daily Patient dtl, and Episode dtl
	 * tables.
	 */
	public EpisodeVO[] oldDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO, EpisodeRefDtlVO episodeRefVO);

	/**
	 * Changes primary and secondary categories of a patient. Updates the record in Patient dtl.
	 */
	public boolean patCategoryChange(PatientVO _patientVO, EpisodeVO[] _arrEpisodeVO, ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO);

	/**
	 * Checks whether patient record is modifiable at registration desk or not.
	 */
	public boolean checkModify(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Modifies patient details at Registration Desk. Modification at registration can be done with in 10 minutes of
	 * registration. Updates the record in Patient dtl and Address dtl tables. Also updates patient's demographic details in
	 * Daily Patient Detail table.
	 */
	//public boolean patDtlModificationREG(PatientVO _patientVO,PatientVO _patientVOOldData, UserVO _userVO);

	/**
	 * Modifies patient details at MRD Desk. Updates the record Patient dtl and Address dtl tables.
	 */
	 //public boolean patDtlModificationMRD(PatientVO _patientVO, UserVO _userVO);
	/**
	 * Retrieves all the addresses of a patient from Address Dtl Table.
	 */
	public AddressVO[] getPatAddressAll(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Checks whether an address entry has to be made in the DB or an existing address is to be modified.
	 */
	public AddressVO checkAddModify(AddressVO[] _arrAddressVO, PatientVO _patientVO, AddressVO addVO, UserVO _userVO, String add_modify);

	/**
	 * Modifies patient address details at MRD Desk. Also more addresses can be added. Updates the record in Address dtl
	 * table.
	 */
	//public int patAddressDtlModification(AddressVO _arrAddressVO, VerificationDocumentVO _verificationDocumentVO, UserVO _userVO,
			//String _addTypeCode, String _addModify);

	/**
	 * Retrieves patient details. Reserved for future use.
	 */
	public PatientVO getPatientDtl(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Retrieves patient details on the basis of Patient Name. Reserved for future use.
	 */
	public PatientVO[] searchPatientByName(String _patientName, UserVO _userVO);

	/**
	 * Retrieves patient details on the basis of Id Noif the patient is an employee of the hospital.
	 */
	public PatientVO searchPatientByIdNo(String _idNo, UserVO _userVO);

	/**
	 * Retrieves patient details on the basis of Previous CR No alloted in the previous system from the previous DB.
	 */
	public PatientVO getPrevSystemPatDetail(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Facilitates issue of duplicate card for an OPD to a patient. Also enters the details of duplicate card in the DB.
	 */
	public EpisodeVO[] duplicateCardPrinting(RegChargeDtlVO[] _regChargeVO, RegCardPrintVO[] _regCardPrintVO, UserVO _userVO);

	/**
	 * Facilitates the change of unit if a particular unit is not on duty or if the change of unit is required under some
	 * special conditions. Can only performed from MRD desk. Also saves the details of unit change in the DB.
	 */
	public UnitChangeVO unitChangeOldDeptVisit(PatientVO _patientVO, EpisodeVO _episodeVO, UnitChangeVO _unitChangeVO, UserVO _userVO);

	// emergency registration----------

	/**
	 * Registers a patient when he/she visits the hospital for the first time in an Emergency. Generates the CR No of the
	 * Patient. Calculates age of the patient if DOB is provided and DOB if age is provided. Saves data in Patient Dtl,
	 * Address dtl, Daily Patient dtl, and Episode dtl tables.
	 */
	public EpisodeVO emergencyPatRegistration(PatientVO _patientVO, PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO, HttpServletRequest _request);

	/**
	 * Stamps the visit of a patient when he/she re-visits the hosital in an emergency. New episode is started whenever a
	 * patient visits in emergency. Saves data in Daily Patient dtl, and Episode dtl tables.
	 */
	public EpisodeVO emergencyOldDeptVisitStamp(PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO);

	/**
	 * Modifies patient details at emergency registration desk. Modification at registration can be done with in 10 minutes
	 * of registration. Updates the record in Patient dtl and Address dtl tables. Also updates patient's demographic details
	 * in Daily Patient Detail table.
	 * @param patientBroughtByDtlVO 
	 */
	public boolean emergencyPatDtlModificationREG(PatientVO _patientVO, PatientBroughtByDtlVO patientBroughtByDtlVO,PatientVO _patientVOOldData, UserVO _userVO);

	public void savePatientNotAttendedDetail(EpisodeActionDtlVO epActionDtlVO, UserVO _userVO);

	public void savePatientAttendedAdmittedDetail(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Saves MLC details of a patient. Deatils are entered by the CMO. Saves data in Patient MLC Dtl table. Updates mlc no in
	 * Episode dtl table.
	 */
	public MlcVO patMlcDtl(MlcVO _mlcVO, UserVO _userVO, PatientBroughtByDtlVO _patBroughtByDtlVO, PoliceVerificationDtlVO policeVerDtlVO);

	/**
	 * Retrieves patient MLC details on the basis of CR No from the Patient MLC Dtl Table.
	 */
	public MlcVO getMlcDtl(PatientVO _patientVO, MlcVO _mlcVO, UserVO _userVO);

	/**
	 * Creates a new entry in MLC dtl table each time a record is to be modified so as to keep track of all modifications.
	 */
	public void modifyMlcDtl(MlcVO _mlcVO, UserVO _userVO, PatientBroughtByDtlVO _patBroughtByDtlVO,PoliceVerificationDtlVO policeVerDtlVO);

	public void saveDeathDetail(EpisodeDeathVO _episodeDeathVO, PatientVO _patVO, UserVO _userVO);

	public void saveBroughtDeathDetail(EpisodeDeathVO _episodeDeathVO, DailyPatientVO _patVO, UserVO _userVO);

	public void savePatientAttendedDisposed(EpisodeDiagnosisVO _episodeDiagVO[], EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO);

	public void savePatientReferredOut(EpisodeReferVO _episodeReferVO, EpisodeActionDtlVO episodedtlActionDtlVO, UserVO _userVO);

	public void savePatientAttendedObserved(EpisodeDiagnosisVO _episodeDiagVO[], EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO);

	public void savePatientAttendedRefInCasuality(EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO);

	public void savePatientRefInWard(EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO);

	public EpisodeVO isEmergency(PatientVO _patVO, UserVO _userVO);

	public void saveOpdVisitConfirm(EpisodeVO _episodeVO, EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO);

	public void saveOpdEpisodeClose(EpisodeVO _episodeVO, EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO);

	public EpisodeVO[] isOpd(PatientVO _patVO, UserVO _userVO);

	public EpisodeVO isMlcEligible(PatientVO _patVO, UserVO _userVO);

	// Checking if MLC is already added
	public EpisodeVO isCsultyMlcEligible(PatientVO _patVO, String _episodeCode, UserVO _userVO);

	public MlcVO getMlcDtlBasedOnMlcNo(MlcVO _mlcVO, UserVO _userVO);

	public MlcVO getMlcDtlBasedOnCrNo(MlcVO vo, UserVO _uservo);

	public EpisodeVO getEmgOpenEpisode(PatientVO _patVO, UserVO _userVO);

	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO, UserVO _userVO, String visitType);

	/**
	 * Converts an unknown patient to a known patient. Modifies deatils of the patient. Can be done only by the authorized
	 * personnel. Updates the record in Patient dtl and Address dtl tables. Also creates an entry in Unknown Change Detail
	 * table that stores the information regarding when the patient became known.
	 * @param patientVOOldData 
	 */
	public boolean unknownToKnownConversion(PatientVO _patientVO,PatientVO patientVOOldData, VerificationDocumentVO verifDocVO, UserVO _userVO);

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table. Generally used when patient referral is involved.
	 */
	public EpisodeReferVO searchPatientRefEpisodeByEpisodeNo(PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO);

	/**
	 * Modifies refer details of a patient at MRD. Can be done only by the authorized personnel. Updates the record in
	 * Patient dtl, Episode dtl and Episode Refer dtl tables.
	 */
	public boolean referDtlModificationMRD(PatientVO _patientVO, EpisodeReferVO _episodeReferVO, UserVO _userVO);

	/**
	 * Stamps the visit of a patient when he/she visits a department in which he/she is referred from some other department.
	 * Saves data in Daily Patient dtl, Episode dtl and Episode Refer dtl tables.
	 */
	public EpisodeVO[] referredDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, EpisodeReferVO _episodeReferVO, PatientVO _patientVO, UserVO _userVO);

	public UnknownChangeVO getConvertedToKnownDetails(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Retrieves refer internal details of a patient from the Episode Refer Dtl Table.
	 */
	public EpisodeReferVO[] retrieveRefInternalEpisodeDtl(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 */
	public boolean checkPatientStatusByVisitType(PatientVO _patientVO, UserVO _userVO, String visitType);

	/**
	 * Retrieves all episode details of the patient fron Episode Dtl Table.
	 */
	public EpisodeVO[] searchAllEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO);

	/**
	 * 
	 * @param _patientVO
	 * @param _userVO
	 */
	public MlcVO isPatientMlc(PatientVO _patientVO, UserVO _userVO);

	/**
	 * 
	 * @param _mlcVo
	 * @param _uservo
	 * @return
	 */
	public MlcVO[] getMlcDtlBasedOnCrNoandImage(MlcVO _mlcVo, UserVO _uservo);

	public void saveMlcDoc(MlcVO _mlcVo, UserVO _uservo);

	/**
	 * Checks whether currently any episode is opened for a patient in emergency on the basis of CR No. Also checks that the
	 * episodes should be valid and active.
	 */
	public boolean checkOpenEmgEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Changes primary and secondary categories of a patient.
	 * Updates the record in Patient dtl.
	 */
	//  public boolean changePatientCategory(ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO);

	/**
	 * Changes primary and secondary categories of a patient. Updates the record in Patient dtl.
	 */
	// public boolean changePatientCategory(ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO);
	public EpisodeVO getLastEpisodeInEmergency(PatientVO _patVO, UserVO _userVO);

	/**
	 * Retrieves all referred episodes of the patient fron Episode Dtl Table.
	 */
	public EpisodeVO[] retrieveAllReferredEpisodesByCrNo(PatientVO _patientVO, UserVO _userVO);

	/**
	 * Retrieves all currently opened episodes of a patient in OPD.
	 */
	public EpisodeReferVO[] getAllOpenOPDEpisodes(String crNo, UserVO _userVO);

	public EpisodeRefDtlVO[] retrieveAllOpenOPDEpisodes(String crNo, UserVO _userVO);

	public EpisodeRefDtlVO[] retrieveAllSpecialEpisodes(String crNo, UserVO _userVO);

	/**
	 * Retrieves all currently opened episodes of a patient in the Hospital.
	 */
	public Map getAllOpenEpisodes(String crNo, UserVO _userVO);

	/**
	 * Changes primary category of a patient. Updates the record in Patient Dtl and Episode Dtl table.
	 */
	public boolean changePrimaryCategory(PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO, VerificationDocumentVO _veriDocVO,
			EmpMasterVO _empMasterVO, String _patIdNo);

	/**
	 * Changes secondary categories of a patient for open episodes. Updates the record in Episode Dtl table.
	 */
	public boolean changeSecondaryCategory(SecondaryCategoryChangeVO[] _secCatChangeVO,SecondaryCategoryChangeVO[] secCatRevokeVO, UserVO _userVO);

	// public List getListDeptWiseFileNo(String crNo, UserVO _userVO);

	public EpisodeVO[] getMapDeptWiseFileNoChange(String crNo, UserVO _userVO);

	public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, UserVO _userVO, String patPrimaryCode);

	// public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, String primCatCode, UserVO _userVO);

	public Map getMapDeptWiseFileNo(String crNo, UserVO _userVO);

	public EpisodeVO[] changeFileNo(FileNumberChangeVO[] _fileNoChangeVO, UserVO _userVO);

	//public EpisodeVO specialClinicNewPatRegistration(EpisodeVO _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO);// each

	// VisitStampVO
	// keeps
	// PatientVO

	public String getNewDeptVisitAmount(String crNo, String primCatCode, String deptcode, UserVO _userVO);

	public String[] getDeptsForRenewal(String crNo, UserVO _userVO);

	public boolean saveRenewalDtl(EpisodeVO _episodeVO, UserVO _userVO);

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 */
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo, UserVO _userVO, String visitType);

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 */
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo, UserVO _userVO, String visitType, String isRenewal, String patCatagoryCode, String tariffId, String serviceId,String episodeType);
	
	public EpisodeVO[] searchOldPatientEmgEpisodesByCrNo(String crNo, UserVO _userVO, String visitType, String renewalTariffId, String patPrimaryCatCode, String registrationServiceId,String isRenewal);

	public boolean patDtlModificationMRD(PatientVO _patientVO,PatientVO _patientVOOldData, UserVO _userVO, VerificationDocumentVO _verDoc);

	public boolean saveRenewalDtl(EpisodeVO[] _episodeVO, UserVO _userVO);

	public EpisodeVO[] saveNewSpVisiStamp(EpisodeVO[] episodeVO, UserVO userVO, PatientVO patientVO, RegChargeDtlVO regChargeDtlVO,
			EpisodeRefDtlVO episodeRefDtlVO,PatientVO _oldPatientVO);

	public DailyPatientVO searchDailyPatientByCrNo(DailyPatientVO dailyPatientVO, UserVO userVO);
	
	public DailyPatientVO searchDailyPatientByCrNoWithoutHospital(DailyPatientVO dailyPatientVO, UserVO userVO);

	public PatientVO[] searchPatientByNationalID(String nationalID, UserVO _userVO);

	public PatientVO[] searchPatientByContactNo(String contactNo, UserVO _userVO);

	public PatientVO[] searchPatientByEmployeeID(String employeeID, UserVO _userVO);

	public void saveRenewalDetail(PatientVO _patientVO, String _amount, UserVO _userVO, String _newExpiryDate);

	//public void saveDeptWiseRenewalDetail(EpisodeVO[] selectedEpisodeVO, String sysDate, UserVO userVO);

	public EpisodeVO getEpisodeVisitByCrno(String _patCrNo, String _visitDate, String _unitCode,UserVO userVO);

	public void saveDiagnosisDetails(EpisodeVO episodeVO, EpisodeDiagnosisVO[] episodeDiagnosisVO, UserVO userVO,String _episodeStatusInVO);

	public void saveMlcDocUpload(MlcDocUploadDtlVO[] _mlcDocUploadDtlVOs, MlcVO[] _mlcVo, UserVO userVO);

	public VerificationDocumentVO[] getVerificationDocumentDtl(VerificationDocumentVO verficationVo, UserVO userVO);

	/*
	 * public PatientVO checkForRenewalAtSaveToPatintDao(PatientVO _patientVO,UserVO _userVO,JDBCTransactionContext tx);
	 * public void checkForRenewalAtSaveToEpisodeDao(EpisodeVO episodeVO,UserVO _userVO,String
	 * deptUnitCode,JDBCTransactionContext tx);
	 */

	public EpisodeRefDtlVO[] getReferPat(UserVO _userVO);

	public EpisodeRefDtlVO[] getSCReferPat(UserVO _userVO);

	public MlcVO[] getMlcDtlArrayBasedOnCrNo(String crNo, UserVO _uservo);

	public MlcVO[] getMlcDtlArrayBasedOnMlcNo(String mlcNo, UserVO _uservo);

	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String _patCrNo, UserVO _userVO);

	public void saveTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO);

	public void modifyTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO);

	public EpisodeTriageDetailVO getPatTriageDtl(String _crNo, String _epiCode, String _visitNo, UserVO _userVO);

	public EpisodeVO[] saveReprintCard(RegCardPrintVO[] regCardPrintVO, UserVO userVO);

	public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNo(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO);

	// public void saveVerificationDoc(VerificationDocumentVO[] verficationVoArray, MlcVO[] mlcVO, UserVO userVO);

	public EpisodeVO retrieveByEpisodeNo(EpisodeVO _epiVO, UserVO _userVO);

	// public Map getAllEpisodesForDuplicateReprint(String crNo,UserVO userVO);

	public EpisodeRefDtlVO[] getOldPatReferDtl(UserVO userVO);

	public EpisodeVO[] searchOldPatientEpisodesByCrNoByDept(String crNo, String dept, UserVO _userVO, String visitType);

	public EpisodeVO[] searchOldPatientEpisodesByCrNoByUnit(String crNo, String unit, UserVO _userVO, String visitType);

	public Map getAllEpisodesForDuplicateReprint(String crNo, UserVO userVO, String _choice);

	public void saveParichitDetails(PatientParichitVO _patParichitVO, UserVO _userVO);

	public List getPrimaryCatListExceptPatientCat(String patCat, UserVO userVO);

	//public EpisodeVO[] saveDeptWiseRenewalAndStaming(PatientVO _patVO, EpisodeVO[] _selectedEpisodeVO, EpisodeVO[] _arrRenewalEpisodeVO,
			//EpisodeVO[] _arrEpisodeVO, EpisodeRefDtlVO _episodeRefVO, String _sysDate, UserVO _userVO);

	/** List of All Open MLC Episode of the Patient
	 * @param crNo Patient CR Number
	 * @param _userVO User VO
	 * @return
	 */
	public EpisodeVO[] getAllOpenTodayMLCEpisodes(String _patCrNo, UserVO _userVO);

	/** Revoking MLC Episodes 
	 * @param _mlcRevVO MLC Revoking VO
	 * @param _userVO User VO
	 * @return true if Revoked otherwise false
	 */
	public boolean revokeMLCEpisodes(MLCRevokeEpisodeDetailVO[] _mlcRevVOs, UserVO _userVO);
	
	/** For Changing the room of the patient 
	 * @param _mlcRevVO MLC Revoking VO
	 * @param _userVO User VO
	 * @return true if Revoked otherwise false
	 */
	
	public void changePatientRoom(RoomChangeVO _roomChangeVO,EpisodeVO _episodeVO,DailyPatientVO _dailyPatientVO,UserVO _userVO,String _unitCode);
	
	
	/** For getting the Patient Audit Trail 
	 * @param _mlcRevVO MLC Revoking VO
	 * @param _userVO User VO
	 * @return true if Revoked otherwise false
	 */
	public Map getPatientAuditTrailEssentials(String patCrNo,UserVO _userVO);
	
	 public void savePatientAttendedUnderObservation(EpisodeActionDtlVO _episodedtlActionDtlVO,UserVO _userVO);
	 
	 public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNoNew(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO);
	 
	 public Map checkEligibleForMLC(PatientVO _patVO, UserVO _userVO);
	 
	 public void savePoliceVerification(PoliceVerificationDtlVO policeVerDtlVO,UserVO userVO);
	 
	 public PoliceVerificationDtlVO getPoliceVerDtl(MlcVO mlcVO, UserVO userVO);
	 
	 public void savePatientDeathDetail(ANCDetailVO ancDetailVO, PatientDeathDetailVO patDeathDtlVO,UserVO userVO);
	 
	 public boolean checkRecordAdded(String crNo,UserVO userVO);
	 
	 public PatientDeathDetailVO getDeathDetailByCrNo(String crNo,UserVO userVO);
	 
	 public AddressVO getPatAddress(String crNo,UserVO userVO);
	 
	 public Map getHandoverToDetail(String crNo,UserVO userVO);
	
/**
## 		Modification Log		: saveHandoverToDetail					
##		Modify Date				: 07-01-2015	
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh 
*/	
	 
	 public void saveHandoverToDetail(PatientDeathDetailVO patDeathDtlVO,String flagForPrint,UserVO userVO);
	 
	 public PatientDeathDetailVO[] getDeadPatientList(UserVO userVO);
	 
	 public String getPatientMlcNo(PatientDetailVO selectedPatientVO,UserVO userVO);
	 
	 public ANCDetailVO getPatientANCDetail(String crNo,UserVO userVO);
	 
	 public DocumentUploadDtlVO[] getMlcUploadDetail(MlcVO _mlcVO,UserVO _userVO);
	 
	 public void saveDocumentDetail(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVOs, UserVO userVO);
	 
	 public EpisodeVO[] getPatientAdmittedEpisodes(String crNo, UserVO _userVO);
	 
	 public void revokeSecondaryCategory(SecondaryCategoryChangeVO[] _secCatChangeVO, UserVO _userVO);

	public MlcVO[] getMLCDetail(PatientVO patVO, UserVO userVO);
	
	public void saveRedirectOfOPDPatDtl(List deptUnitRosterModDtlVOList,List roomChnageVOList,UserVO _userVO);
	
	public EpisodeVO[] saveOfflineRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO);
	
	public void saveDeactivateUnitRoomDtl(List deptUnitRosterModDtlVOList,List deptUnitRosterModDtlVOInActiveList,UserVO _userVO);
	
	public DailyPatientVO[] getTodayPatientListByDeptUnitCode(String mode, String departmentUnitCode, UserVO _userVO);
	
	public void saveFileNumberPrinting(EpisodeVO[] episodeVOs, RegCardPrintVO[] regCardPrintVOs, UserVO _userVO);

	public EpisodeVO[] oldDeptVisitStampRoomWise(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefDtlVO,UserVO _userVO );
	
	//public EpisodeVO[] newDepartmentVisitStampRoomWiseForDuplicate(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO);
	
	//public EpisodeVO saveEmgRenewalAndStamping(PatientVO _patientvo,EpisodeVO[] _episodevo, String sysDate, UserVO _uservo);

	/**
	 * Modifies patient+address details. Also more addresses can be added. Updates the record in Patient & Address dtl
	 * table.
	 * 
	 * Cretaed By Pragya at 08-Aug-2011
	 */
	public int patientDetailModification(PatientVO _patientVO, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			VerificationDocumentVO _verDoc, UserVO _userVO);
	
	public Map savePatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,EpisodeVO[] _episodeNewDeptVO);
	public Map saveSplPatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,EpisodeVO[] _episodeNewDeptVO);
	public EpisodeVO[] saveEmgOldPatientVisit(PatientVO patientVO,
			EpisodeVO[] episodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO);
	public Map saveEmergenyPatientVisitStamping(PatientVO patientVO,
			EpisodeVO[] oldVisitEpisodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] newVisitEpisodeVO, RegChargeDtlVO regChargeDtlVO);
	
	public PatientAdhaarDtlVO checkDupAdhaarPatient(PatientVO _patientVO, UserVO _userVO);
	
	public boolean saveMCTSRegNo(MCTSRegistrationVO MCTSRegistrationVO_p , UserVO _userVO);
	/**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table. Provides age of the patient according to
	 * the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 */
	public PatientVO retrieveByCrNoFromMCTS(PatientVO _patientVO, UserVO _userVO);
	
	public EpisodeVO[] getAllLatestEpisodesVisited(String _patCrNo, UserVO _userVO);
	
	public List<PatientDetailVO> getPatientsForPoliceExaminationReqt(String _patCrNo, UserVO _userVO);
	public void savePoliceExaminationReqtDtl(
			PoliceExaminationReqtDtlVO policeExamReqtDtlVO, UserVO userVO);
	public List<PatientDetailVO> getPatientsForPoliceExaminationReportGeneration(
			String strMode_p, String strPatCrNo_p, String strEpisode_p,
			String strEpisodeVisitNo_p, UserVO objUserVO_p);
	public void savePoliceExaminationReportGenerationDtl( PoliceExaminationReqtDtlVO objPoliceExamReqtDtlVO_p, UserVO objUserVO_p);
	
	//Added by Vasu
	public void savePatientDocumentDetail(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVOs,vo.registration.PatientVO patientVO,UserVO userVO);
	public byte[] fetchImageFromPostgres(PatientImageDtlVO patientImageDtlVO);
}
