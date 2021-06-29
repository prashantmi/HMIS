package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;

public class NursingStationPatListDATA extends ControllerDATA
{
	public static PatientDetailVO[] getAdmittedPatientList(String roomCode,String unitCode,String wardCode,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getAdmittedPatientList(roomCode,unitCode,wardCode,userVO);
	}
}
