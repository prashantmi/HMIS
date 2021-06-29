package inpatient.transaction.dao;

import hisglobal.vo.ANCVisitDetailVO;
import hisglobal.vo.UserVO;

public interface ANCVisitDtlDAOi 
{
	/**
	 * Getting ANC Visit Detail 
	 * @param _ancVisitDetailVO ANC Visit Detail VO
	 * @param _userVO User Detail
	 * @return ANC Visit Detail
	 */
	public ANCVisitDetailVO getANCVisitDetail(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO);
	
	/**
	 * Create ANC Visit Detail
	 * @param _ancVisitDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 */
	public void create(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO);

	/**
	 * Update ANC Visit Detail
	 * @param _ancVisitDetailVO ANC Detail VO
	 * @param _userVO User Detail
	 */
	public void update(ANCVisitDetailVO _ancVisitDetailVO, UserVO _userVO);
}
