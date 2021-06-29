package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.Map;

public class DischargeRequestDATA extends ControllerDATA
{
	
	public static boolean getPatientStatus(String admNo,UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getPatientStatus(admNo,userVO); 
	}
	public static PatDischargeReqDtlVO getDischargeRemarks(String admNo,UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getDischargeRemarks(admNo,userVO); 
	}
	
	
	public static void saveDischargeRequest(String patStatus,PatDischargeReqDtlVO patDischargeReqVO,UserVO userVO)
	{
		InpatientDelegate delegate= new InpatientDelegate();
		delegate.saveDischargeRequest(patStatus,patDischargeReqVO,userVO); 
	}
	
	public static Map getDischargeStatusListNProfileStatus(PatientDetailVO patientVO,UserVO userVO)
	{
		InPatientEssentialDelegate essDelegate=new InPatientEssentialDelegate();
		return essDelegate.getDischargeStatusListNProfileStatus(patientVO, userVO);
	}
	
}
