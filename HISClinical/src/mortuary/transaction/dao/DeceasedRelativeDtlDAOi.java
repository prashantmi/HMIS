package mortuary.transaction.dao;

import hisglobal.vo.DeceasedRelativeDtlVO;
import hisglobal.vo.UserVO;

public interface DeceasedRelativeDtlDAOi 
{
	
	/** Inserting Record
	 * @param deceasedRelativeVO
	 * @param userVO
	 */
	public void create(DeceasedRelativeDtlVO deceasedRelativeVO, UserVO userVO);
	
	public DeceasedRelativeDtlVO[] getDeceasedExistingRelative(String deceasedNo,UserVO userVO);
	
	public DeceasedRelativeDtlVO getPostmortemRequestedByRelative(String deceasedNo,UserVO userVO);
	
}
