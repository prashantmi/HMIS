package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.testAvailabilityDelegate;
import new_investigation.vo.testAvailabilityVO;


public class testAvailabilityDATA
{
	 
	
	public static Map LabComboForMachineResultEntry(testAvailabilityVO resultentryvo, UserVO _UserVO)
	{
		testAvailabilityDelegate masterDelegate = new testAvailabilityDelegate();
		return masterDelegate.LabComboForMachineResultEntry(resultentryvo, _UserVO);
	}
	

	public static Map getPatientMachineResultEntry(testAvailabilityVO resultentryvo, UserVO _UserVO)
	{
		testAvailabilityDelegate masterDelegate = new testAvailabilityDelegate();
		return masterDelegate.getPatientMachineResultEntry(resultentryvo, _UserVO);
	}
	
	

		public static Map saveMachineResultEntry(List<testAvailabilityVO> resultEntry,UserVO _userVO,HttpServletRequest _request)
		{
			testAvailabilityDelegate masterDelegate = new testAvailabilityDelegate();
			return masterDelegate.saveMachineResultEntry(resultEntry,  _userVO,  _request);
		}
	
		

} 
