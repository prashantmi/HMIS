package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileDiagnosisDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileDiagnosisDtlDAOi {
	
	/** 
	 * Saving Patient Profile Access Detail
	 * @param _profileAccessDtlVO Profile Access Detail
	 * @param _userVO User VO
	 */
	public void create(ProfileDiagnosisDtlVO _profileDiagnosisTypeVO, UserVO _userVO);
	
	public ProfileDiagnosisDtlVO[] fetchDiagProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileDiagnosisDtlVO _profileDiagnosisTypeVO, UserVO _userVO);

}
