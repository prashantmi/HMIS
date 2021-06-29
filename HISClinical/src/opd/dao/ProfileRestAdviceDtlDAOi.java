package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileRestAdviceDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileRestAdviceDtlDAOi 
{
	public void create(ProfileRestAdviceDtlVO _profileRestAdviceDtlVO, UserVO _userVO);

	public ProfileRestAdviceDtlVO fetchDischargeRestAdviceDetails(PatientProfileDetailVO _patientProfileDtlVO,UserVO _userVO);
	
	public void updateIsValidStatus(ProfileRestAdviceDtlVO profileRestAdviceDtlVO, UserVO _userVO);
}
