package mortuary.transaction.dao;

import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.UserVO;

public interface MortuaryDAOi 
{
	public DeceasedDetailVO getDeceasedDetailByDeceasedNo(DeceasedDetailVO deceasedVO,UserVO userVO);
}
