package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileAlertsDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileAlertsDtlDAOi
{
	public void create(ProfileAlertsDtlVO profileAlertsDtlVO, UserVO _userVO);
	
	public ProfileAlertsDtlVO[] fetchPatAlertsDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);

	public void updateIsValidStatus(ProfileAlertsDtlVO profileAlertsDtlVO, UserVO _userVO);
}
