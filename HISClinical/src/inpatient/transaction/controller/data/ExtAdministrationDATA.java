package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

public class ExtAdministrationDATA extends ControllerDATA 
{
	public static Map getPatExtTreatmentDetailEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getExtAdministrationEssential(patientDetailVO, _userVO);

	}
	
	public static void saveExtTreatment(List actvityList,List extAdminVoList ,UserVO _userVO)

	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveExtTreatmentExe(actvityList,extAdminVoList,_userVO);
	}
}
