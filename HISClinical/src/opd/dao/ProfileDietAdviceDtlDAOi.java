package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileDietAdviceDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileDietAdviceDtlDAOi 
{
	public void create(ProfileDietAdviceDtlVO _profileDietAdviceDtlVO, UserVO _userVO);
	
	public ProfileDietAdviceDtlVO fetchDischargeDietAdviceDetails(PatientProfileDetailVO _patientProfileDtlVO,UserVO _userVO);
	
	public void updateIsValidStatus(ProfileDietAdviceDtlVO profileDietAdviceDtlVO, UserVO _userVO);
}
