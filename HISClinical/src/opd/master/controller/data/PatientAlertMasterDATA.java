package opd.master.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientAlertMasterVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import opd.bo.delegate.OpdMasterDelegate;

public class PatientAlertMasterDATA extends ControllerDATA  
{
	public static Map getPatientAlertEssentials(UserVO _userVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.getPatientAlertEssentials(_userVO);		
	}
	public static void savePatientAlert(PatientAlertMasterVO _patientAlertMasterVO,
			UserVO _userVO) 
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.savePatientAlert(_patientAlertMasterVO,_userVO);
	}
	public static Map fetchPatientAlertEssentials(UserVO _userVO){			
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchPatientAlertEssentials(_userVO);		
	}
	
	
	public static PatientAlertMasterVO fetchPatientAlertModify(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		return masterDelegate.fetchPatientAlertModify(_patientAlertMasterVO,_userVO);
	}
	
	public static void modifySave(PatientAlertMasterVO _patientAlertMasterVO, UserVO _userVO)
	{
		OpdMasterDelegate masterDelegate = new OpdMasterDelegate();
		masterDelegate.modifySavePatientAlert(_patientAlertMasterVO,_userVO);
	}

}
