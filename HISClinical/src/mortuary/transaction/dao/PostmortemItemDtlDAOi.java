package mortuary.transaction.dao;

import hisglobal.vo.PostmortemItemDtlVO;
import hisglobal.vo.UserVO;

public interface PostmortemItemDtlDAOi 
{
	public void create(PostmortemItemDtlVO postmortemItemDtlVO,UserVO userVO);
	
	public PostmortemItemDtlVO[] getPreservedItem(String postmortemId,UserVO userVO);
	
	public void updateStatus(String postmortemId,String itemCode,String status,UserVO userVO);
	
	public PostmortemItemDtlVO[] getPreservedItemInMortuary(String postmortemId,UserVO userVO);
	
}
