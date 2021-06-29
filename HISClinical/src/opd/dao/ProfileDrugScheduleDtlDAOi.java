package opd.dao;

import java.util.List;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileDrugScheduleDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileDrugScheduleDtlDAOi {

	public void create(ProfileDrugScheduleDtlVO drugScheduleDtlVO, UserVO _userVO);
	
	public void updateIsValidStatus(ProfileDrugScheduleDtlVO drugScheduleDtlVO,UserVO _userVO);
	
	public List<ProfileDrugScheduleDtlVO> fetchDrugSchedule(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);
}
