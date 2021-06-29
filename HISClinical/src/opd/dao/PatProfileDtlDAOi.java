package opd.dao;

import hisglobal.vo.PatProfileDtlVO;
import hisglobal.vo.UserVO;

public interface PatProfileDtlDAOi {
	public PatProfileDtlVO create(PatProfileDtlVO _patProfileDtlVO,UserVO _userVO);
	public PatProfileDtlVO[] getProfileDtlByCrNo(String _crNo,String _departmentCode,UserVO _userVO);
	public PatProfileDtlVO updateEffectiveFromToDate(PatProfileDtlVO _patProfileDtlVO,UserVO _userVO);
}
