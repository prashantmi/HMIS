package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;

import java.util.Map;

public class PendingTaskListDATA extends ControllerDATA 
{
	public static Map getPendingTasks(String deptUnitCode,String wardCode,String roomCode,UserVO _userVO)
	{
		InPatientEssentialDelegate inPatientEssentialDelegate= new InPatientEssentialDelegate();
		return inPatientEssentialDelegate.getPendingTasks(deptUnitCode,wardCode,roomCode,_userVO);
	}
	
	
}
