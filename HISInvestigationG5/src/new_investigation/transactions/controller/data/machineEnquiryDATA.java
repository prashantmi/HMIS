package new_investigation.transactions.controller.data;

import hisglobal.vo.UserVO;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import new_investigation.transactions.delegate.machineEnquiryDelegate;
import new_investigation.vo.machineEnquiryVO;


public class machineEnquiryDATA
{
	 
	
	public static Map getMachineBasedSampleNo(machineEnquiryVO resultentryvo, UserVO _UserVO)
	{
		machineEnquiryDelegate masterDelegate = new machineEnquiryDelegate();
		return masterDelegate.getMachineBasedSampleNo(resultentryvo, _UserVO);
	}
	
	public static Map getMachineComboForEnquiry(machineEnquiryVO resultentryvo, UserVO _UserVO)
	{
		machineEnquiryDelegate masterDelegate = new machineEnquiryDelegate();
		return masterDelegate.getMachineComboForEnquiry(resultentryvo, _UserVO);
	}
	 
	public static Map getPatientmachineEnquiry(machineEnquiryVO resultentryvo, UserVO _UserVO)
	{
		machineEnquiryDelegate masterDelegate = new machineEnquiryDelegate();
		return masterDelegate.getPatientmachineEnquiry(resultentryvo, _UserVO);
	}

		

} 
