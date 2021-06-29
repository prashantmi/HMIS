package mortuary.transaction.dao;

import hisglobal.vo.DeceasedIdentityDtlVO;
import hisglobal.vo.UserVO;

public interface DeceasedIdentityDtlDAOi 
{
	public void create(DeceasedIdentityDtlVO identityDtlVO, UserVO userVO);
	
	public boolean checkBodyIdentified(String deceasedNo,UserVO userVO);
	
	public DeceasedIdentityDtlVO[] getIdentifiedByDetailByDeceasedNo(String deceasedNo,UserVO userVO);
}
