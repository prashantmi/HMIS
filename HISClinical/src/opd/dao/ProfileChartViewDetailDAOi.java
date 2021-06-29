package opd.dao;

import java.util.List;

import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.ProfileChartViewDtlVO;
import hisglobal.vo.UserVO;

public interface ProfileChartViewDetailDAOi {
	
public void create(ProfileChartViewDtlVO _profileOTDtlVO, UserVO _userVO);
	
public List<ProfileChartViewDtlVO> fetchChartViewProfileDetails(PatientProfileDetailVO _patientProfileDtlVO, UserVO _userVO);

	
public void updateIsValidStatus(ProfileChartViewDtlVO _profileOTDtlVO, UserVO _userVO);

}
