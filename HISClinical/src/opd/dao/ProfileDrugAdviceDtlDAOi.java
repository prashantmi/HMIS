package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileDrugAdviceDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileDrugAdviceDtlDAOi 
{
	
	public void create(ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO, UserVO _userVO);
	
	public ProfileDrugAdviceDtlVO[] fetchTreatProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO, UserVO _userVO);
	
	public ProfileDrugAdviceDtlVO[] fetchDischargeTreatProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public void updateIsValidStatusDischarge(ProfileDrugAdviceDtlVO _profileDrugAdviceDtlVO, UserVO _userVO);

	public String getMaxSerialNo(ProfileDrugAdviceDtlVO profileDrugAdviceDtlVO, UserVO _userVO);
}
