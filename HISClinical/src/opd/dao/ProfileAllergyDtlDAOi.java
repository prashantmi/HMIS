package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileAllergyDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileAllergyDtlDAOi
{

	public void create(ProfileAllergyDtlVO _profileAllergyDtlVO, UserVO _userVO);
	
	public ProfileAllergyDtlVO[] fetchAllergyProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileAllergyDtlVO profileAllegyDtlVO, UserVO _userVO);
}
