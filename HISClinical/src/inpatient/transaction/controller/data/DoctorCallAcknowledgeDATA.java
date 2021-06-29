package inpatient.transaction.controller.data;

import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;


public class DoctorCallAcknowledgeDATA 

{
	public static DoctorCallBookVO[] getCallAcknowledgeDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getCallAcknowledgeDetails(doctorCallBookVO,_userVO);
	}
	
	public static DoctorCallBookVO[] getAllCalls(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getAllCallsAcknowledge(doctorCallBookVO,_userVO);
	}
	
	public static DoctorCallBookVO getCallDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO){
		InpatientDelegate delegate= new InpatientDelegate();
		return delegate.getCallDetails(doctorCallBookVO,_userVO);
	}
	
	public static boolean saveDoctorCallAcknowledgeDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO)
	{
		InpatientDelegate inpatientDelegate = new InpatientDelegate();
		boolean hasFlag=inpatientDelegate.saveDoctorCallAcknowledgeDetails(_callBookVO, _UserVO);
		return hasFlag;
	}
	public static List getCallRemarksNNotes(String processId,UserVO userVO)
	{
		InPatientEssentialDelegate delegate=new InPatientEssentialDelegate();
		return delegate.getCallRemarksNNotes(processId,userVO);
	}																	
}
