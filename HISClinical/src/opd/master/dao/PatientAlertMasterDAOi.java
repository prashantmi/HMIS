package opd.master.dao;

import hisglobal.vo.PatientAlertMasterVO;
import hisglobal.vo.UserVO;

public interface PatientAlertMasterDAOi {

	public void create(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);
	
	public boolean checkDuplicateBeforeSave(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);
	
	public PatientAlertMasterVO fetchPatientAlertModify(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);
	
	public boolean  checkDuplicateBeforeModifySave(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);
	
	public void modify(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);
	
	public void modifyInsert(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO);
	

}
