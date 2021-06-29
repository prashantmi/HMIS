package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileExtTreatmentDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileExtTreatmentDtlDAOi 
{
	
	public void create(ProfileExtTreatmentDtlVO _profileExtTreatmentDtlVO, UserVO _userVO);
	
	public ProfileExtTreatmentDtlVO[] fetchDischargeExtTreatmentDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileExtTreatmentDtlVO _profileExtTreatmentDtlVO, UserVO _userVO);

}
