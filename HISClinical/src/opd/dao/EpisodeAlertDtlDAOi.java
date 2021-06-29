package opd.dao;

import hisglobal.vo.EpisodeAlertDtlVO;
import hisglobal.vo.UserVO;

public interface EpisodeAlertDtlDAOi 
{
	/** Inserting New Row
	 * @param epiAlertDtlVO
	 * @param userVO
	 */
	public void create(EpisodeAlertDtlVO epiAlertDtlVO,UserVO userVO);
}
