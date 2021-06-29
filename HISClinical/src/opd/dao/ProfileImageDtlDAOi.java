package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileImageDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileImageDtlDAOi 
{
	public void create(ProfileImageDtlVO _profileImageDtlVO , UserVO _userVO);
	
	public ProfileImageDtlVO[] fetchImageProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileImageDtlVO profileImageDtlVO, UserVO _userVO);
}
