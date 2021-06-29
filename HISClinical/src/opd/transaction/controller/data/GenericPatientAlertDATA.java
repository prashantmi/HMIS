package opd.transaction.controller.data;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.UserVO;

public class GenericPatientAlertDATA extends ControllerDATA
{
	/** Getting All The Assigned Alerts of the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static List<PatientAlertsDetailVO> getPatientAssignedAlert(String crNo,UserVO userVO)
	{
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		return patientDelegate.getPatientAssignedAlert(crNo,userVO);
	}
	
	/** List of Alert Names that are not assigned to the Patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static List getAllPatAlerts(String crNo,UserVO userVO)
	{
		OpdEssentialDelegate essentialDelegate = new OpdEssentialDelegate();
		return essentialDelegate.getAllPatAlerts(crNo,userVO);
	}
	
	/**  Saving the Patient Alerts & Revoking
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public static void savePatientAlerts(List<PatientAlertsDetailVO> lstAddedPatAlerts,List<PatientAlertsDetailVO> lstRevokedPatAlerts,UserVO userVO)
	{
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		patientDelegate.savePatientAlerts(lstAddedPatAlerts,lstRevokedPatAlerts,userVO);
	}
	
	/** Revoking the Alert of the Patient
	 * @param patAlertDetailVO
	 * @param userVO
	 */
	public static void revokeAlerts(PatientAlertsDetailVO patAlertDetailVO,UserVO userVO)
	{
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		patientDelegate.revokeAlerts(patAlertDetailVO,userVO);
	}
	
	/** Getting All The Alert of The patient
	 * @param crNo
	 * @param userVO
	 * @return
	 */
	public static PatientAlertsDetailVO[] getAllPatientAlert(String crNo,UserVO userVO)
	{
		OpdPatientDelegate  patientDelegate= new OpdPatientDelegate();
		return patientDelegate.getAllPatientAlert(crNo,userVO);
	}
}
