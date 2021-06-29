package mortuary.transaction.dao;

import hisglobal.vo.PostmortemHandoverDtlVO;
import hisglobal.vo.UserVO;

public interface PostmortemHandoverDtlDAOi 
{
	public void create(PostmortemHandoverDtlVO postmortemHandoverDtlVO, UserVO userVO);
	
	public boolean checkForPostmortemHandover(String deceasedNo,UserVO userVO);
}
