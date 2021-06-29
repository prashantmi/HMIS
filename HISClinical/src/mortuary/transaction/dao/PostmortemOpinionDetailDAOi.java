package mortuary.transaction.dao;

import hisglobal.vo.PostmortemOpinionDetailVO;
import hisglobal.vo.UserVO;

public interface PostmortemOpinionDetailDAOi 
{
	//Inserting Record
	public void create(PostmortemOpinionDetailVO postmortemOpinionVO, UserVO userVO);
	
	public PostmortemOpinionDetailVO[] getAddedOpinion(String postmortemId,UserVO userVO);
	
	public void invalidTheRecord(String postmortemId, UserVO userVO);
	
	public void updateOpinion(PostmortemOpinionDetailVO postmortemOpinionVO, UserVO userVO);
}
