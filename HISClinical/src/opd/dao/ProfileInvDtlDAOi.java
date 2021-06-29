package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileInvDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileInvDtlDAOi 
{
	public void create(ProfileInvDtlVO _profileInvDtlVO, UserVO _userVO);
	
	public ProfileInvDtlVO[] fetchInvestigationProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileInvDtlVO _profileInvDtlVO, UserVO _userVO);

	public ProfileInvDtlVO[] fetchInvestigationProfileDetailstoGenerate(ProfileInvDtlVO[] profileInvDtlVO, UserVO _userVO); //Added by Vasu on 13-Nov-2017
			
}
