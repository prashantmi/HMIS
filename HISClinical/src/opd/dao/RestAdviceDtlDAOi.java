package opd.dao;

import hisglobal.vo.RestAdviceDtlVO;
import hisglobal.vo.UserVO;

public interface RestAdviceDtlDAOi 
{
	public void savePatRestAdviceTreatmentDetail(RestAdviceDtlVO restAdviceDtlVO, UserVO _userVO);
	public void updatePatRestAdviceDetail(RestAdviceDtlVO restAdviceDtlVO, UserVO _userVO);
}
