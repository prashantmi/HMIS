package registration.dao;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.PrimaryCategoryChangeVO;
import hisglobal.vo.UserVO;

import java.util.List;
/**
 * PatientDAOi is an interface that declares CRUD methods associated with HRGT_PATIENT_DTL table.
 * @author AHIS
 */
public interface PatientDAOi{
	
	/**
	 * Creates a new patient entry in DB for a patient. Generates a 12-digit CR No using a SQL Procedure.
	 * When patient's age is entered, isActualDob flag is reset and DOB is calulated for the patient to be stored in the Db.
	 * When patient's DOB is entered, isActualDob flag is set.
	 */
	public PatientVO create(HisDAO daoObj,PatientVO _patientVO, UserVO _userVO);

	/**
	 * Updates already stored patient record in the DB.
	 * When patient's age is entered, isActualDob flag is reset and DOB is calulated for the patient to be stored in the Db.
	 * When patient's DOB is entered, isActualDob flag is set.
	 */
	public int update(HisDAO daoObj, PatientVO _patientVO, UserVO _userVO);
	

	/**
	 * Calculates the timestamp of a record entered for a patient. 
	 * Whenpatient's age is entered, isActualDob flag is reset and DOB is calulated
	 * for the patient to be stored in the Db. When patient's DOB is entered, isActualDob flag is set.
	 */
	public float checkTimeStamp(PatientVO _patientVO, UserVO _userVO);


    /**
     * Retrieves the details of a patient on the basis of CR No.
     * Calculates the age of the patient as on today.
     */
	public PatientVO retrieveByCrNo(PatientVO _patientVO, AddressVO _addressVO, UserVO _userVO);

    /**
     * Retrieves the the CR No of all patients who have been registered with in last 10 minutes.
     * Used for modification at Registration Desk.
     */
	public PatientVO[] retrieveCrNoForModification(UserVO _userVO,String episodeVisitType);

    /**
     * Retrieves the the CR No of all patients who have been registered with in last 10 minutes.
     * Used for modification at Registration Desk.
     */
	public List getCrNoForModification(UserVO _userVO);

    /**
     * Retrieves the patient details from another DB system on the basis of  Previous CR No.
     * Calculates the age of the patient as on today.
     */
	public PatientVO previousSystemCrNo(PatientVO _patientVO, AddressVO _addressVO, UserVO _userVO);

    /**
     * Retrieves all the patients with same name.
     * Used for modification at Registration Desk.
     */
	public PatientVO[] retrieveByName(String _patientName, AddressVO[] _addressVO,  UserVO _userVO);	   

    /**
     * Changes primary and secondary catgories of a patient.
     */	
    public int changePatientPrimaryCat(PatientVO _patientVO, UserVO _userVO,String _patIdNo);

    /**
	 * Updates patient refer details at MRD.
	 */
	public int updateRefDtlAtMRD(PatientVO _patientVO, UserVO _userVO);

	public PatientVO[] retrieveByContactNo(String contactNo, AddressVO[] _addressVO, UserVO _userVO);

	public PatientVO[] retrieveByNationalID(String nationalID, AddressVO[] _addressVO, UserVO _uservo);
	
	
	  /**
	 * Patient Audit Trail Methods
	 */
	
	
	public PatientDetailVO getPatientHrgtEssentials(String patCrNo,UserVO _userVO);
	
	
	public PatientDetailVO[] getPatientAuditEssentials(String patCrNo,UserVO _userVO);
	
	
	public AddressVO[] getPatientAddressDetailsEssentials(String patCrNo,UserVO _userVO);
	

	public PrimaryCategoryChangeVO[] getPrimaryCategotyChangeDetailsEssentials(String _patCrNo,UserVO _userVO);
	
	// Getting Patient Additional Information (for the ANC Process)
	public PatientDetailVO getPatientAdditionalInformation(String _patCrNo, UserVO _userVO);
	
	// Updating Patient Additional Information (for the ANC Process)
	public void updatePatientAdditionalInformation(PatientDetailVO _patDtlVO, UserVO _userVO);
	// update patient caste & areaType (according to jsy Detail)
	//public void updatePatientCasteAreaType(PatientDetailVO pDetailVO,UserVO _userVO);

}//end of interface