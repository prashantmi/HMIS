package registration.controller.data;

import java.util.Map;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.UserVO;
import registration.bo.delegate.PatientDelegate;


public class HandoverToDeadBodyDATA extends ControllerDATA{

	public static Map getHandoverToDetail(String crNo,UserVO userVO)
	{
		PatientDelegate delegate = new PatientDelegate();
		return (delegate.getHandoverToDetail(crNo,userVO));
	}
	
	public static void saveHandoverToDetail(PatientDeathDetailVO patDeathDtlVO,String flagForPrint,UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		patDelegate.saveHandoverToDetail(patDeathDtlVO,flagForPrint,userVO);
	}
	
	public static PatientDeathDetailVO[] getDeadPatientList(UserVO userVO)
	{
		PatientDelegate patDelegate = new PatientDelegate();
		return (patDelegate.getDeadPatientList(userVO));
	}
	
	
}
