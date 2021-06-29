package opd.dao;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileClinicalDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileClinicalDtlDAOi 
{
	public void create(ProfileClinicalDtlVO _profileClinicalDtlVO, UserVO _userVO);
	
	public ProfileClinicalDtlVO[] fetchProfileComplaintsDetails(PatientProfileDetailVO _patientProfileDtlVO,String _deskType, String recordView, UserVO _userVO);
	
	public void updateIsValidStatus(String profileId, UserVO _userVO);
	
	public String fetchRecordView(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
	
	public ProfileClinicalDtlVO[] fetchProfileComplaintsDetailsDateWise(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
}
