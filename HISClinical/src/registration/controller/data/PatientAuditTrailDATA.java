package registration.controller.data;


import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.Status;

import java.util.List;
import java.util.Map;


import registration.bo.delegate.PatientDelegate;
import hisglobal.vo.UserVO;


public class PatientAuditTrailDATA  extends ControllerDATA 
{
		
	public static Map getPatientAuditTrailEssentials(String patCrNo,UserVO _userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();		
		return patDelegate.getPatientAuditTrailEssentials(patCrNo,_userVO);
	}
	
	

	
	
}
