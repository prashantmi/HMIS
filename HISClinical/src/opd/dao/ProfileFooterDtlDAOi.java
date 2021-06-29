package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileFooterDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileFooterDtlDAOi 
{
	public void create(ProfileFooterDtlVO _profileFooterDtlVO , UserVO _userVO);
	
	public ProfileFooterDtlVO[] fetchProfileFooterDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileFooterDtlVO profileFooterDetailVO, UserVO _userVO);

}
