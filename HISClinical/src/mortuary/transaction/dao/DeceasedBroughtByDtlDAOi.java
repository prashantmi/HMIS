package mortuary.transaction.dao;

import hisglobal.vo.DeceasedBroughtByDtlVO;
import hisglobal.vo.UserVO;

public interface DeceasedBroughtByDtlDAOi 
{
	
	/** Inserting Record
	 * @param deceasedBroughtVO
	 * @param userVO
	 */
	public void create(DeceasedBroughtByDtlVO deceasedBroughtVO, UserVO userVO);
}
