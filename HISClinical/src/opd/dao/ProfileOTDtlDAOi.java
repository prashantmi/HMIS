package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileOTDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileOTDtlDAOi
{
	public void create(ProfileOTDtlVO _profileOTDtlVO, UserVO _userVO);
	
	public ProfileOTDtlVO[] fetchOTProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileOTDtlVO _profileOTDtlVO, UserVO _userVO);
}
