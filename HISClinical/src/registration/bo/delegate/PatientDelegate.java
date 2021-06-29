package registration.bo.delegate;

import hisglobal.business.Delegate;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.presentation.Status;
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
import hisglobal.vo.MLCRevokeEpisodeDetailVO;
import hisglobal.vo.MlcDocUploadDtlVO;
import hisglobal.vo.MlcVO;
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
import hisglobal.vo.PatientAdhaarDtlVO;
import hisglobal.vo.MCTSRegistrationVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import registration.bo.EssentialBOi;
import registration.bo.PatientBO;
import registration.bo.PatientBOi;
import registration.vo.BPLDetailsVO;


/**
 * PatientDelegate is a class which describes the processes for all the transactions, 
 * which in turn call the methods from PatientBO class which specifies the business logic for all the transactions.
 * PatientDelegate class provides a standard interface between Controller and Business Objects.
 * @author AHIS
 */
public class PatientDelegate extends Delegate
{
	/**
	 * Creates a new PatientDelegate object.
	 * Sets the service provider.
	 */
	public PatientDelegate()
	{
		super(new PatientBO()); ///<<Setting the service provider
	}

	/**
	 * Registers a patient when he/she visits the hospital for the first time.
	 * Generates the CR No of the Patient.
	 * Calculates age of the patient if DOB is provided and DOB if age is provided.
	 * Saves data in Patient Dtl, Address dtl, Daily Patient dtl, and Episode dtl tables.
	 * @param	_arrEpisodeVO[]	Provides the departments in which patient has been registered.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] newPatRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO  ,HttpServletRequest _request) 
	{
		//System.out.println(" in side new patreg delegate");
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newPatRegistration(_arrEpisodeVO, _patientVO, _userVO, _request));
		//null is passed for PatientBroughtByDetailVO
		//return (serviceBO.registerNewPatient(_arrEpisodeVO, _patientVO, null, _userVO, RegistrationConfig.NEW_REGISTRATION_PROCESS));
	}// each VisitStampVO keeps PatientVO

	/**
	 * Registers a patient when he/she visits the hospital for the first time.
	 * Generates the CR No of the Patient.
	 * Calculates age of the patient if DOB is provided and DOB if age is provided.
	 * Saves data in Patient Dtl, Address dtl, Daily Patient dtl, and Episode dtl tables.
	 * @param	_arrEpisodeVO[]	Provides the departments in which patient has been registered.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] newPatSplRegistration(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO  ,HttpServletRequest _request) 
	{
		//System.out.println(" in side new patreg delegate");
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newPatSplRegistration(_arrEpisodeVO, _patientVO, _userVO, _request));
		//null is passed for PatientBroughtByDetailVO
		//return (serviceBO.registerNewPatient(_arrEpisodeVO, _patientVO, null, _userVO, RegistrationConfig.NEW_REGISTRATION_PROCESS));
	}// eac
	/**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table.
	 * Provides age of the patient according to the DOB stored in DB.
	 * Sets all the values to null in case the patient is Unknown.
	 * @param	_patientVO	Provides CR No to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	PatientVO with values stored in DB.
	 */
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchPatientByCrNo(_patientVO, _userVO));
	}

	/**
	 * Retrieves patient details on the basis of CR No and visit type from the Patient Dtl Table. Provides age of the patient
	 * according to the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param _patientVO Provides CR No to be searched.
	 * @param _userVO Provides User details.
	 * @param _visitType Specifies visit type of the patient.
	 * @return PatientVO with values stored in DB.
	 */
	public PatientVO searchPatientByCrNo(PatientVO _patientVO, UserVO _userVO, String _visitType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchPatientByCrNo(_patientVO, _userVO, _visitType));
	}

	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	patStatus. Sets patStatus=11 if Patient status if IPD, patStatus=12 is Patient status is OPD, patStatus=13 if Patient is dead.
	 */
	public String checkPatientStatus(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.checkPatientStatus(_patientVO, _userVO));
	}

	/**
	 * Retrieves all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 * @param	_userVO		Provides User details.
	 * @return	 Array of PatientVO populated with CR No.
	 */
	public PatientVO[] getCrNoForModification(UserVO _userVO, String episodeVisitType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getCrNoForModification(_userVO, episodeVisitType));
	}

	/**
	 * Retrieves all the CR No entered during last 10 minutes from the Patient Dtl Table.
	 * @param	_userVO		Provides User details.
	 * @return	 List of CR No.
	 */
	public List getCrNoModification(UserVO _userVO, Status _objStatus)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getCrNoModification(_userVO));
	}

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and isConfirmed status.
	 * Provides last visit details of all episodes which are open presently.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @param	visitType	Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @param	isConfirmed	Specifies whether visit is confirmed.
	 * @return	PatientVO with values stored in DB.
	 */
	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO, UserVO _userVO, String visitType, String isConfirmed)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchPatientEpiosdeByCrNo(_patientVO, _userVO, visitType, isConfirmed));
	}

	/**
	 * Stamps the visit of a patient when he/she visits a department for the first time.
	 * Saves data in Daily Patient dtl, and Episode dtl tables.
	 * @param	_arrEpisodeVO[]	Provides the departments in which patient has been registered.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] newDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO));
	}

	public EpisodeVO[] newDepartmentVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO, PatientVO _oldPatientVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newDepartmentVisitStamp(_arrEpisodeVO, _patientVO, episodeRefVO, _userVO,_oldPatientVO));
	}

	public EpisodeVO[] newSplDepartmentVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO, PatientVO _oldPatientVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newSplUnitVisitStamp(_arrEpisodeVO, _patientVO, episodeRefVO, _userVO,_oldPatientVO));
	}
	public EpisodeVO[] newDepartmentVisitStampRoomWise(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, EpisodeRefDtlVO episodeRefVO, UserVO _userVO,PatientVO _oldPatientVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newDepartmentVisitStampRoomWise(_arrEpisodeVO, _patientVO, episodeRefVO, _userVO,_oldPatientVO));
	}
	
	/**
	 * Stamps the visit of a patient when he/she re-visits a department.
	 * Saves data in Daily Patient dtl, and Episode dtl tables.
	 * @param	_arrEpisodeVO[]	Provides the departments in which patient has been registered.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] oldDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO, UserVO _userVO, EpisodeRefDtlVO episodeRefDtlVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.oldDeptVisitStamp(_arrEpisodeVO, _patientVO, _userVO, episodeRefDtlVO));
	}

	/**
	 * Changes primary and secondary categories of a patient.
	 * Updates the record in Patient dtl.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if records are updated successfully otherwise <code>false</code>.
	 * @deprecated Replaced by PatientDelegate.changePatientCategory().
	 */
	public boolean patCategoryChange(PatientVO _patientVO, EpisodeVO[] _arrEpisodeVO, ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.patCategoryChange(_patientVO, _arrEpisodeVO, _arrChangeCategoryVO, _userVO));
	}

	/**
	 * Checks whether patient record is modifiable at registration desk or not.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if time-duration less than 10 min otherwise <code>false</code>.
	 */
	public boolean checkModify(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.checkModify(_patientVO, _userVO));
	}

	/**
	 * Modifies patient details at Registration Desk. 
	 * Modification at registration can be done with in 10 minutes of registration.
	 * Updates the record in Patient dtl and Address dtl tables. 
	 * Also updates patient's demographic details in Daily Patient Detail table.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	number of records updated.
	 */
	/*public boolean patDtlModificationREG(PatientVO _patientVO,PatientVO _patientVOOldData, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.patDtlModificationREG(_patientVO,_patientVOOldData, _userVO));
	}*/

	/**
	 * Modifies patient details at MRD Desk.
	 * Updates the record Patient dtl and Address dtl tables.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	number of records updated.
	 */

   /* public boolean patDtlModificationMRD(PatientVO _patientVO, UserVO _userVO){
    	PatientBOi serviceBO = (PatientBOi)super.getServiceProvider();
    	return (serviceBO.patDtlModificationMRD(_patientVO, _userVO));
    }*/
    
    
    public boolean patDtlModificationMRD(PatientVO _patientVO,PatientVO _patientVOOldData, UserVO _userVO,VerificationDocumentVO _verDocVO){
    	PatientBOi serviceBO = (PatientBOi)super.getServiceProvider();
    	return (serviceBO.patDtlModificationMRD(_patientVO,_patientVOOldData, _userVO,_verDocVO));
    }
    /**

	/* public boolean patDtlModificationMRD(PatientVO _patientVO, UserVO _userVO){
	 	PatientBOi serviceBO = (PatientBOi)super.getServiceProvider();
	 	return (serviceBO.patDtlModificationMRD(_patientVO, _userVO));
	 }*/

	/*public boolean patDtlModificationMRD(PatientVO _patientVO, UserVO _userVO, VerificationDocumentVO _verDocVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.patDtlModificationMRD(_patientVO, _userVO, _verDocVO));
	}*/

	/**

	 * Retrieves all the addresses of a patient from Address Dtl Table.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Array of AddressVO with all the addresses of the patient.
	 */
	public AddressVO[] getPatAddressAll(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getPatAddressAll(_patientVO, _userVO));
	}

	/**
	 * Checks whether an address entry has to be made in the DB or an existing address is to be modified.
	 * @param	_arrAddressVO[]	Provides all the addresses to be added or modified.
	 * @param	_patientVO	Provides Patient details.
	 * @param	addVO	Stores address which is to be modified.
	 * @param	_userVO		Provides User details.
	 * @return	AddressVO with address to be modified.
	 * @deprecated	No replacement.
	 */
	public AddressVO checkAddModiy(AddressVO[] _arrAddressVO, PatientVO _patientVO, AddressVO addVO, UserVO _userVO, String add_modify)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.checkAddModify(_arrAddressVO, _patientVO, addVO, _userVO, add_modify));
	}

	/**
	 * Modifies patient address details at MRD Desk.
	 * Also more addresses can be added.
	 * Updates the record in Address dtl table.
	 * @param	_arrAddressVO[] Provides addresses to be added or modified.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	Number of records updated.
	 */
	/*public int patAddressDtlModification(AddressVO _arrAddressVO, VerificationDocumentVO _verificationDocumentVO, UserVO _userVO,
			String _addTypeCode, String _addModify)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.patAddressDtlModification(_arrAddressVO, _verificationDocumentVO, _userVO, _addTypeCode, _addModify));
	}*/

	/**
	 * Retrieves patient details. Reserved for future use.
	 * @param	_patientVO	Provides CR No to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	PatientVO with values stored in DB.
	 */
	public PatientVO getPatientDtl(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientDtl(_patientVO, _userVO));
	}

	/**
	 * Retrieves patient details on the basis of Patient Name. Reserved for future use.
	 * @param	_patientName	Provides name to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Array of PatientVO with values stored in DB.
	 */
	public PatientVO[] searchPatientByName(String _patientName, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchPatientByName(_patientName, _userVO));
	}

	/**
	 * Retrieves patient details on the basis of Id Noif the patient is an employee of the hospital.
	 * Reserved for future use.
	 * @param	_idNo	Provides Id No to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	PatientVO with values stored in DB.
	 */
	public PatientVO searchPatientByIdNo(String _idNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchPatientByIdNo(_idNo, _userVO));
	}

	/**
	 * Retrieves patient details on the basis of Previous CR No alloted in the previous system from the previous DB.
	 * @param	_patientVO	Provides CR No to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	PatientVO with values stored in DB.
	 */
	public PatientVO getPrevSystemPatDetail(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getPrevSystemPatDetail(_patientVO, _userVO));
	}

	/**
	 * Facilitates issue of duplicate card for an OPD to a patient.
	 * Also enters the details of duplicate card in the DB.
	 * @param	_episodeVO	Provides all the episodes which are opened for a patient.
	 * @param	_duplicateRenewVO	Provides duplicate card details.
	 * @param	_userVO		Provides User details.
	 * @return	DuplicateRenewVO with values stored in DB.
	 */
	public EpisodeVO[] duplicateCardPrinting(RegChargeDtlVO[] _regChargeVO, RegCardPrintVO[] _regCardPrintVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.duplicateCardPrinting(_regChargeVO, _regCardPrintVO, _userVO);
	}

	/**
	 * Facilitates the change of unit if a particular unit is not on duty 
	 * or if the change of unit is required under some special conditions.
	 * Can only performed from MRD desk. 
	 * Also saves the details of unit change in the DB.
	 * @param	_patientVO	Provides patient's details.
	 * @param	_episodeVO	Provides the episode for which unit has to be changed.
	 * @param	_unitChangeVO	Provides details required for unit change.
	 * @param	_userVO		Provides User details.
	 * @return	UnitChangeVO with values stored in DB.
	 */
	public UnitChangeVO unitChangeOldDeptVisit(PatientVO _patientVO, EpisodeVO _episodeVO, UnitChangeVO _unitChangeVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.unitChangeOldDeptVisit(_patientVO, _episodeVO, _unitChangeVO, _userVO));
	}

	/**
	 * Registers a patient when he/she visits the hospital for the first time in an Emergency.
	 * Generates the CR No of the Patient.
	 * Calculates age of the patient if DOB is provided and DOB if age is provided.
	 * Saves data in Patient Dtl, Address dtl, Daily Patient dtl, and Episode dtl tables.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	EpisodeVO with values stored in DB.
	 */
	public EpisodeVO emergencyPatRegistration(PatientVO _patientVO, PatientBroughtByDtlVO _paBroughtByDtlVO, UserVO _userVO, HttpServletRequest _request)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.emergencyPatRegistration(_patientVO, _paBroughtByDtlVO, _userVO, _request));
		//EpisodeVO[] episodeVO= (serviceBO.registerNewPatient(null,_patientVO, _paBroughtByDtlVO, _userVO, RegistrationConfig.EMERGENCY_REGISTRATION_PROCESS));
		//return episodeVO[0];
	}

	/**
	 * Stamps the visit of a patient when he/she re-visits the hosital in an emergency.
	 * New episode is started whenever a patient visits in emergency.
	 * Saves data in Daily Patient dtl, and Episode dtl tables.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	EpisodeVO with values stored in DB.
	 */
	public EpisodeVO emergencyOldDeptVisitStamp(PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.emergencyOldDeptVisitStamp(_patientVO, _episodeVO, _userVO));
	}

	/**
	 * Modifies patient details at emergency registration desk. 
	 * Modification at registration can be done with in 10 minutes of registration.
	 * Updates the record in Patient dtl and Address dtl tables. 
	 * Also updates patient's demographic details in Daily Patient Detail table.
	 * @param	_patientVO	Provides Patient details.
	 * @param patientBroughtByDtlVO 
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if records are updated otherwise <code>false</code>.
	 */
	public boolean emergencyPatDtlModification(PatientVO _patientVO, PatientBroughtByDtlVO patientBroughtByDtlVO,PatientVO _patientVOOldData, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.emergencyPatDtlModificationREG(_patientVO,patientBroughtByDtlVO,_patientVOOldData, _userVO));
	}

	/**
	 * Saves MLC details of a patient. Deatils are entered by the CMO.
	 * Saves data in Patient MLC Dtl table.
	 * Updates mlc no in Episode dtl table.
	 * @param	_mlcVO	Provides MLC details.
	 * @param	_userVO		Provides User details.
	 * @return	MlcVO with values stored in DB.
	 */
	public MlcVO patMlcDtl(MlcVO _mlcVO, UserVO _userVO, PatientBroughtByDtlVO _patBroughtByDtlVO, PoliceVerificationDtlVO policeVerDtlVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.patMlcDtl(_mlcVO, _userVO, _patBroughtByDtlVO, policeVerDtlVO));
	}

	/**
	 * Retrieves patient MLC details on the basis of CR No from the Patient MLC Dtl Table.
	 * @param	_patientVO	Provides patient details.
	 * @param	_mlcVO	Provides MLC no no and episode no.
	 * @param	_userVO		Provides User details.
	 * @return	MlcVO with values stored in DB.
	 */
	public MlcVO getMlcDtl(PatientVO _patientVO, MlcVO _mlcVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getMlcDtl(_patientVO, _mlcVO, _userVO));
	}

	/**
	 * Creates a new entry in MLC dtl table each time a record is to be modified so as to keep track of all modifications.
	 * @param	_mlcVO	Provides MLC details.
	 * @param	_userVO		Provides User details.
	 */
	public void modifyMlcDtl(MlcVO _mlcVO, UserVO _userVO, PatientBroughtByDtlVO _PatientBroughtByDtlVO,PoliceVerificationDtlVO policeVerDtlVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.modifyMlcDtl(_mlcVO, _userVO, _PatientBroughtByDtlVO,policeVerDtlVO);
	}

	public void savePatientNotAttendedDetail(EpisodeActionDtlVO epActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientNotAttendedDetail(epActionDtlVO, _userVO);
	}

	public void savePatientAttendedAdmittedDetail(PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientAttendedAdmittedDetail(_patVO, _userVO);
	}

	public void saveDeathDetail(EpisodeDeathVO _episodeDeathVO, PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveDeathDetail(_episodeDeathVO, _patVO, _userVO);
	}

	public void saveBroughtDeathDetail(EpisodeDeathVO _episodeDeathVO, DailyPatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveBroughtDeathDetail(_episodeDeathVO, _patVO, _userVO);
	}

	public void savePatientReferredOut(EpisodeReferVO _episodeReferVO, EpisodeActionDtlVO episodedtlActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientReferredOut(_episodeReferVO, episodedtlActionDtlVO, _userVO);
	}

	public void savePatientAttendedDisposed(EpisodeDiagnosisVO _episodeDiagVO[], EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientAttendedDisposed(_episodeDiagVO, _episodedtlActionDtlVO, _userVO);
	}

	public void savePatientAttendedObserved(EpisodeDiagnosisVO _episodeDiagVO[], EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientAttendedObserved(_episodeDiagVO, _episodedtlActionDtlVO, _userVO);
	}

	public void savePatientAttendedRefInCasuality(EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientAttendedRefInCasuality(_episodedtlActionDtlVO, _userVO);
	}

	public void savePatientRefInWard(EpisodeActionDtlVO _episodedtlActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientRefInWard(_episodedtlActionDtlVO, _userVO);
	}

	public EpisodeVO isEmergency(PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.isEmergency(_patVO, _userVO));
	}

	public void saveOpdVisitConfirm(EpisodeVO _episodeVO, EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveOpdVisitConfirm(_episodeVO, _episodeActionDtlVO, _userVO);
	}

	public void saveOpdEpisodeClose(EpisodeVO _episodeVO, EpisodeActionDtlVO _episodeActionDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveOpdEpisodeClose(_episodeVO, _episodeActionDtlVO, _userVO);
	}

	public EpisodeVO[] isOpd(PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.isOpd(_patVO, _userVO));
	}

	public MlcVO getMlcDtlMlcNo(MlcVO _mlcVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getMlcDtlBasedOnMlcNo(_mlcVO, _userVO);
	}

	public MlcVO getMlcDtlBasedOnCrNo(MlcVO vo, UserVO _uservo)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getMlcDtlBasedOnCrNo(vo, _uservo);
	}

	public EpisodeVO isMlcEligible(PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.isMlcEligible(_patVO, _userVO));
	}

	// Checking if MLC is already added
	public EpisodeVO isCsultyMlcEligible(PatientVO _patVO, String _episodeCode, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.isCsultyMlcEligible(_patVO, _episodeCode, _userVO));
	}

	public EpisodeVO getEmgOpenEpisode(PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getEmgOpenEpisode(_patVO, _userVO));
	}

	public EpisodeVO[] searchPatientEpiosdeByCrNo(PatientVO _patientVO, UserVO _userVO, String visitType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchPatientEpiosdeByCrNo(_patientVO, _userVO, visitType));
	}

	/**
	 * Converts an unknown patient to a known patient. 
	 * Modifies deatils of the patient. Can ber done only by the authorized personnel.
	 * Updates the record in Patient dtl and Address dtl tables. 
	 * Also creates an entry in Unknown Change Detail table that stores the information regarding when the patient became known.
	 * @param	_patientVO	Provides Patient details.
	 * @param patientVOOldData 
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if records are updated otherwise <code>false</code>.
	 */
	public boolean unknownToKnownConversion(PatientVO _patientVO,PatientVO patientVOOldData, VerificationDocumentVO verifDocVO,UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.unknownToKnownConversion(_patientVO,patientVOOldData,verifDocVO, _userVO));
	}

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @param	visitType	Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @return	EpisodeVO with values stored in DB.
	 */
	public EpisodeReferVO searchPatientRefEpisodeByEpisodeNo(PatientVO _patientVO, EpisodeVO _episodeVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchPatientRefEpisodeByEpisodeNo(_patientVO, _episodeVO, _userVO));
	}

	/**
	 * Modifies refer details of a patient at MRD. 
	 * Can be done only by the authorized personnel.
	 * Updates the record in Patient dtl, Episode dtl and Episode Refer dtl tables. 
	 * @param	_episodeVO	Provides Episode details of the patient.
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if records are updated otherwise <code>false</code>.
	 */
	public boolean referDtlModificationMRD(PatientVO _patientVO, EpisodeReferVO _episodeReferVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.referDtlModificationMRD(_patientVO, _episodeReferVO, _userVO));
	}

	/**
	 * Stamps the visit of a patient when he/she visits a department in which he/she is referred from some other department.
	 * Saves data in Daily Patient dtl, Episode dtl and Episode Refer dtl tables.
	 * @param	_arrEpisodeVO[]	Provides the departments in which patient has been registered.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] referredDeptVisitStamp(EpisodeVO[] _arrEpisodeVO, EpisodeReferVO _episodeReferVO, PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.referredDeptVisitStamp(_arrEpisodeVO, _episodeReferVO, _patientVO, _userVO));
	}

	public UnknownChangeVO getConvertedToKnownDetails(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getConvertedToKnownDetails(_patientVO, _userVO));
	}

	/**
	 * Retrieves refer internal details of a patient from the Episode Refer Dtl Table.
	 * @param	_patientVO Provides patient's CR No.
	 * @param	_userVO		Provides User details.
	 * @return	 Array of EpisodeReferVO containing internal referral details.
	 */
	public EpisodeReferVO[] retrieveRefInternalEpisodeDtl(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.retrieveRefInternalEpisodeDtl(_patientVO, _userVO));
	}

	public MlcVO[] getMlcDtlBasedOnCrNoandImage(MlcVO _mlcVo, UserVO _uservo)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getMlcDtlBasedOnCrNoandImage(_mlcVo, _uservo));
	}

	public void saveMlcDoc(MlcVO _mlcVo, UserVO _uservo)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveMlcDoc(_mlcVo, _uservo);
	}

	/**
	 * Checks patient's current status in the hospital. Should be called only after the patient details has been retrieved.
	 * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @param	visitType	Specifies the visit type, whether its OPD, IPD or Emergency. 
	 * @return	<code>true</code> if patient status is according to the visit type otherwise <code>false</code>.
	 */
	public boolean checkPatientStatusByVisitType(PatientVO _patientVO, UserVO _userVO, String visitType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.checkPatientStatusByVisitType(_patientVO, _userVO, visitType));
	}

	/**
	 * Retrieves all episode details of the patient fron Episode Dtl Table.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] searchAllEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchAllEpisodeByCrNo(_patientVO, _userVO));

	}

	/**
	 * 
	 * @param _patientVO
	 * @param _userVO
	 * @return
	 */

	public MlcVO isPatientMlc(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.isPatientMlc(_patientVO, _userVO));
	}

	/**
	 * Checks whether currently any episode is opened for a patient in emergency on the basis of CR No.
	 * Also checks that the episodes should be valid and active.
	 * @param	_patientVO	Provides CR No of the patient whose address is to be searched.
	 * @param	_userVO		Provides User details.
	 */
	public boolean checkOpenEmgEpisodeByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.checkOpenEmgEpisodeByCrNo(_patientVO, _userVO));

	}

	/**
	 * Changes primary and secondary categories of a patient.
	 * Updates the record in Patient dtl.
	 * @param	_arrChangeCategoryVO	Provides change of category details.
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if records are updated successfully otherwise <code>false</code>.
	 */
	/*  public boolean changePatientCategory(ChangeCategoryVO[] _arrChangeCategoryVO, UserVO _userVO){
	  	PatientBOi serviceBO = (PatientBOi)super.getServiceProvider();
		return(serviceBO.changePatientCategory(_arrChangeCategoryVO,_userVO));	
		
	  }*/

	public EpisodeVO getLastEpisodeInEmergency(PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getLastEpisodeInEmergency(_patVO, _userVO));

	}

	/**
	 * Retrieves all referred episodes of the patient fron Episode Dtl Table.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] retrieveAllReferredEpisodesByCrNo(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.retrieveAllReferredEpisodesByCrNo(_patientVO, _userVO));
	}

	/**
	 * Retrieves all currently opened episodes of a patient in OPD.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Array of AddressVO with all the addresses of the patient.
	 */
	public EpisodeReferVO[] getAllOpenOPDEpisodes(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getAllOpenOPDEpisodes(crNo, _userVO));
	}

	public EpisodeRefDtlVO[] retrieveAllOpenOPDEpisodes(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.retrieveAllOpenOPDEpisodes(crNo, _userVO));
	}

	/**
	 * Retrieves all currently opened episodes of a patient in Hospital.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @return	Array of EpisodeVO with all the open episodes of the patient.
	 */
	public EpisodeRefDtlVO[] retrieveAllSpecialEpisodes(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.retrieveAllSpecialEpisodes(crNo, _userVO));
	}

	public Map getAllOpenEpisodes(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getAllOpenEpisodes(crNo, _userVO));
	}

	/**
	 * Changes primary category of a patient.
	 * Updates the record in Patient Dtl and Episode Dtl table.
	 * @param	_primCatChangeVO	Provides change of category details.
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if records are updated successfully otherwise <code>false</code>.	 * 
	 */
	public boolean changePrimaryCategory(PrimaryCategoryChangeVO _primCatChangeVO, UserVO _userVO, VerificationDocumentVO _veriDocVO,
			EmpMasterVO _empMasterVO, String _patIdNo)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.changePrimaryCategory(_primCatChangeVO, _userVO, _veriDocVO, _empMasterVO, _patIdNo));
	}

	/**
	 * Changes secondary categories of a patient for open episodes.
	 * Updates the record in Episode Dtl table.
	 * @param	_secCatChangeVO	Provides change of category details.
	 * @param	_userVO		Provides User details.
	 * @return	<code>true</code> if records are updated successfully otherwise <code>false</code>.
	 */
	public boolean changeSecondaryCategory(SecondaryCategoryChangeVO[] _secCatChangeVO,SecondaryCategoryChangeVO[] secCatRevokeVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.changeSecondaryCategory(_secCatChangeVO,secCatRevokeVO, _userVO));
	}

	public EpisodeVO[] changeFileNo(FileNumberChangeVO[] _fileNoChangeVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.changeFileNo(_fileNoChangeVO, _userVO);
	}

	public boolean saveRenewalDtl(EpisodeVO[] _episodeVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.saveRenewalDtl(_episodeVO, _userVO));
	}

	/*public List getListDeptWiseFileNo(String crNo, UserVO _userVO){
		PatientBOi serviceBO = (PatientBOi)super.getServiceProvider();
		return(serviceBO.getListDeptWiseFileNo(crNo,_userVO));
	}*/

	public Map getMapDeptWiseFileNo(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getMapDeptWiseFileNo(crNo, _userVO));
	}

	public EpisodeVO[] getMapDeptWiseFileNoChange(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getMapDeptWiseFileNoChange(crNo, _userVO));
	}

	public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, UserVO _userVO, String patPrimaryCode)
	{

		// public EpisodeVO[] getDeptWiseRenewalOfRegistration(String crNo, String primCatCode, UserVO _userVO){

		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();

		return (serviceBO.getDeptWiseRenewalOfRegistration(crNo, _userVO, patPrimaryCode));

		//return(serviceBO.getDeptWiseRenewalOfRegistration(crNo, primCatCode, _userVO));

	}

	public String getNewDeptVisitAmount(String crNo, String primCatCode, String deptcode, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getNewDeptVisitAmount(crNo, primCatCode, deptcode, _userVO);
	}

	public String[] getDeptsForRenewal(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getDeptsForRenewal(crNo, _userVO);
	}

	public EpisodeVO[] saveNewSpVisiStamp(EpisodeVO[] episodeVO, UserVO userVO, PatientVO patientVO, RegChargeDtlVO regChargeDtlVO,
			EpisodeRefDtlVO episodeRefDtlVO,PatientVO _oldPatientVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.saveNewSpVisiStamp(episodeVO, userVO, patientVO, regChargeDtlVO, episodeRefDtlVO,_oldPatientVO);

	}

	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @param	visitType	Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo, UserVO _userVO, String visitType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchOldPatientEpisodesByCrNo(crNo, _userVO, visitType);

	}
	/**
	 * Retrieves episode details of the patient fron Episode Dtl Table depending upon the visit type and episode not closed.
	 * Provides last visit details of all episodes which are open presently.
	 * @param	_patientVO	Provides CR No of the patient for which episode details are to be searched.
	 * @param	_userVO		Provides User details.
	 * @param	visitType	Specifies type of visit, namely, IPD, OPD, or EMG.
	 * @param serviceId 
	 * @param tariffId 
	 * @param patCatagoryCode 
	 * @param isRenewal 
	 * @return	Array of EpisodeVO with values stored in DB.
	 */
	public EpisodeVO[] searchOldPatientEpisodesByCrNo(String crNo, UserVO _userVO, String visitType, String isRenewal, String patCatagoryCode, String tariffId, String serviceId,String episodeType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchOldPatientEpisodesByCrNo(crNo, _userVO, visitType,isRenewal,patCatagoryCode,tariffId,serviceId,episodeType);

	}
	
	public PatientVO[] searchPatientByNationalID(String nationalID, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchPatientByNationalID(nationalID, _userVO);
	}

	public PatientVO[] searchPatientByContactNo(String contactNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchPatientByContactNo(contactNo, _userVO);
	}

	public PatientVO[] searchPatientByEmployeeID(String employeeID, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchPatientByEmployeeID(employeeID, _userVO);
	}

	
	

	public VerificationDocumentVO[] getVerificationDocumentDtl(VerificationDocumentVO verficationVoArray, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getVerificationDocumentDtl(verficationVoArray, userVO);

	}

	
	/*public EpisodeVO[] searchOldPatientEmgEpisodesByCrNo(String crNo, UserVO _userVO, String visitType){
		PatientBOi serviceBO = (PatientBOi)super.getServiceProvider();		
		return serviceBO.searchOldPatientEmgEpisodesByCrNo(crNo, _userVO, visitType);
		
	}*/
    
	
	public DailyPatientVO serchDailyPatientByCrNo(DailyPatientVO dailyPatientVO, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchDailyPatientByCrNo(dailyPatientVO, userVO);
	}

		
	public DailyPatientVO serchDailyPatientByCrNoWithoutHospital(DailyPatientVO dailyPatientVO, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchDailyPatientByCrNoWithoutHospital(dailyPatientVO, userVO);
	}	

	

	public EpisodeVO[] getAllOpenEpisodesVisitedToday(String _patCrNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getAllOpenEpisodesVisitedToday(_patCrNo, _userVO);
	}

	
    public void saveRenewalDetail(PatientVO _patientVO, String _amount, UserVO _userVO, String _newExpiryDate)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveRenewalDetail(_patientVO, _amount, _userVO, _newExpiryDate);
	}
	
	
	 
	 public PatientVO[] searchPatient(HashMap _searchMap, UserVO _userVO)
		{
			PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
			return serviceBO.searchPatient(_searchMap, _userVO);
		}
    
   
    
  /*  public void saveDeptWiseRenewalDetail(EpisodeVO[] selectedEpisodeVO, String sysDate, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveDeptWiseRenewalDetail(selectedEpisodeVO, sysDate, userVO);
	}
    */
      
    public EpisodeVO getEpisodeVisitByCrno(String _patCrNo, String _visitDate, String _unitCode, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getEpisodeVisitByCrno(_patCrNo, _visitDate, _unitCode,userVO);
	}

    
    public  void saveDiagnosisDetails(EpisodeVO episodeVO,EpisodeDiagnosisVO[] episodeDiagnosisVO,UserVO userVO,String _episodeStatusInVO){
    	PatientBOi serviceBO = (PatientBOi)super.getServiceProvider();
    	serviceBO.saveDiagnosisDetails(episodeVO,episodeDiagnosisVO,userVO,_episodeStatusInVO);
    	
    }
    
  
    
    public void saveMlcDocUpload(MlcDocUploadDtlVO[] _mlcDocUploadDtlVOs, MlcVO[] mlcVO, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveMlcDocUpload(_mlcDocUploadDtlVOs, mlcVO, userVO);

	}
   

    
    public EpisodeRefDtlVO[] getReferPat(UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getReferPat(_userVO);
	}

	
    
   
    public EpisodeRefDtlVO[] getSCReferpat(UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getSCReferPat(_userVO);

	}
    
  
    
    public MlcVO[] getMlcDtlArrayByCrNo(String crNo, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getMlcDtlArrayBasedOnCrNo(crNo, userVO);

	}

 
    public MlcVO[] getMlcDtlArrayByMlcNo(String mlcNo, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getMlcDtlArrayBasedOnMlcNo(mlcNo, userVO);

	}
    
   
    



	//* Saving Triage Details
	public void saveTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveTriageDetails(_triVO, _userVO);
	}

	//* Modify Triage Details
	public void modifyTriageDetails(EpisodeTriageDetailVO _triVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.modifyTriageDetails(_triVO, _userVO);
	}

	//* Getting Patient Triage Detail
	public EpisodeTriageDetailVO getPatTriageDtl(String _crNo, String _epiCode, String _visitNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPatTriageDtl(_crNo, _epiCode, _visitNo, _userVO);
	}

	//* Getting Episode Detail
	public EpisodeVO retrieveByEpisodeNo(EpisodeVO _epiVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.retrieveByEpisodeNo(_epiVO, _userVO);
	}

	public EpisodeVO[] saveReprintCard(RegCardPrintVO[] regCardPrintVO, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.saveReprintCard(regCardPrintVO, userVO);
	}

	public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNo(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchPatientBropughtByDetailByCrNo(_patBroughtByDtlVO, _userVO);
	}

	public Map getAllEpisodesForDuplicateReprint(String crNo, UserVO userVO, String _choice)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getAllEpisodesForDuplicateReprint(crNo, userVO, _choice);
	}

	public EpisodeRefDtlVO[] getOldPatReferDtl(UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getOldPatReferDtl(userVO);
	}

	public EpisodeVO[] searchOldPatientEpisodesByCrNoByDept(String crNo, String dept, UserVO _userVO, String visitType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchOldPatientEpisodesByCrNoByDept(crNo, dept, _userVO, visitType);

	}

	public EpisodeVO[] searchOldPatientEpisodesByCrNoByUnit(String crNo, String unit, UserVO _userVO, String visitType)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchOldPatientEpisodesByCrNoByUnit(crNo, unit, _userVO, visitType);

	}

	public void saveParichitDetails(PatientParichitVO _patParichitVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveParichitDetails(_patParichitVO, _userVO);
	}

	public List getPrimaryCatListExceptPatientCat(String patCat, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPrimaryCatListExceptPatientCat(patCat, userVO);
	}

	/*public EpisodeVO[] saveDeptWiseRenewalAndStaming(PatientVO _patVO, EpisodeVO[] _selectedEpisodeVO, EpisodeVO[] _arrRenewalEpisodeVO,
			EpisodeVO[] _arrEpisodeVO, EpisodeRefDtlVO _episodeRefVO, String _sysDate, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.saveDeptWiseRenewalAndStaming(_patVO, _selectedEpisodeVO, _arrRenewalEpisodeVO, _arrEpisodeVO, _episodeRefVO, _sysDate,
				_userVO);
	}
*/
	/** List of All Open MLC Episode of the Patient
	 * @param crNo Patient CR Number
	 * @param _userVO User VO
	 * @return
	 */
	public EpisodeVO[] getAllOpenTodayMLCEpisodes(String _patCrNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getAllOpenTodayMLCEpisodes(_patCrNo, _userVO);
	}

	/** Revoking MLC Episodes 
	 * @param _mlcRevVO MLC Revoking VO
	 * @param _userVO User VO
	 * @return true if Revoked otherwise false
	 */
	public boolean revokeMLCEpisodes(MLCRevokeEpisodeDetailVO[] _mlcRevVOs, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.revokeMLCEpisodes(_mlcRevVOs, _userVO));
	}
	
	public void changePatientRoom(RoomChangeVO _roomChangeVO,EpisodeVO _episodeVO,DailyPatientVO _dailyPatientVO,UserVO _userVO,String _unitCode)
	{
		
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.changePatientRoom(_roomChangeVO,_episodeVO,_dailyPatientVO,_userVO,_unitCode);
	}
	
	
	public Map getPatientAuditTrailEssentials(String patCrNo,UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPatientAuditTrailEssentials( patCrNo, _userVO);
	}

	public PatientBroughtByDtlVO getpatientBroughtByDetail(PatientBroughtByDtlVO patientBroughtByDtlVO, UserVO _userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchPatientBropughtByDetailByCrNoNew(patientBroughtByDtlVO, _userVO);
	}
	
	public void savePatientAttendedUnderObservation(EpisodeActionDtlVO _episodedtlActionDtlVO,UserVO _userVO){
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientAttendedUnderObservation( _episodedtlActionDtlVO, _userVO);
	}
	
	public PatientBroughtByDtlVO searchPatientBropughtByDetailByCrNoNew(PatientBroughtByDtlVO _patBroughtByDtlVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.searchPatientBropughtByDetailByCrNoNew(_patBroughtByDtlVO, _userVO);
	}
	
	public Map checkEligibleForMLC(PatientVO _patVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.checkEligibleForMLC(_patVO, _userVO));
	}
	
	public void savePoliceVerification(PoliceVerificationDtlVO policeVerDtlVO,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePoliceVerification(policeVerDtlVO, userVO);
	}
	
	public PoliceVerificationDtlVO getPoliceVerDtl(MlcVO mlcVO, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPoliceVerDtl(mlcVO, userVO);
	}
	
	public void savePatientDeathDetail(ANCDetailVO ancDetailVO, PatientDeathDetailVO patDeathDtlVO,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientDeathDetail(ancDetailVO, patDeathDtlVO, userVO);
	}
	
	public boolean checkRecordAdded(String crNo,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.checkRecordAdded(crNo, userVO);
	}
	
	public PatientDeathDetailVO getDeathDetailByCrNo(String crNo,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getDeathDetailByCrNo(crNo, userVO);
	}
	
	public AddressVO getPatAddress(String crNo,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPatAddress(crNo, userVO);
	}
	
	//handoverTo detail 

	public Map getHandoverToDetail(String crNo,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getHandoverToDetail(crNo,userVO));
	}

	public void saveHandoverToDetail(PatientDeathDetailVO patDeathDtlVO,String flagForPrint,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveHandoverToDetail(patDeathDtlVO,flagForPrint,userVO);
	}
	
	public PatientDeathDetailVO[] getDeadPatientList(UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getDeadPatientList(userVO));
	}
	
	public String getPatientMlcNo(PatientDetailVO selectedPatientVO,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientMlcNo(selectedPatientVO,userVO));
	}
	
	public ANCDetailVO getPatientANCDetail(String crNo,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getPatientANCDetail(crNo,userVO));
	}

	public DocumentUploadDtlVO[] getMlcUploadDetail(MlcVO _mlcVO,UserVO _userVO){
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.getMlcUploadDetail(_mlcVO,_userVO));
	}
	
	public void savePatientDocumentdetails(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVO, UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveDocumentDetail(docUploadVos,documentUploadRevokeDtlVO, userVO);
	}

	
	public EpisodeVO[] getPatientAdmittedEpisodes(String crNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPatientAdmittedEpisodes(crNo, _userVO);
	}
	
	public void revokeSecondaryCategory(SecondaryCategoryChangeVO[] _secCatChangeVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.revokeSecondaryCategory(_secCatChangeVO, _userVO);
	}

	public MlcVO[] getMLCDetail(PatientVO patVO, UserVO userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getMLCDetail(patVO, userVO);
	}
	
	public void saveRedirectOfOPDPatDtl(List deptUnitRosterModDtlVOList,List roomChnageVOList,UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveRedirectOfOPDPatDtl(deptUnitRosterModDtlVOList,roomChnageVOList,_userVO);
	}

	public EpisodeVO[] saveOfflineRegistration(EpisodeVO[] arrEpisodeVO,
			PatientVO patientVO, UserVO userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.saveOfflineRegistration(arrEpisodeVO,patientVO,userVO);
		//return (serviceBO.registerNewPatient(arrEpisodeVO, patientVO, null, userVO, RegistrationConfig.ROOM_WISE_REGISTRATION_PROCESS));
	}

	/*public EpisodeVO[] newDepartmentVisitStampForDuplicate(	EpisodeVO[] _arrEpisodeVO, PatientVO _patientVO,
			EpisodeRefDtlVO episodeRefVO, UserVO _userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newDepartmentVisitStampForDuplicate(_arrEpisodeVO, _patientVO, episodeRefVO, _userVO));
		}  */
	
	public void saveDeactivateUnitRoomDtl(List deptUnitRosterModDtlVOList,List deptUnitRosterModDtlVOInActiveList,UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveDeactivateUnitRoomDtl(deptUnitRosterModDtlVOList,deptUnitRosterModDtlVOInActiveList,_userVO);
	}
	
	public DailyPatientVO[] getTodayPatientListByDeptUnitCode(
			String mode, String departmentUnitCode, UserVO userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getTodayPatientListByDeptUnitCode(mode,departmentUnitCode,userVO);
	}

	public void saveFilePrintDetail(EpisodeVO[] episodeVOs, RegCardPrintVO[] regCardPrintVOs, UserVO userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.saveFileNumberPrinting(episodeVOs,regCardPrintVOs,userVO);
	}

	public EpisodeVO[] saveOldPatientVisitRoomWise(EpisodeVO[] _episodevo,
			PatientVO _patvo, UserVO _uservo, EpisodeRefDtlVO episodeRefDtlVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.oldDeptVisitStampRoomWise(_episodevo,_patvo,episodeRefDtlVO,_uservo);
	}
	/*public EpisodeVO[] newDepartmentVisitStampRoomWiseForDuplicate(
			EpisodeVO[] arrEpisodeVO, PatientVO patientVO,
			EpisodeRefDtlVO episodeRefVO, UserVO userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.newDepartmentVisitStampRoomWiseForDuplicate(arrEpisodeVO, patientVO, episodeRefVO, userVO));
	}

	public EpisodeVO saveEmgRenewalAndStamping(PatientVO _patientVO,EpisodeVO[] _episodeVO, String sysDate, UserVO _userVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.saveEmgRenewalAndStamping(_patientVO,_episodeVO, sysDate , _userVO));
		
	}*/

	public Map savePatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,
			UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,
			EpisodeVO[] _episodeNewDeptVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.savePatientVisit(_episodeVO, _patVO, _userVO, _episodeRefDtlVO, _episodeNewDeptVO));
	}
	public Map saveSplPatientVisit(EpisodeVO[] _episodeVO, PatientVO _patVO,
			UserVO _userVO, EpisodeRefDtlVO _episodeRefDtlVO,
			EpisodeVO[] _episodeNewDeptVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.saveSplPatientVisit(_episodeVO, _patVO, _userVO, _episodeRefDtlVO, _episodeNewDeptVO));
	}

	public EpisodeVO[] searchOldPatientEmgEpisodesByCrNo(String patCrNo,
			UserVO userVO, String episodeVisitTypeEmg, String renewalTariffId,
			String patPrimaryCatCode, String registrationServiceId,String isRenewal) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.searchOldPatientEmgEpisodesByCrNo(patCrNo,userVO,episodeVisitTypeEmg,renewalTariffId,patPrimaryCatCode,registrationServiceId,isRenewal));
	}

	public EpisodeVO[] saveEmgOldPatientVisit(PatientVO patientVO,
			EpisodeVO[] episodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.saveEmgOldPatientVisit(patientVO,episodeVO,userVO,episodeRefDtlVO));
	}

	public Map saveEmergenyPatientVisitStamping(PatientVO patientVO,
			EpisodeVO[] oldVisitEpisodeVO, UserVO userVO,
			EpisodeRefDtlVO episodeRefDtlVO, EpisodeVO[] newVisitEpisodeVO, RegChargeDtlVO regChargeDtlVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.saveEmergenyPatientVisitStamping(patientVO,oldVisitEpisodeVO,userVO,episodeRefDtlVO,newVisitEpisodeVO,regChargeDtlVO));
	}
	
	/**
	 * Modifies patient+address details. Also more addresses can be added. Updates the record in Patient & Address dtl
	 * table.
	 * 
	 * @param _arrAddressVO[] Provides addresses to be added or modified.
	 * @param _patientVO Provides Patient details.
	 * @param _userVO Provides User details.
	 * @return Number of records updated.
	 * Cretaed By Pragya at 08-Aug-2011
	 */
	public int patientDetailModification(PatientVO _patientVO, PatientVO _patientVOOldData, String addModify, AddressVO _arrAddressVO, 
			VerificationDocumentVO _verDoc, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.patientDetailModification(_patientVO, _patientVOOldData, addModify, _arrAddressVO, _verDoc,_userVO));
	}
	
	/**
	 * Retrieves a patient when he/she visits the hospital with adhaar id avaible
     * @param	_patientVO	Provides Patient details.
	 * @param	_userVO		Provides User details.
	 * @return	 PatientAdhaarDtlVO with values stored in DB.
	 */
	public PatientAdhaarDtlVO checkDupAdhaarPatient(PatientVO _patientVO, UserVO _userVO)
	{
		//System.out.println(" in side new patreg delegate");
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.checkDupAdhaarPatient( _patientVO, _userVO));
		//null is passed for PatientBroughtByDetailVO
		//return (serviceBO.registerNewPatient(_arrEpisodeVO, _patientVO, null, _userVO, RegistrationConfig.NEW_REGISTRATION_PROCESS));
	}// each VisitStampVO keeps PatientVO
	
	public boolean saveMCTSRegNo(MCTSRegistrationVO MCTSRegistrationVO_p, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.saveMCTSRegNo(MCTSRegistrationVO_p,_userVO));
	}
	
	public PatientVO retrieveByCrNoFromMCTS(PatientVO _patientVO, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return (serviceBO.retrieveByCrNoFromMCTS(_patientVO, _userVO));
	}
	
	public EpisodeVO[] getAllLatestEpisodesVisited(String _patCrNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getAllLatestEpisodesVisited(_patCrNo, _userVO);
	}
	
	public List<PatientDetailVO> getPatientsForPoliceExaminationReqt(String _patCrNo, UserVO _userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPatientsForPoliceExaminationReqt(_patCrNo, _userVO);
	}
	
	public void savePoliceExaminationReqtDtl(PoliceExaminationReqtDtlVO objPoliceExamReqtDtlVO_p, UserVO objUserVO_p)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePoliceExaminationReqtDtl(objPoliceExamReqtDtlVO_p, objUserVO_p);
	}

	public List<PatientDetailVO> getPatientsForPoliceExaminationReportGeneration(String strMode_p,
			String strPatCrNo_p,String strEpisode_p, String strEpisodeVisitNo_p, UserVO objUserVO_p) {
		
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		return serviceBO.getPatientsForPoliceExaminationReportGeneration(strMode_p,strPatCrNo_p,strEpisode_p,strEpisodeVisitNo_p,objUserVO_p);
	}
	public void savePoliceExaminationReportGenerationDtl(PoliceExaminationReqtDtlVO objPoliceExamReqtDtlVO_p, UserVO objUserVO_p)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePoliceExaminationReportGenerationDtl(objPoliceExamReqtDtlVO_p, objUserVO_p);
	}
	//Added by Vasu 
	public void savePatientUploadedDocumentdetails(DocumentUploadDtlVO[] docUploadVos,DocumentUploadDtlVO[] documentUploadRevokeDtlVO,vo.registration.PatientVO patientVO,UserVO userVO)
	{
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		serviceBO.savePatientDocumentDetail(docUploadVos,documentUploadRevokeDtlVO,patientVO, userVO);
	}

	public byte[] fetchImageFromPostgres(PatientImageDtlVO patientImageDtlVO) {
		PatientBOi serviceBO = (PatientBOi) super.getServiceProvider();
		byte[] getdoc = serviceBO.fetchImageFromPostgres(patientImageDtlVO); 
		return getdoc;
	}

}// end of class


