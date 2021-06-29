package registration.dao;

import hisglobal.vo.DailyPatientVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.RoomChangeVO;
import hisglobal.vo.UserVO;
/**
 * DailyPatientDAOi is an interface that declares CRUD methods associated with HRGT_DAILY_PATIENT_DTL table.
 * @author AHIS
 */
public interface DailyPatientDAOi {
	
	/**
	 * Creates a new DailyPatientDAO object.
	 */	
    //create is synonymous to insert
	public DailyPatientVO create(DailyPatientVO  _dailyPatientVO,UserVO _userVO);	

	/**
	 * Creates a new entry in DB for each patient when the patient visits a department in which he/she is registered
	 * but visit has yet been stamped.
	 * Generates the token details, namely, que no, unit no and room no of the patient.
	 */
	public DailyPatientVO createOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO);

	/**
	 * Creates a new entry in DB for each patient when the patient re-visits a department.
	 * Patient is alloted the same unit no and room no as the previous visit.
	 */
	public DailyPatientVO stampOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO);

	/**
     * Retrieves the token details for a department as on today. Provides the que no for the particular unit and room no of a department.
     */
	public DailyPatientVO getTokenDtlOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO);

	/**
	 * Creates a new entry in DB for each patient when the patient re-visits the hospital under emergency.
	 * Generates the episode number for the new episode of the patient.
	 */
	public DailyPatientVO createEmgOldDeptVisit(DailyPatientVO _dailyPatientVO, UserVO _userVO);
	
	
	//public PatientVO retrieveByCrNo(String _crNo);	
	//public PatientVO[] retrieveByName(String _patientName);	
	
	public DailyPatientVO searchPatientByCrNo(DailyPatientVO _dailyPatientVO,UserVO _userVO);
	
	public String getQueNumber(UserVO _userVO,String _unitCode,String _roomCode);
	
	public void changeDailyPatientDetails(DailyPatientVO _dailyPatientVO,UserVO _userVO);
	
	//public String getPatientsVisitCount(UserVO _userVO,String patCrNo);
	
	public PatientDetailVO getPatDetailForJSYRegistration(String patCrNo, UserVO _userVO);
	
	public int updateChangeRoomDetail(RoomChangeVO roomChangeVO,UserVO _userVO);
	
	//public DailyPatientVO createNewOfflineRegistration(DailyPatientVO _dailyPatientVO, UserVO _userVO);
	
	public DailyPatientVO[] getTodayPatientListByDeptUnitCode(String mode,String departmentUnitCode, UserVO _userVO);

	public void updateFilePrintFlag(DailyPatientVO dailyPatientVO, UserVO _userVO);

}
