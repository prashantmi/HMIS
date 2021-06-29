package inpatient.transaction.dao;

import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.UserVO;

public interface DoctorCallAcknowledgeDAOi 
{
	public DoctorCallBookVO[] getCallAcknowledgeDetails(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	
	public DoctorCallBookVO[] getAllCallsAcknowledge(DoctorCallBookVO doctorCallBookVO,UserVO _userVO);
	
	public void saveDoctorCallAcknowledgeDetails(DoctorCallBookVO _callBookVO, UserVO _UserVO);
}
