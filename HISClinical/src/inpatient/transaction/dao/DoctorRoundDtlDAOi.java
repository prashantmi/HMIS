package inpatient.transaction.dao;

import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public interface DoctorRoundDtlDAOi 
{
	/** Inserting Data
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void create(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);
	
	/**
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
	
	/**
	 * @param unverifiedRecordVO
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO getRecordDetail(DoctorRoundDtlVO unverifiedRecordVO,UserVO userVO);
	
	/** Updating Nurse Visit Notes & Saving Doctor Instruction,Progress Notes 
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void updateVerifiedData(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);
	
	/** Saving Notes Entered By Doctor
	 * @param docRoundDtlVO
	 * @param userVO
	 */
	public void saveNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);

	/** Getting Doctor Prev Round Dtl on the Basis of Admission No
	 * @param patAdmNo
	 * @param userVO
	 * @return
	 */
	public DoctorRoundDtlVO[] getDoctorPrevRoundDetail(String patAdmNo, UserVO userVO);
	
	/** Getting Patient Progress Notes Entered By Doctor
	 * @param patCrNo
	 * @param userVO
	 * @return DoctorRoundDtlVO[]
	 */
	public DoctorRoundDtlVO[] getPatientProgressNotesEMR(String patCrNo,String[] departmentUnitArray, String accessType, UserVO userVO);
	
	
	
	public DoctorRoundDtlVO[] getPatientProgressNotes(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);
	
	//Added by Vasu on 26.Sept.2018 to update doctor Notes
	public void updateNotesByDoctor(DoctorRoundDtlVO docRoundDtlVO,UserVO userVO);
}
