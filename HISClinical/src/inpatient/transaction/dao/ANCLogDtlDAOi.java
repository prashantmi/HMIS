package inpatient.transaction.dao;

import hisglobal.vo.ANCLogDetailVO;
import hisglobal.vo.UserVO;

public interface ANCLogDtlDAOi 
{
	/**
	 * Create ANC Log Detail
	 * @param _ancLogDetailVO ANC Log Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCLogDetailVO _ancLogDetailVO, UserVO _userVO);

}
