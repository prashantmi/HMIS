package mortuary.transaction.dao;

import hisglobal.vo.PostmortemItemReqDtlVO;
import hisglobal.vo.UserVO;

public interface PostmortemItemReqDAOi 
{
	//Inserting Record
	public void create(PostmortemItemReqDtlVO postmortmItemReqVO, UserVO userVO);
	
	//Getting the Item To Be Preserved
	public PostmortemItemReqDtlVO[] getItemToBePreserved(String postmortemId,UserVO userVO);
	
	public void update(PostmortemItemReqDtlVO postmortmItemReqVO, UserVO userVO);
	
}
