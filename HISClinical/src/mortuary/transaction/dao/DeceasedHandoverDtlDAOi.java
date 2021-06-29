package mortuary.transaction.dao;

import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.UserVO;

public interface DeceasedHandoverDtlDAOi 
{
	
	/** Inserting Record
	 * @param deceasedHandoverVO
	 * @param userVO
	 */
	public void create(DeceasedHandoverDtlVO deceasedHandoverVO, UserVO userVO);
}
