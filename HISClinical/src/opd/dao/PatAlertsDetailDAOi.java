package opd.dao;

import java.util.List;

import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

public interface PatAlertsDetailDAOi
{
	/**  Getting All The Assigned Alerts of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public List<PatientAlertsDetailVO> getPatientAssignedAlert(String crNo,UserVO userVO);
	
	/**  Saving the Patient Alerts
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public void create(PatientAlertsDetailVO patAlertDetailVO,UserVO userVO);
	
	/** Revoking the Patient Alert
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public void updateAlert(PatientAlertsDetailVO patAlertDetailVO,UserVO userVO);
	
	/** Getting All The Alert of The patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public PatientAlertsDetailVO[] getAllPatientAlert(String crNo,UserVO userVO);
	

	/** Getting Alert of The patient for EMR
	 * @param crNo
	 * @param accessType 
	 * @param departmentUnitArray 
	 * @param userVO
	 * @return
	 */
	public PatientAlertsDetailVO[] getPatientAlertEMR(String crNo,String[] departmentUnitArray, String accessType, UserVO userVO);
	

	public PatientAlertsDetailVO[] getPatientAlertsDetail(String _crNo,UserVO _userVO, PatientDetailVO voDP, String profileGenerationMode);

}
