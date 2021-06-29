package inpatient.transaction.dao;

import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.UserVO;

public interface ANCChildHandoverDtlDAOi 
{
	/**
	 * Insert Child Handover Detail
	 * @param _ancChildHandoverDtlVO ANC Child Handover Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCChildHandoverDetailVO _ancChildHandoverDtlVO, UserVO _userVO);

	/**
	 * Getting Child Handover Detail
	 * @param _ancNeonatalDtlVO ANC Child Handover Detail VO
	 * @param _userVO User Detail
	 * @return 
	 */
	public ANCChildHandoverDetailVO get(ANCChildHandoverDetailVO _ancChildHandoverDtlVO, UserVO _userVO);
}
